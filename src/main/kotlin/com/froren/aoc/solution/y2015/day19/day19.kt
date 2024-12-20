package com.froren.aoc.solution.y2015.day19

import com.froren.aoc.common.iteration.mergeToMap
import com.froren.aoc.common.parse.asSingleLine
import com.froren.aoc.common.parse.twoParts
import com.froren.aoc.common.solve
import java.util.PriorityQueue
import kotlin.math.abs

private val ELEMENT_REGEX = Regex("""[A-Z][a-z]?|e""")

fun main() {
    solve(
        year = 2015,
        day = 19,
        part = 1,
    ) {
        val (replacements, molecule) = input.twoParts().let {
            it.first.getReplacements() to it.second.asSingleLine().splitToElements()
        }

        replacements
            .applyOn(molecule)
            .toSet()
            .size
    }

    solve(
        year = 2015,
        day = 19,
        part = 2,
    ) {
        val (replacements, molecule) = input.twoParts().let {
            it.first.getReplacements() to it.second.asSingleLine().splitToElements()
        }

        TODO("Replace with CYK parsing algorithm")

        moleculeAStar(
            start = listOf("e"),
            end = molecule,
            replacements = replacements
        )
    }
}

private typealias Molecule = List<String>

private fun moleculeAStar(start: Molecule, end: Molecule, replacements: Map<String, List<Molecule>>): ReplacementState? {
    val openReplacements = PriorityQueue<ReplacementState>(compareBy { it.preference })
    val seenMolecules = mutableSetOf<Molecule>()

    openReplacements.add(ReplacementState(level = 0, preference = 0, start))

    while (openReplacements.isNotEmpty()) {
        val replacement = openReplacements.poll()

        if (replacement.molecule == end)
            return replacement

        replacements.applyOn(replacement.molecule).forEach { newMolecule ->
            if (newMolecule in seenMolecules)
                return@forEach

            val newPreference = replacement.preference + distance(newMolecule, end) + abs(newMolecule.size - end.size)
            openReplacements.add(
                ReplacementState(level = replacement.level + 1, preference = newPreference, molecule = newMolecule)
            )
        }
    }

    return null
}

private data class ReplacementState(
    val level: Int,
    val preference: Int,
    val molecule: Molecule,
)

private fun distance(m1: Molecule, m2: Molecule) =
    (m1.union(m2).size) - (m1.intersect(m2).size)


private fun Map<String, List<Molecule>>.applyOn(molecule: Molecule) =
    molecule.flatMapIndexed { i, element ->
        getOrElse(element) { emptyList() }
            .map { replacement ->
                molecule.safeSublist(0,i-1) + replacement + molecule.safeSublist(i+1,molecule.size-1)
            }
    }

private fun <T> List<T>.safeSublist(start: Int, end: Int) =
    if (start < end)
        subList(start, end)
    else
        emptyList()

private fun String.splitToElements() =
    ELEMENT_REGEX.findAll(this).map { it.value }.toList()

private fun Sequence<String>.getReplacements() =
    map {
        val parts = it.split(" => ")
        parts[0] to parts[1]
    }.mergeToMap { s1, s2 ->
        "$s1;$s2"
    }.mapValues {
        it.value.split(";").map { it.splitToElements() }
    }


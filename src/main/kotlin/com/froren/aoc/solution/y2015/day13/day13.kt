package com.froren.aoc.solution.y2015.day13

import com.froren.aoc.common.data.asSequence
import com.froren.aoc.common.data.with
import com.froren.aoc.common.iteration.mergeToMap
import com.froren.aoc.common.iteration.permutations
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 13,
        part = 1,
    ) {
        val preferences = input.getPreferences().mergeToMap { a, b -> a + b }

        preferences
            .keys.flatMap { it.asSequence() }.toSet()
            .permutations()
            .maxOf {
                it.zipWithNext { a, b ->
                    preferences[a with b] ?: 0
                }
                    .sum()
                    .plus(preferences[it.first() with it.last()] ?: 0)
            }
    }

    solve(
        year = 2015,
        day = 13,
        part = 2,
    ) {
        val preferences = input.getPreferences().mergeToMap { a, b -> a + b }

        preferences
            .keys.flatMap { it.asSequence() }.toMutableSet().also { it.add("Me") }
            .permutations()
            .maxOf {
                it.zipWithNext { a, b ->
                    preferences[a with b] ?: 0
                }
                    .sum()
                    .plus(preferences[it.first() with it.last()] ?: 0)
            }
    }
}

private fun Sequence<String>.getPreferences() =
    map {
        val parts = it.split(" ")

        val person = parts[0]
        val neighbour = parts.last().dropLast(1) // drop .
        val mult = if (parts[2] == "lose") -1 else +1
        val preference = parts[3].toInt()

        (person with neighbour) to preference * mult
    }
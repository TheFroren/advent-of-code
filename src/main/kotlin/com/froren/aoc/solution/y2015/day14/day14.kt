package com.froren.aoc.solution.y2015.day14

import com.froren.aoc.common.solve
import kotlin.math.min

fun main() {
    solve(
        year = 2015,
        day = 14,
        part = 1,
    ) {
        input.getReindeer()
            .maxOf {
                it.distance(2503)
            }
    }

    solve(
        year = 2015,
        day = 14,
        part = 1,
    ) {
        val points = input.getReindeer().map { it to 0 }.toMap().toMutableMap()

        for (second in 1..2503) {
            val winning = points.keys.maxBy {
                it.distance(second)
            }

            points[winning] = points[winning]!! + 1
        }

        points.values.max()
    }
}

private fun Reindeer.distance(seconds: Int): Int {
    val fullCycles = seconds / (flyDuration + restDuration)
    val remainderFlight = min(seconds - (flyDuration + restDuration) * fullCycles, flyDuration)

    return (fullCycles * flyDuration + remainderFlight) * speed
}

private data class Reindeer(
    val speed: Int,
    val flyDuration: Int,
    val restDuration: Int,
)

private fun Sequence<String>.getReindeer() =
    map {
        Regex("""\d+""")
            .findAll(it)
            .map { it.value.toInt() }
            .toList()
            .let {
                Reindeer(it[0], it[1], it[2])
            }
    }
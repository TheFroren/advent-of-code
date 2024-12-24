package com.froren.aoc.solution.y2016.day06

import com.froren.aoc.common.data.CountingSet
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2016,
        day = 6,
        part = 1
    ) {
        val words = input.toList()

        val counts = List(words.first().length) { CountingSet<Char>() }

        words.forEach { word ->
            word.forEachIndexed { i, char ->
                counts[i].add(char)
            }
        }

        counts.joinToString("") {
            it.entries
                .maxBy { it.value }
                .key
                .toString()
        }
    }

    solve(
        year = 2016,
        day = 6,
        part = 2
    ) {
        val words = input.toList()

        val counts = List(words.first().length) { CountingSet<Char>() }

        words.forEach { word ->
            word.forEachIndexed { i, char ->
                counts[i].add(char)
            }
        }

        counts.joinToString("") {
            it.entries
                .minBy { it.value }
                .key
                .toString()
        }
    }
}

package com.froren.aoc.solution.y2016.day07

import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2016,
        day = 7,
        part = 1
    ) {
        input.count {
            val parts = it.split("[", "]")

            parts.filterIndexed { i, part -> part.containsABBA() && (i%2 == 0)}.any()
                && parts.filterIndexed { i, _ -> i%2 == 1 }.all { !it.containsABBA() }
        }
    }

    solve(
        year = 2016,
        day = 7,
        part = 2
    ) {
        input.count {
            val parts = it.split("[", "]")

            val allABA = parts
                .filterIndexed { i, _ -> i%2 == 0 }
                .flatMap { it.getAllABA() }
                .toSet()

            val allBAB = parts
                .filterIndexed { i, _ -> i%2 == 1 }
                .flatMap { it.getAllBAB() }
                .toSet()

            allABA.intersect(allBAB).isNotEmpty()
        }
    }
}

private fun String.containsABBA() =
    windowed(size = 4, step = 1) {
        it[0] == it[3] && it[1] == it[2] && it[0] != it[1]
    }.any { it }

private fun String.getAllABA() =
    windowed(size = 3, step = 1)
        .filter{
            it[0] == it[2] && it[0] != it[1]
        }
        .map {
            it[0] to it[1]
        }
        .toSet()

private fun String.getAllBAB() =
    getAllABA().map { it.second to it.first }.toSet()

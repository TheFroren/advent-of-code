package com.froren.aoc.solution.y2016.day03

import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2016,
        day = 3,
        part = 1
    ) {
        input
            .getTriangles()
            .count {
                (it.sum() / it.max().toDouble()) > 2.0
            }
    }

    solve(
        year = 2016,
        day = 3,
        part = 1
    ) {
        input
            .getTriangles()
            .windowed(size = 3, step = 3) {
                listOf(
                    listOf(it[0][0], it[1][0], it[2][0]),
                    listOf(it[0][1], it[1][1], it[2][1]),
                    listOf(it[0][2], it[1][2], it[2][2]),
                )
            }
            .flatMap { it }
            .count {
                (it.sum() / it.max().toDouble()) > 2.0
            }
    }
}


private fun Sequence<String>.getTriangles() =
    map {
        it.split(" ", )
            .filter(String::isNotEmpty)
            .map(String::toInt)
    }

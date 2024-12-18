package com.froren.aoc.solution.y2015.day02

import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 2,
        part = 1
    ) {
        input
            .map {
                it.split('x').map(String::toInt)
            }
            .map {
                listOf(
                    2*it[0]*it[1],
                    2*it[1]*it[2],
                    2*it[2]*it[0],
                )
            }
            .sumOf {
                it.sum() + it.min()/2
            }
    }

    solve(
        year = 2015,
        day = 2,
        part = 2
    ) {
        input
            .map {
                it.split('x').map(String::toInt)
            }
            .sumOf {
                (it[0] * it[1] * it[2]) +
                (it.sorted().take(2).sum() * 2)
            }
    }
}

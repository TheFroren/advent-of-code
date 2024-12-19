package com.froren.aoc.solution.y2015.day17

import com.froren.aoc.common.iteration.combinations
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 17,
        part = 1,
    ) {
        input
            .map(String::toInt)
            .toList()
            .let {
                (1..it.size).flatMap { size ->
                    it.combinations(size)
                }
            }
            .count {
                it.sum() == 150
            }
    }

    solve(
        year = 2015,
        day = 17,
        part = 2,
    ) {
        input
            .map(String::toInt)
            .toList()
            .let {
                (1..it.size).map { size ->
                    it.combinations(size).count { it.sum() == 150 }
                }
            }
            .first { it > 0}
    }
}

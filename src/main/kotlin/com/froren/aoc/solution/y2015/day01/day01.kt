package com.froren.aoc.solution.y2015.day01

import com.froren.aoc.common.inputAsLine
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 1,
        part = 1
    ) {
        inputAsLine()
            .asSequence()
            .map {
                when(it) {
                    '(' ->  1
                    ')' -> -1
                    else -> 0
                }
            }
            .sum()
    }

    solve(
        year = 2015,
        day = 1,
        part = 2
    ) {
        inputAsLine()
            .asSequence()
            .map {
                when(it) {
                    '(' ->  1
                    ')' -> -1
                    else -> 0
                }
            }
            .runningReduce(Int::plus)
            .indexOfFirst { it < 0 }
            .let { it + 1}
    }
}

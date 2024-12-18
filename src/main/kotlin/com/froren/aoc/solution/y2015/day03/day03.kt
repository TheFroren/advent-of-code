package com.froren.aoc.solution.y2015.day03

import com.froren.aoc.common.grid.Vector
import com.froren.aoc.common.grid.plus
import com.froren.aoc.common.inputAsLine
import com.froren.aoc.common.iteration.asSequence
import com.froren.aoc.common.iteration.asValueSequence
import com.froren.aoc.common.parse.arrowToDir
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 3,
        part = 1
    ) {
        inputAsLine()
            .map(::arrowToDir)
            .runningFold(Vector(0,0)) { prev, dir ->
                prev + dir
            }
            .toSet()
            .size
    }

    solve(
        year = 2015,
        day = 3,
        part = 2
    ) {
        inputAsLine()
            .map(::arrowToDir)
            .withIndex()
            .partition { it.index % 2 == 0 }
            .asSequence()
            .flatMap {
                it.asValueSequence()
                    .runningFold(Vector(0,0)) { prev, dir ->
                        prev + dir
                    }
            }
            .toSet()
            .size
    }
}

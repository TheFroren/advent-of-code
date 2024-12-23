package com.froren.aoc.solution.y2016.day01

import com.froren.aoc.common.grid.*
import com.froren.aoc.common.parse.asSingleLine
import com.froren.aoc.common.solve
import kotlin.math.abs

fun main() {
    solve(
        year = 2016,
        day = 1,
        part = 1
    ) {
        var lastDir = Dir.N
        input.asSingleLine()
            .split(", ")
            .fold(Vector(0,0)) { total, s ->
                val size = s.substring(1).toInt()
                lastDir = when(s[0]) {
                    'L' -> lastDir.turnCW(-1)
                    'R' -> lastDir.turnCW(1)
                    else -> lastDir
                }
                total + size * lastDir.v
            }.let {
                abs(it.x) + abs(it.y)
            }
    }

    solve(
        year = 2016,
        day = 1,
        part = 2
    ) {
        var lastDir = Dir.N
        val positions = mutableSetOf<Vector>()

        input.asSingleLine()
            .split(", ")
            .fold(Vector(0,0)) { total, s ->
                val size = s.substring(1).toInt()
                lastDir = when(s[0]) {
                    'L' -> lastDir.turnCW(-1)
                    'R' -> lastDir.turnCW(1)
                    else -> lastDir
                }

                for (s in 1..size) {
                    val pos = total + s * lastDir.v
                    if (pos in positions)
                        return@solve pos.let { abs(it.x) + abs(it.y) }

                    positions.add(pos)
                }

                total + size * lastDir.v
            }
    }
}

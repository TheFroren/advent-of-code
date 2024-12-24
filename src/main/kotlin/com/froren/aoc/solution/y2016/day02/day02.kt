package com.froren.aoc.solution.y2016.day02

import com.froren.aoc.common.grid.*
import com.froren.aoc.common.parse.asSingleLine
import com.froren.aoc.common.parse.dirCharToDir
import com.froren.aoc.common.solve
import kotlin.math.abs

private val KEYPAD: Grid<Int> = listOf(
    listOf(1, 2, 3),
    listOf(4, 5, 6),
    listOf(7, 8, 9),
)

private val KEYPAD_AREA = Area(3,3)

private val KEAYPAD_2: Grid<Char> = listOf(
    listOf(' ', ' ', '1', ' ', ' '),
    listOf(' ', '2', '3', '4', ' '),
    listOf('5', '6', '7', '8', '9'),
    listOf(' ', 'A', 'B', 'C', ' '),
    listOf(' ', ' ', 'D', ' ', ' '),
)

fun main() {
    solve(
        year = 2016,
        day = 2,
        part = 1
    ) {
        input.getCommands()
            .runningFold(Vector(1,1)) { start, commands ->
                commands.fold(start) { pos, dir ->
                    val newPos = pos + dir
                    if (newPos in KEYPAD_AREA)
                        newPos
                    else
                        pos
                }
            }
            .drop(1)
            .map {
                KEYPAD[it]
            }.joinToString("")
    }

    solve(
        year = 2016,
        day = 2,
        part = 2
    ) {
        input.getCommands()
            .runningFold(Vector(0,2)) { start, commands ->
                commands.fold(start) { pos, dir ->
                    val newPos = pos + dir
                    val distCenter = abs(newPos.x-2) + abs(newPos.y-2)
                    if (distCenter <= 2)
                        newPos
                    else
                        pos
                }
            }
            .drop(1)
            .map {
                KEAYPAD_2[it]
            }.joinToString("")
    }
}

private fun Sequence<String>.getCommands() =
    map {
        it.map(::dirCharToDir)
    }

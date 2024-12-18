package com.froren.aoc.solution.y2015.day06

import com.froren.aoc.common.grid.Area
import com.froren.aoc.common.grid.forEachCoordinate
import com.froren.aoc.common.solve
import kotlin.math.max

private val COMMAND_REGEX = Regex("""([\w ]+) (\d+),(\d+) through (\d+),(\d+)""")

fun main() {
    solve(
        year = 2015,
        day = 6,
        part = 1
    ) {
        val lights = Array(1000) { BooleanArray(1000) { false } }

        input
            .getCommandsAndAreas()
            .forEach { (command, area) ->
                area.forEachCoordinate { x, y ->
                    lights[y][x] = when (command) {
                        Command.TURN_ON -> true
                        Command.TURN_OFF -> false
                        Command.TOGGLE -> !lights[y][x]
                    }
                }
            }

        lights.sumOf { it.count { it } }
    }

    solve(
        year = 2015,
        day = 6,
        part = 2
    ) {
        val lights = Array(1000) { IntArray(1000) { 0 } }

        input
            .getCommandsAndAreas()
            .forEach { (command, area) ->
                area.forEachCoordinate { x, y ->
                    lights[y][x] = when (command) {
                        Command.TURN_ON -> lights[y][x] + 1
                        Command.TURN_OFF -> max(0, lights[y][x] - 1)
                        Command.TOGGLE -> lights[y][x] + 2
                    }
                }
            }

        lights.sumOf { it.sum() }
    }
}

fun Sequence<String>.getCommandsAndAreas() =
    mapNotNull { commandString ->
        COMMAND_REGEX.matchEntire(commandString)?.let {
            val command = when (it.groupValues[1]) {
                "turn on" -> Command.TURN_ON
                "turn off" -> Command.TURN_OFF
                "toggle" -> Command.TOGGLE
                else -> return@mapNotNull null
            }

            val area = Area(
                xBounds = it.groupValues[2].toInt() .. it.groupValues[4].toInt(),
                yBounds = it.groupValues[3].toInt() .. it.groupValues[5].toInt(),
            )

            command to area
        }
    }

enum class Command {
    TURN_ON,
    TURN_OFF,
    TOGGLE,
}

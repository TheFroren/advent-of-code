package com.froren.aoc.solution.y2016.day08

import com.froren.aoc.common.grid.MutableGrid
import com.froren.aoc.common.grid.println
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2016,
        day = 8,
        part = 1
    ) {
        val grid = MutableGrid(50, 6) { _,_ -> false }

        input.getCommands().forEach { (command, args) ->
            when(command) {
                Command.RECT -> grid.setCorner(args.first, args.second, true)
                Command.ROTATE_ROW -> grid.rotateRow(args.first, args.second)
                Command.ROTATE_COLUMN -> grid.rotateColumn(args.first, args.second)
            }
        }

        grid.sumOf { it.count { it } }
    }

    solve(
        year = 2016,
        day = 8,
        part = 2
    ) {
        val grid = MutableGrid(50, 6) { _,_ -> false }

        input.getCommands().forEach { (command, args) ->
            when(command) {
                Command.RECT -> grid.setCorner(args.first, args.second, true)
                Command.ROTATE_ROW -> grid.rotateRow(args.first, args.second)
                Command.ROTATE_COLUMN -> grid.rotateColumn(args.first, args.second)
            }
        }

        grid.println { if (it) '#' else ' ' }
    }
}

private fun <T> MutableGrid<T>.rotateRow(y: Int, by: Int) {
    val width = first().size
    val oldRow = List(width) { x -> this[y][x] }
    oldRow.forEachIndexed { oldX, it ->
        this[y][(oldX+by)%width] = it
    }
}

private fun <T> MutableGrid<T>.rotateColumn(x: Int, by: Int) {
    val height = size
    val oldRow = List(height) { y -> this[y][x] }
    oldRow.forEachIndexed { oldY, it ->
        this[(oldY+by)%height][x] = it
    }
}

private fun <T> MutableGrid<T>.setCorner(width: Int, height: Int, value: T) {
    for (y in 0..<height) {
        for (x in 0..<width) {
            this[y][x] = value
        }
    }
}

private enum class Command {
    RECT, ROTATE_ROW, ROTATE_COLUMN
}

private fun Sequence<String>.getCommands() =
    mapNotNull {
        val parts = it.split(" ")

        when {
            parts.size == 2 -> Command.RECT to (
                parts[1]
                    .split("x")
                    .let {
                        it[0].toInt() to it[1].toInt()
                    }
            )
            parts[1] == "row" -> Command.ROTATE_ROW to (
                    parts[2].substring(2).toInt() to parts[4].toInt()
            )
            parts[1] == "column" -> Command.ROTATE_COLUMN to (
                    parts[2].substring(2).toInt() to parts[4].toInt()
            )
            else -> null
        }
    }


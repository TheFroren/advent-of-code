package com.froren.aoc.solution.y2015.day18

import com.froren.aoc.common.grid.*
import com.froren.aoc.common.parse.toGrid
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 18,
        part = 1,
    ) {
        var grid = input.toGrid { it == '#' }

        (1..100).forEach {
            grid = conway(grid)
        }

        grid.sumOf { it.count { it } }
    }

    solve(
        year = 2015,
        day = 18,
        part = 1,
    ) {
        var grid = input.toGrid { it == '#' }

        (1..100).forEach {
            grid = conwayCorners(grid)
        }

        grid.sumOf { it.count { it } }
    }
}

fun conway(grid: Grid<Boolean>): Grid<Boolean> {
    val area = Area(grid.first().size, grid.size)
    val newGrid = MutableGrid(area.width, area.height) { x, y -> false }

    area.forEachCoordinate { x, y ->
        val coord = Vector(x,y)

        val neighbours = Dir8.entries.count {
            val target = coord + it
            target in area && grid[target]
        }

        newGrid[coord] = when {
            grid[coord] && neighbours in 2..3 -> true
            !grid[coord] && neighbours == 3 -> true
            else -> false
        }
    }

    return newGrid
}

fun conwayCorners(grid: Grid<Boolean>): Grid<Boolean> {
    val area = Area(grid.first().size, grid.size)
    val newGrid = MutableGrid(area.width, area.height) { x, y -> false }

    val corners = listOf(
        Vector(0,0),
        Vector(0, area.height-1),
        Vector(area.width-1, 0),
        Vector(area.width-1, area.height-1),
    )

    area.forEachCoordinate { x, y ->
        val coord = Vector(x,y)

        val neighbours = Dir8.entries.count {
            val target = coord + it
            target in area && grid[target] || target in corners
        }

        newGrid[coord] = when {
            grid[coord] && neighbours in 2..3 -> true
            !grid[coord] && neighbours == 3 -> true
            else -> false
        }
    }

    corners.forEach {
        newGrid[it] = true
    }

    return newGrid
}

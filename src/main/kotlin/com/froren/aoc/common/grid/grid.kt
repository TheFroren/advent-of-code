package com.froren.aoc.common.grid

typealias Grid<T> = List<List<T>>

operator fun <T> Grid<T>.get(vector: Vector) = this[vector.y][vector.x]

typealias MutableGrid<T> = MutableList<MutableList<T>>
operator fun <T> MutableGrid<T>.set(vector: Vector, value: T) { this[vector.y][vector.x] = value }

fun <T> MutableGrid(width: Int, height: Int, default: (Int, Int) -> T) =
    MutableList(height) { y ->
        MutableList(width) { x ->
            default(x,y)
        }
    }

fun <T> Grid<T>.println(displayMapping: (T) -> Char) {
    forEach {
        it.forEach {
            print(displayMapping(it))
        }
        println()
    }
}

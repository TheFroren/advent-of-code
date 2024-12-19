package com.froren.aoc.common.parse

import com.froren.aoc.common.grid.Grid
import com.froren.aoc.common.grid.MutableGrid

fun <T> Sequence<String>.toGrid(mapping: (Char) -> T): Grid<T> =
    map {
        it.map(mapping)
    }.toList()

fun <T> Sequence<String>.toMutableGrid(mapping: (Char) -> T): MutableGrid<T> =
    map {
        it.mapTo(mutableListOf(), mapping)
    }.toMutableList()


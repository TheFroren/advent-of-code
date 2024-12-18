package com.froren.aoc.common.parse

import com.froren.aoc.common.grid.Dir

fun arrowToDir(arrow: Char) =
    when(arrow) {
        '^' -> Dir.N
        '>' -> Dir.E
        'v' -> Dir.S
        '<' -> Dir.W
        else -> error("Unknown arrow symbol")
    }
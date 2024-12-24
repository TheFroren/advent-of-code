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

fun dirCharToDir(arrow: Char) =
    when(arrow) {
        'U' -> Dir.N
        'R' -> Dir.E
        'D' -> Dir.S
        'L' -> Dir.W
        else -> error("Unknown dir symbol")
    }

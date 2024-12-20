package com.froren.aoc.common.parse

fun Sequence<String>.twoParts(): Pair<Sequence<String>,Sequence<String>> {
    val parts = joinToString("\n").split("\n\n", limit = 2)
    return parts[0].lineSequence() to parts[1].lineSequence()
}

fun Sequence<String>.asSingleLine() = joinToString("")

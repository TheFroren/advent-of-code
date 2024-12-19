package com.froren.aoc.solution.y2015.day12

import com.froren.aoc.common.inputAsLine
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 12,
        part = 1,
    ) {
        val json = inputAsLine()

        Regex("""-?\d+""")
            .findAll(json)
            .sumOf { it.value.toInt() }
    }

    solve(
        year = 2015,
        day = 12,
        part = 2,
    ) {
        val cleanedJson = cleanRed(inputAsLine())

        Regex("""-?\d+""")
            .findAll(cleanedJson)
            .sumOf { it.value.toInt() }
    }
}

private fun cleanRed(src: String): String {
    var cleaned = src
    var i = 1
    val lbStack = mutableListOf(0)

    while (lbStack.isNotEmpty()) {
        if (cleaned[i] == '{')
            lbStack += i

        if (cleaned[i] == '}') {
            val start = lbStack.removeLast()

            if (Regex(""":"red"""").containsMatchIn(cleaned.substring(start..i))) {
                cleaned = cleaned.replaceRange(start+1..i, "")
                i = start
            }
        }

        i++
    }

    return cleaned
}
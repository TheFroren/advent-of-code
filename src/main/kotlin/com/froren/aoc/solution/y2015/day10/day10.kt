package com.froren.aoc.solution.y2015.day10

import com.froren.aoc.common.inputAsLine
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 10,
        part = 1,
    ) {
        var input = inputAsLine()

        (1..40).forEach {
            input = lookAndSay(input)
        }

        input.length
    }

    solve(
        year = 2015,
        day = 10,
        part = 2,
    ) {
        var input = inputAsLine()

        (1..50).forEach {
            input = lookAndSay(input)
        }

        input.length
    }
}

private fun lookAndSay(input: String) = buildString {
    var count = 0
    var last = input.first()

    input.forEach {
        if (it == last) {
            count++
            return@forEach
        }

        append(count)
        append(last)

        count = 1
        last = it
    }

    append(count)
    append(last)
}

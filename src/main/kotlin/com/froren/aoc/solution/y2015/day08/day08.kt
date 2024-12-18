package com.froren.aoc.solution.y2015.day08

import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 8,
        part = 1
    ) {
        input.sumOf { line ->
            var escapedLine = line.substring(1..<line.length-1)

            escapedLine = escapedLine.replace(Regex("""(\\\\)|(\\")|\\x(\d|[abcdef]){2}"""), "!")

            line.length - escapedLine.length
        }
    }

    solve(
        year = 2015,
        day = 8,
        part = 2
    ) {
        input.sumOf { line ->
            4 + Regex("""(\\\\)|(\\")|\\x(\d|[abcdef]){2}""")
                .findAll(line)
                .sumOf {
                    when(it.value) {
                        "\\\\" -> 2L
                        "\\\"" -> 2L
                        else -> 1L
                    }
                }
        }
    }
}

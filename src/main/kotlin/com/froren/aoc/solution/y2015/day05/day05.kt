package com.froren.aoc.solution.y2015.day05

import com.froren.aoc.common.solve

private val VOVELS = listOf('a','e','i','o','u')

fun main() {
    solve(
        year = 2015,
        day = 5,
        part = 1
    ) {
        input
            .filter {
                !it.contains(Regex("""ab|cd|pq|xy"""))
            }
            .count { string ->
                var vovels = 0
                var last = ' '
                var double = false

                string.forEach {
                    if (it in VOVELS)
                        vovels++

                    if (last == it)
                        double = true

                    last = it

                    if (vovels >= 3 && double)
                        return@count true
                }

                return@count false
            }
    }

    solve(
        year = 2015,
        day = 5,
        part = 2
    ) {
        input.count { string ->
            val tuples = mutableSetOf<String>()
            var tupleDouble = false
            var pairBetween = false

            string.windowed(size = 3, step = 1, partialWindows = true).forEach { chars ->
                if (chars.length == 1)
                    return@forEach

                if (chars.length == 2 || !(chars[0] == chars[1] && chars[0] == chars[2])) {
                    val tuple = "${chars[0]}${chars[1]}"

                    if (tuple in tuples)
                        tupleDouble = true
                    else
                        tuples.add(tuple)
                }

                if (chars.length == 3 && chars[0] == chars[2])
                    pairBetween = true

                if (tupleDouble && pairBetween)
                    return@count true
            }

            return@count false
        }
    }
}

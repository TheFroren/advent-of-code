package com.froren.aoc.solution.y2015.day16

import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 16,
        part = 1,
    ) {
        input.getInfo()
            .indexOfFirst {
                it.all {
                    SCAN[it.first] == it.second
                }
            } + 1
    }

    solve(
        year = 2015,
        day = 16,
        part = 2,
    ) {
        input.getInfo()
            .indexOfFirst {
                it.all {
                    val scan = SCAN[it.first]!!
                    when (it.first) {
                        in MORE_KEY -> it.second > scan
                        in FEWER_KEY -> it.second < scan
                        else -> it.second == scan
                    }
                }
            } + 1
    }
}

private val SCAN = mapOf(
    "children" to 3,
    "cats" to 7,
    "samoyeds" to 2,
    "pomeranians" to 3,
    "akitas" to 0,
    "vizslas" to 0,
    "goldfish" to 5,
    "trees" to 3,
    "cars" to 2,
    "perfumes" to 1,
)

private val MORE_KEY = listOf("cats", "trees")
private val FEWER_KEY = listOf("pomeranians", "goldfish")

private fun Sequence<String>.getInfo() =
    map {
        Regex("""(\w+): (\d+)""")
            .findAll(it)
            .map {
                it.groupValues[1] to it.groupValues[2].toInt()
            }.toList()
    }
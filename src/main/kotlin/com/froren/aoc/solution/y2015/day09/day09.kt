package com.froren.aoc.solution.y2015.day09

import com.froren.aoc.common.iteration.permutations
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 9,
        part = 1
    ) {
        val distances = input.getDistances()

        distances.keys.permutations().minOf {
            it.zipWithNext { a, b ->
                distances[a]!![b]!!
            }.sum()
        }
    }

    solve(
        year = 2015,
        day = 9,
        part = 2
    ) {
        val distances = input.getDistances()

        distances.keys.permutations().maxOf {
            it.zipWithNext { a, b ->
                distances[a]!![b]!!
            }.sum()
        }
    }
}

private typealias Distances = MutableMap<String, MutableMap<String, Int>>

private fun Sequence<String>.getDistances(): Distances {
    val distances: Distances = mutableMapOf()

    forEach { line ->
        val places = line.split(" = ")[0].split(" to ")
        val distance = line.split(" = ")[1].toInt()
        distances.add(places[0], places[1], distance)
    }

    return distances
}

private fun Distances.add(first: String, second: String, distance: Int) {
    getOrPut(first) { mutableMapOf() }[second] = distance
    getOrPut(second) { mutableMapOf() }[first] = distance
}

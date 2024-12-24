package com.froren.aoc.solution.y2016.day04

import com.froren.aoc.common.data.toCountingSet
import com.froren.aoc.common.println
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2016,
        day = 4,
        part = 1
    ) {
        input
            .getIdAndHash()
            .filter { (id, hash) ->
                isNotDecoy(id, hash)
            }
            .sumOf {
                it.first.substringAfterLast('-').toInt()
            }

    }

    solve(
        year = 2016,
        day = 4,
        part = 2
    ) {
        input
            .getIdAndHash()
            .filter { (id, hash) ->
                isNotDecoy(id, hash)
            }
            .map {
                val id = it.first.substringAfterLast('-').toInt()

                it.first
                    .substringBeforeLast('-')
                    .map {
                        if (it != '-') it.rotate(id) else ' '
                    }
                    .joinToString("")
                    .also {
                        "$id: $it".println()
                    } to id
            }
            .first {
                it.first == "northpole object storage"
            }.second
    }
}

private fun isNotDecoy(id: String, hash: String) =
    id.asSequence()
        .filter { it in 'a'..'z' }
        .toCountingSet()
        .entries
        .sortedWith(
            compareBy<MutableMap.MutableEntry<Char, Int>>
                { it.value }.reversed().thenBy { it.key }
        )
        .take(5)
        .joinToString("") {
            it.key.toString()
        } == hash

private fun Char.rotate(amount: Int) =
    'a' + ((this + amount - 'a') % ('z' - 'a' + 1))

private fun Sequence<String>.getIdAndHash() =
    map {
        it.split("[").let {
            it[0] to it[1].substringBefore(']')
        }
    }

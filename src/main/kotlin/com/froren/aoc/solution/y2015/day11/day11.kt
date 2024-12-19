package com.froren.aoc.solution.y2015.day11

import com.froren.aoc.common.inputAsLine
import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 11,
        part = 1,
    ) {
        val password = inputAsLine().toCharArray()
        password.increase()

        while(!isValid(password)) {
            password.increase()
        }

        String(password)
    }

    solve(
        year = 2015,
        day = 11,
        part = 2,
    ) {
        val password = inputAsLine().toCharArray()
        password.increase()

        while(!isValid(password)) {
            password.increase()
        }

        password.increase()

        while(!isValid(password)) {
            password.increase()
        }

        String(password)
    }
}

private val forbidden = listOf('i', 'o', 'l')

private fun isValid(password: CharArray): Boolean {
    val pairs = mutableSetOf<Char>()
    var hasStraight = false

    password
        .asSequence()
        .windowed(size = 3, step = 1, partialWindows = true)
        .forEach {
            if (it[0] in forbidden)
                return false

            if (it.size == 1)
                return@forEach

            if (it[0] == it[1]) {
                pairs += it[0]
            }

            if (it.size == 3 && it[0]+1 == it[1] && it[0]+2 == it[2])
                hasStraight = true
        }

    return pairs.size >= 2 && hasStraight
}

private fun CharArray.increase() {
    for (i in indices.reversed()) {
        this[i]++

        if (this[i] <= 'z')
            break
        else
            this[i] = 'a'
    }
}

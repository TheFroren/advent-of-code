package com.froren.aoc.solution.y2015.day20

import com.froren.aoc.common.parse.asSingleLine
import com.froren.aoc.common.solve
import java.math.BigInteger
import kotlin.math.floor
import kotlin.math.sqrt


fun main() {
    solve(
        year = 2015,
        day = 20,
        part = 1,
    ) {
        val presentLimit = input.asSingleLine().toInt()

        for (house in 1..Int.MAX_VALUE) {
            if (presentsPt1(house) > presentLimit)
                return@solve house
        }

        0
    }

    solve(
        year = 2015,
        day = 20,
        part = 2,
    ) {
        val presentLimit = input.asSingleLine().toInt()

        for (house in 1..Int.MAX_VALUE) {
            if (presentsPt2(house) > presentLimit)
                return@solve house
        }

        0
    }
}

private fun presentsPt1(house: Int) =
    divisors(house).sum()*10

private fun presentsPt2(house: Int) =
    divisors(house).filter {
        house / it <= 50
    }.sum()*11

private fun divisors(x: Int): List<Int> {
    val smallDivisors = mutableListOf<Int>()
    val largeDivisors = mutableListOf<Int>()

    for (i in 1..floor(sqrt(x.toDouble())).toInt()) {
        if (x % i == 0)
            smallDivisors.add(i)
    }

    for (i in smallDivisors) {
        if (x == i*i)
            continue

        largeDivisors.add(x / i)
    }

    return smallDivisors + largeDivisors
}

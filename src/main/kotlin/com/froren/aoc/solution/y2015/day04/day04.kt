package com.froren.aoc.solution.y2015.day04

import com.froren.aoc.common.inputAsLine
import com.froren.aoc.common.solve
import java.security.MessageDigest

fun main() {
    solve(
        year = 2015,
        day = 4,
        part = 1
    ) {
        val key = inputAsLine()
        val md5 = MessageDigest.getInstance("MD5")

        for (i in 1..Int.MAX_VALUE) {
            val hash = md5
                .digest("$key$i".toByteArray())
                .joinToString("") { "%02x".format(it) }

            if (hash.substring(0..4) == "00000")
                return@solve i
        }
    }

    solve(
        year = 2015,
        day = 4,
        part = 2
    ) {
        val key = inputAsLine()
        val md5 = MessageDigest.getInstance("MD5")

        for (i in 1..Int.MAX_VALUE) {
            val hash = md5
                .digest("$key$i".toByteArray())
                .joinToString("") { "%02x".format(it) }

            if (hash.substring(0..5) == "000000")
                return@solve i
        }
    }
}

package com.froren.aoc.solution.y2016.day05

import com.froren.aoc.common.parse.asSingleLine
import com.froren.aoc.common.println
import com.froren.aoc.common.solve
import java.security.MessageDigest

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    solve(
        year = 2016,
        day = 5,
        part = 1
    ) {
        val doorId = input.asSingleLine()
        val md5 = MessageDigest.getInstance("MD5")

        (0..Int.MAX_VALUE)
            .asSequence()
            .map {
                md5.digest((doorId+it).toByteArray()).toHexString()
            }
            .filter {
                it.startsWith("00000")
            }
            .take(8)
            .map { it[5] }
            .joinToString("")
    }

    solve(
        year = 2016,
        day = 5,
        part = 2
    ) {
        val doorId = input.asSingleLine()
        val md5 = MessageDigest.getInstance("MD5")
        val indexes = mutableSetOf<Char>()

        (0..Int.MAX_VALUE)
            .asSequence()
            .map {
                md5.digest((doorId+it).toByteArray()).toHexString()
            }
            .filter {
                it.startsWith("00000") && it[5] in '0'..'7'
            }
            .filter { hash ->
                if (hash[5] in indexes)
                    false
                else
                    true.also { indexes.add(hash[5]) }
            }
            .take(8)
            .sortedBy { it[5] }
            .joinToString("") { it[6].toString() }
    }
}



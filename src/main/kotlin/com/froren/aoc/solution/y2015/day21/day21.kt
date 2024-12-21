package com.froren.aoc.solution.y2015.day21

import com.froren.aoc.common.iteration.combinations
import com.froren.aoc.common.solve
import kotlin.math.max


private val WEAPONS = listOf(
    Equipment(8,4,0),
    Equipment(10,5,0),
    Equipment(25,6,0),
    Equipment(40,7,0),
    Equipment(74,8,0),
)

private val ARMOR = listOf(
    Equipment(0,0,0),
    Equipment(13,0,1),
    Equipment(31,0,2),
    Equipment(53,0,3),
    Equipment(75,0,4),
    Equipment(102,0,5),
)

private val RING = listOf(
    Equipment(0,0,0),
    Equipment(0,0,0),
    Equipment(25,1,0),
    Equipment(50,2,0),
    Equipment(100,3,0),
    Equipment(20,0,1),
    Equipment(40,0,2),
    Equipment(80,0,3),
)

fun main() {
    solve(
        year = 2015,
        day = 21,
        part = 1,
    ) {
        val boss = input.getBoss()

        WEAPONS.flatMap { weapon ->
            ARMOR.flatMap { armor ->
                RING.combinations(2).map { rings ->
                    weapon + armor + rings.reduce(Equipment::plus)
                }
            }
        }.filter {
            it.canBeatBoss(100, boss)
        }.minOf {
            it.cost
        }
    }

    solve(
        year = 2015,
        day = 21,
        part = 2,
    ) {
        val boss = input.getBoss()

        WEAPONS.flatMap { weapon ->
            ARMOR.flatMap { armor ->
                RING.combinations(2).map { rings ->
                    weapon + armor + rings.reduce(Equipment::plus)
                }
            }
        }.filter {
            !it.canBeatBoss(100, boss)
        }.maxOf {
            it.cost
        }
    }
}

private fun Equipment.canBeatBoss(hp: Int, boss: Boss) =
    boss.hp.divRoundUp(max(1, damage - boss.armor)) <= hp.divRoundUp(max(1, boss.damage - armor))

private fun Int.divRoundUp(i: Int) =
    this / i + if (this % i == 0) 0 else 1


private data class Equipment(
    val cost: Int,
    val damage: Int,
    val armor: Int,
)

private operator fun Equipment.plus(another: Equipment) =
    Equipment(
        cost = cost + another.cost,
        damage = damage + another.damage,
        armor = armor + another.armor,
    )

private data class Boss(
    val hp: Int,
    val damage: Int,
    val armor: Int,
)

private fun Sequence<String>.getBoss() =
    toList().let {
        Boss(
            it[0].split(": ")[1].toInt(),
            it[1].split(": ")[1].toInt(),
            it[2].split(": ")[1].toInt(),
        )
    }
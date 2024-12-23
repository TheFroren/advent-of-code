package com.froren.aoc.solution.y2015.day22

import kotlin.math.max

data class CombatState(
    val player: PlayerState,
    val boss: BossState,
)

fun CombatState.deepCopy() =
    CombatState(player.copy(), boss.copy())

data class PlayerState(
    var hp: Int,
    var mana: Int,
    var shieldTurns: Int,
    var poisonTurns: Int,
    var rechargeTurns: Int
)

data class BossState(
    var hp: Int,
    var damage: Int,
)

data class Spell(
    val manaCost: Int,
    val predicate: CombatState.() -> Boolean = { true },
    val effect: CombatState.() -> Unit,
)

val SPELLS = listOf(
    Spell(53) {
        boss.hp -= 4
    },
    Spell(73) {
        boss.hp -= 2
        player.hp += 2
    },
    Spell(113, predicate = { player.shieldTurns <= 1 } ) {
        player.shieldTurns = 6
    },
    Spell(173, predicate = { player.poisonTurns <= 1 } ) {
        player.poisonTurns = 6
    },
    Spell(229, predicate = { player.rechargeTurns <= 1 } ) {
        player.rechargeTurns = 5
    },
)


fun CombatState.onStartOfTurn() {
    if (player.shieldTurns > 0) {
        player.shieldTurns--
    }

    if (player.poisonTurns > 0) {
        boss.hp -= 3
        player.poisonTurns--
    }

    if (player.rechargeTurns > 0) {
        player.mana += 101
        player.rechargeTurns--
    }
}

fun CombatState.onBossAttack() {
    player.hp -= max(1, boss.damage - if (player.shieldTurns > 0) 7 else 0)
}

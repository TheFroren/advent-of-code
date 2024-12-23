package com.froren.aoc.solution.y2015.day22

import com.froren.aoc.common.solve

fun main() {
    solve(
        year = 2015,
        day = 21,
        part = 1,
    ) {
        val boss = input.getBoss()

        combatDynamic(
            CombatState(
                player = PlayerState(
                    hp = 50,
                    mana = 500,
                    shieldTurns = 0,
                    poisonTurns = 0,
                    rechargeTurns = 0
                ),
                boss = boss,
            )
        )
    }
}

private fun combatDynamic(initialState: CombatState): Int {
    val memo = mutableMapOf<CombatState, Int>()

    fun bestManaToKill(state: CombatState, manaSpent: Int): Int {
        println("$manaSpent ${state.boss.hp}")

        if (state.boss.hp <= 0)
            return manaSpent

        if (state in memo)
            return memo[state]!!

        var bestManaToKill = Int.MAX_VALUE/2
        var bestState = state

        for (spell in SPELLS) {
            if (state.player.mana < spell.manaCost || !spell.predicate(state))
                continue

            val newState = state.deepCopy()
            newState.player.mana -= spell.manaCost

            spell.effect(newState)
            if (newState.boss.hp > 0)
                newState.onBossAttack()

            if (newState.player.hp <= 0)
                continue

            newState.onStartOfTurn()

            val manaToKill = bestManaToKill(newState, manaSpent) + spell.manaCost
            if (bestManaToKill >= manaToKill) {
                bestManaToKill = manaToKill
                bestState = newState
            }
        }

        memo[bestState] = bestManaToKill
        return bestManaToKill
    }

    return bestManaToKill(initialState, 0)
}

private fun Sequence<String>.getBoss() =
    toList().let {
        BossState(
            it[0].split(": ")[1].toInt(),
            it[1].split(": ")[1].toInt(),
        )
    }

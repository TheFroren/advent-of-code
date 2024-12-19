package com.froren.aoc.solution.y2015.day15

import com.froren.aoc.common.solve
import kotlin.math.max
import kotlin.math.min
import kotlin.time.Duration.Companion.seconds

fun main() {
    solve(
        year = 2015,
        day = 15,
        part = 1,
    ) {
        val ingredients = input.getIngredients().toList()

        sequence {
            for (i in 0..100) {
                for (j in 0..100-i) {
                    for (k in 0..100-i-j) {
                        val l = 100-i-j-k

                        yield(ingredients.scoreAndCalories(i,j,k,l))
                    }
                }
            }
        }
        .maxOf { it.first }
    }

    solve(
        year = 2015,
        day = 15,
        part = 2,
    ) {
        val ingredients = input.getIngredients().toList()

        sequence {
            for (i in 0..100) {
                for (j in 0..100-i) {
                    for (k in 0..100-i-j) {
                        val l = 100-i-j-k

                        yield(ingredients.scoreAndCalories(i,j,k,l))
                    }
                }
            }
        }
        .filter { it.second == 500 }
        .maxOf { it.first }
    }
}

private fun List<Ingredient>.scoreAndCalories(vararg c: Int) =
    (max(0, mapIndexed { i, it -> c[i] * it.capacity }.sum())
    * max(0, mapIndexed { i, it -> c[i] * it.durability }.sum())
    * max(0, mapIndexed { i, it -> c[i] * it.flavor }.sum())
    * max(0, mapIndexed { i, it -> c[i] * it.texture }.sum())
    ) to (mapIndexed { i, it -> c[i] * it.calories }.sum())

private data class Ingredient(
    val capacity: Int,
    val durability: Int,
    val flavor: Int,
    val texture: Int,
    val calories: Int,
)

private fun Sequence<String>.getIngredients() =
    map {
        Regex("""-?\d+""")
            .findAll(it)
            .map { it.value.toInt() }
            .toList()
            .let {
                Ingredient(it[0], it[1], it[2], it[3], it[4])
            }
    }
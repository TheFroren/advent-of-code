package com.froren.aoc.common

typealias Solution<P, R> = SolutionRunner<P>.() -> R

class SolutionRunner<P>(
    val input: Sequence<String>,
    val params: P?,
)

fun <P, R> solve(
    inputSrc: String,
    params: P? = null,
    solution: Solution<P, R>,
): R =
    loadResource(inputSrc)
        .reader()
        .useLines { input ->
            SolutionRunner(input, params).solution()
        }

fun <P, R> solve(
    year: Int,
    day: Int,
    part: Int,
    params: P? = null,
    solution: Solution<P, R>,
) =
    solve(
        inputSrc = "y$year/day${day.toString().padStart(2, '0')}input.txt",
        params,
        solution,
    ).also {
        println("Year $year day $day part $part result is $it")
    }

fun <R> solve(
    year: Int,
    day: Int,
    part: Int,
    solution: Solution<*, R>,
) = solve(
    year = year,
    day = day,
    part = part,
    params = null,
    solution = solution,
)

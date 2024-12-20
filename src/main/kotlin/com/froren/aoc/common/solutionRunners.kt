package com.froren.aoc.common

import kotlin.time.measureTime
import kotlin.time.measureTimedValue

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
) = measureTimedValue {
    solve(
        inputSrc = "/y$year/day${day.toString().padStart(2, '0')}input.txt",
        params,
        solution,
    )
}.let { (result, time) ->
    println("Year $year day $day part $part result is $result. Finished in $time")
    result
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

fun SolutionRunner<*>.inputAsLine() =
    input.joinToString("")

package com.froren.aoc.common.iteration

fun <T> Pair<T, T>.asSequence() = sequence {
    yield(first)
    yield(second)
}

fun <T> Iterable<IndexedValue<T>>.asValueSequence() =
    this.asSequence().map { it.value }
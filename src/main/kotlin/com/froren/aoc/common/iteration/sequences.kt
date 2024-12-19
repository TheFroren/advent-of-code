package com.froren.aoc.common.iteration

fun <T> Pair<T, T>.asSequence() = sequence {
    yield(first)
    yield(second)
}

fun <T> Iterable<IndexedValue<T>>.asValueSequence() =
    this.asSequence().map { it.value }

/**
 * ChatGPT claims that this is Heap's algorithm.
 */
fun <T> Collection<T>.permutations() = sequence {
    if (isEmpty())
        yield(emptyList())

    val currentPermutation = toMutableList()
    val stack = IntArray(size) { 0 }

    yield(currentPermutation.toList())

    var i = 0
    while (i < size) {
        if (stack[i] < i) {
            if (i % 2 == 0) {
                currentPermutation.swap(0, i)
            } else {
                currentPermutation.swap(stack[i], i)
            }

            yield(currentPermutation.toList())

            stack[i]++
            i = 0
        } else {
            stack[i] = 0
            i++
        }
    }
}

private fun <T> MutableList<T>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

fun <K,V : Any> Sequence<Pair<K,V>>.mergeToMap(merge: (V, V) -> V): Map<K,V> {
    val map = mutableMapOf<K,V>()
    forEach {
        map.merge(it.first, it.second, merge)
    }
    return map
}

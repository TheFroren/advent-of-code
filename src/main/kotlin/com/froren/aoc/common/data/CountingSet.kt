package com.froren.aoc.common.data

class CountingSet<V>: MutableMap<V,Int> by mutableMapOf() {
    fun add(element: V) = addMultiple(element, 1)

    fun addMultiple(element: V, toAdd: Int) {
        if (toAdd < 0)
            removeMultiple(element, toAdd * -1)

        this[element] = (this[element] ?: 0) + 1
    }

    fun removeOne(element: V) = removeMultiple(element, 1)

    fun removeMultiple(element: V, toRemove: Int) {
        val count = this[element] ?: 0

        if (count > toRemove) {
            this[element] = count - toRemove
        } else {
            this.remove(element)
        }
    }

    fun sizeWithRepetitions(): Int {
        return values.sum()
    }

    fun uniqueElements(): Set<V> {
        return keys
    }

    override fun toString(): String {
        return "CountingSet(${this.entries.joinToString { "${it.key}=${it.value}" }})"
    }
}

fun <T> Iterable<T>.toCountingSet(): CountingSet<T> {
    val set = CountingSet<T>()
    forEach {
        set.add(it)
    }
    return set
}

fun <T> Sequence<T>.toCountingSet(): CountingSet<T> {
    val set = CountingSet<T>()
    forEach {
        set.add(it)
    }
    return set
}
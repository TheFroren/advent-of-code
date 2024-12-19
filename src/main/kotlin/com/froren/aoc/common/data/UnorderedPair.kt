package com.froren.aoc.common.data

data class UnorderedPair<T>(val first: T, val second: T) {
    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        if (other !is UnorderedPair<*>)
            return false

        return (first == other.first && second == other.second) ||
                (first == other.second && second == other.first)
    }

    override fun hashCode(): Int {
        return first.hashCode() + second.hashCode()
    }
}

infix fun <T> T.with(another: T) =
     UnorderedPair(this, another)

fun <T> UnorderedPair<T>.asSequence() = sequence {
    yield(first)
    yield(second)
}
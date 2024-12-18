package com.froren.aoc.common.grid

data class Area (
    val xBounds: IntRange,
    val yBounds: IntRange,
) {

    constructor(width: Int, height: Int):
            this(0..<width, 0..<height)

    val width = xBounds.last - xBounds.first + 1
    val height = yBounds.last - yBounds.first + 1
}

fun Area.forEachCoordinate(op: (Int,Int) -> Unit) {
    for (y in yBounds) {
        for (x in xBounds) {
            op(x, y)
        }
    }
}

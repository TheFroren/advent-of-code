package com.froren.aoc.common.grid

data class Vector(
    val x: Int,
    val y: Int,
)

operator fun Vector.plus(other: Vector) =
    Vector(x+other.x, y+other.y)

operator fun Vector.minus(other: Vector) =
    Vector(x-other.x, y-other.y)

operator fun Vector.times(factor: Int) =
    Vector(x*factor, y*factor)
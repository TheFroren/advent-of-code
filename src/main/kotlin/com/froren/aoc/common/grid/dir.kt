package com.froren.aoc.common.grid

enum class Dir(x: Int, y: Int) {
    N(0,-1),
    E(1,0),
    S(0,1),
    W(-1,0);

    val v = Vector(x,y)
}

fun Dir.turnCW(amount: Int) =
    Dir.entries[(this.ordinal + (amount % 4)) % 4]

operator fun Vector.plus(dir: Dir) =
    this + dir.v
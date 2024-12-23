package com.froren.aoc.common.grid

enum class Dir(x: Int, y: Int) {
    N(0,-1),
    E(1,0),
    S(0,1),
    W(-1,0);

    val v = Vector(x,y)
}

fun Dir.turnCW(amount: Int) =
    Dir.entries[(this.ordinal + (Math.floorMod(amount, 4))) % 4]

operator fun Vector.plus(dir: Dir) =
    this + dir.v

enum class Dir8(x: Int, y: Int) {
    N(0,-1),
    NE(1,-1),
    E(1,0),
    SE(1,1),
    S(0,1),
    SW(-1,1),
    W(-1,0),
    NW(-1,-1);

    val v = Vector(x,y)
}

operator fun Vector.plus(dir: Dir8) =
    this + dir.v

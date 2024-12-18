package com.froren.aoc.common

fun loadResource(path: String) =
    object{}.javaClass.getResourceAsStream(path)!!

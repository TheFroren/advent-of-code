package com.froren.aoc.solution.y2015.day07

import com.froren.aoc.common.solve

private val CONST = Regex("""\d+""")
private val REF = Regex("""\w+""")
private val NOT = Regex("""NOT (.+)""")
private val BINARY = Regex("""(.+) (\w+) (.+)""")
private const val TWO_TO_16 = 1 shl 16

fun main() {
    solve(
        year = 2015,
        day = 7,
        part = 1
    ) {
        input
            .getConnections()
            .toMap(mutableMapOf())
            .let { refs ->
                (refs["a"]!!)(refs)
            }

    }

    solve(
        year = 2015,
        day = 7,
        part = 2
    ) {
        input
            .getConnections()
            .toMap(mutableMapOf())
            .let { refs ->
                refs["b"] = constExpr(3176)
                (refs["a"]!!)(refs)
            }

    }
}

fun Sequence<String>.getConnections() =
    mapNotNull { commandString ->
        commandString.split(" -> ").let { parts ->
            val expr = parseConstOrRef(parts[0])
                ?: NOT.matchEntire(parts[0])?.let {
                    unaryExpr(parseConstOrRef(it.groupValues[1])!!) { a -> a.inv() }
                } ?: BINARY.matchEntire(parts[0])?.let {
                    val aExpr = parseConstOrRef(it.groupValues[1])!!
                    val bExpr = parseConstOrRef(it.groupValues[3])!!

                    when(it.groupValues[2]) {
                        "AND" -> binaryExpr(aExpr,bExpr) { a, b -> a and b }
                        "OR" -> binaryExpr(aExpr,bExpr) { a, b -> a or b }
                        "LSHIFT" -> binaryExpr(aExpr,bExpr) { a, b -> a shl b }
                        "RSHIFT" -> binaryExpr(aExpr,bExpr) { a, b -> a shr b }
                        else -> constExpr(0)
                    }
                } ?: constExpr(0)

            parts[1] to expr
        }
    }

fun parseConstOrRef(src: String): Expr? =
    CONST.matchEntire(src)?.let {
        constExpr(it.value.toInt())
    } ?: REF.matchEntire(src)?.let {
        refExpr(it.value)
    }

interface Expr: (MutableMap<String, Expr>) -> Int

fun constExpr(value: Int) = object: Expr {
    override fun invoke(refs: MutableMap<String, Expr>) = value
}

fun refExpr(ref: String) = object: Expr {
    override fun invoke(refs: MutableMap<String, Expr>) =
        ((refs[ref]?.invoke(refs) ?: 0) % TWO_TO_16)
            .also { refs[ref] = constExpr(it) }
}

fun unaryExpr(arg: Expr, op: (Int) -> Int) = object: Expr {
    override fun invoke(refs: MutableMap<String, Expr>) =
        op(arg.invoke(refs)) % TWO_TO_16
}

fun binaryExpr(arg1: Expr, arg2: Expr, op: (Int, Int) -> Int) = object: Expr {
    override fun invoke(refs: MutableMap<String, Expr>) =
        op(arg1.invoke(refs), arg2.invoke(refs)) % TWO_TO_16
}

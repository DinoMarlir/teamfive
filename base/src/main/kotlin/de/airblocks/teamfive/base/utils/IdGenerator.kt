package de.airblocks.teamfive.base.utils

fun generateId(length: Int): String {
    val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..length)
        .map { charPool.random() }
        .joinToString("")
}
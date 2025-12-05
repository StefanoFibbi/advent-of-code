package adventofcode.utils

val Int.isNegative: Boolean
    get() = this < 0

val Int.isPositive: Boolean
    get() = this >= 0

fun String.countOccurrencesOf(pattern: String): Int =
    Regex(Regex.escape(pattern))
        .findAll(this)
        .count()
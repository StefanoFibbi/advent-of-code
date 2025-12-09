package adventofcode.utils

val Int.isNegative: Boolean
    get() = this < 0

val Int.isPositive: Boolean
    get() = this >= 0

fun String.countOccurrencesOf(pattern: String): Int =
    Regex(Regex.escape(pattern))
        .findAll(this)
        .count()

fun <T> List<List<T>>.transpose(): List<List<T>> =
    (0 until first().size).map { col ->
        map { row -> row[col] }
    }

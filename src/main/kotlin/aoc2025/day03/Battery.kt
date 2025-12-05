package aoc2025.day03

typealias Joltage = Long

data class Battery(
    val joltage: Joltage
) {
    operator fun compareTo(other: Battery): Int = this.joltage.compareTo(other.joltage)

    infix fun hasHigherJoltageThan(other: Battery): Boolean =
        this > other

    companion object {
        const val MIN_JOLTAGE = 1
        const val MAX_JOLTAGE = 9
    }
}

fun Long.toBattery() = Battery(joltage = this)
fun Int.toBattery() = Battery(joltage = this.toLong())

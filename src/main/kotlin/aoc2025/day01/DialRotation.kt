package aoc2025.day01

sealed class DialRotation {
    abstract val rotatedNumber: Int

    data class Left(override val rotatedNumber: Int) : DialRotation()
    data class Right(override val rotatedNumber: Int) : DialRotation()

    companion object {
        fun from(rotationString: String): DialRotation {
            val direction = rotationString.first()
            val number = rotationString.substring(1).toInt()
            return when (direction) {
                'L' -> Left(number)
                'R' -> Right(number)
                else -> throw IllegalArgumentException("Invalid direction: $direction")
            }
        }
    }
}
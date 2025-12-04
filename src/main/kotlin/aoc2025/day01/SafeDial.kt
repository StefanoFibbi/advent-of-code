package aoc2025.day01

typealias ZeroTicksCount = Int

data class SafeDial(
    val number: Int = DEFAULT_NUMBER,
    val zeroTickCount: ZeroTicksCount = 0
) {
    fun rotate(
        rotation: DialRotation,
        zeroCountStrategy: ZeroTickCountStrategy,
    ): SafeDial {
        val rotatedNumber = when (rotation) {
            is DialRotation.Right -> (number + rotation.rotatedNumber) % DIAL_NUMBERS
            is DialRotation.Left -> {
                val newNumberCandidate = number - rotation.rotatedNumber
                ((newNumberCandidate % DIAL_NUMBERS) + DIAL_NUMBERS) % DIAL_NUMBERS
            }
        }

        val currentRotationZeroCount = when (zeroCountStrategy) {
            ZeroTickCountStrategy.ENDS_AT_ZERO -> if (rotatedNumber == 0) 1 else 0
            ZeroTickCountStrategy.EACH_TIME_ZERO_IS_REACHED -> {
                val oneOrManyFullSpins = rotation.rotatedNumber % DIAL_NUMBERS == 0
                val fullSpinCrossZeroNum = rotation.rotatedNumber / DIAL_NUMBERS

                when {
                    // Only full spins happened (e.g. 100, 200, 300, ...)
                    oneOrManyFullSpins -> fullSpinCrossZeroNum

                    // Last (or only) spin was incomplete, and dial number is 0
                    rotatedNumber == 0 -> fullSpinCrossZeroNum + 1

                    // Last (or only) spin is incomplete, dial number not 0 -> Check if 0 is crossed during last spin
                    rotatedNumber != 0 -> when (rotation) {
                        is DialRotation.Right -> {
                            val lastCrossingZero = if (rotatedNumber < number) 1 else 0
                            fullSpinCrossZeroNum + lastCrossingZero
                        }
                        is DialRotation.Left -> {
                            val lastCrossingZero = if (number in 1..<rotatedNumber) 1 else 0
                            fullSpinCrossZeroNum + lastCrossingZero
                        }
                    }

                    else -> throw IllegalStateException("Unexpected state reached when computing zero crossed times")
                }
            }
        }

        return SafeDial(
            number = rotatedNumber,
            zeroTickCount = zeroTickCount + currentRotationZeroCount
        )
    }

    companion object {
        const val DEFAULT_NUMBER = 50
        const val DIAL_NUMBERS = 100
    }

    enum class ZeroTickCountStrategy {
        ENDS_AT_ZERO,
        EACH_TIME_ZERO_IS_REACHED,
    }
}
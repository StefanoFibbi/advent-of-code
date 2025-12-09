package aoc2025.day06

class CephalopodsPuzzleParser(val source: List<String>) {
    private val matrixNumbers: List<CharArray> =
        source
            .take(source.size - 1)
            .map { it.toCharArray() }

    fun parseProblems(): List<CephalopodsProblemNumbers> {
        val maxColumnsNumber = source.maxOfOrNull { it.length } ?: 0

        tailrec fun buildCephalopodsNumbers(
            lastProblemBlockSeparatorIndex: Int,
            cephalopodsNumbers: List<CephalopodsProblemNumbers>,
        ): List<List<Int>> {
            if (lastProblemBlockSeparatorIndex >= maxColumnsNumber) return cephalopodsNumbers

            val (problemNumbers, endProblemBlockIndex) = takeSingleProblemNumbersFrom(lastProblemBlockSeparatorIndex)
            return buildCephalopodsNumbers(
                lastProblemBlockSeparatorIndex = endProblemBlockIndex + 1,
                cephalopodsNumbers = cephalopodsNumbers + listOf(problemNumbers)
            )
        }

        return buildCephalopodsNumbers(
            lastProblemBlockSeparatorIndex = 0,
            cephalopodsNumbers = emptyList()
        )
    }

    private tailrec fun takeSingleProblemNumbersFrom(
        index: Int,
        problemNumbers: CephalopodsProblemNumbers = emptyList()
    ): Pair<CephalopodsProblemNumbers, Int> = when (val number = getNumber(numberAt = index)) {
        null -> Pair(problemNumbers, index)
        else -> takeSingleProblemNumbersFrom(
            index = index + 1,
            problemNumbers = problemNumbers + number
        )
    }

    private fun getNumber(numberAt: Int): Int? =
        matrixNumbers
            .map { sourceLine -> if (numberAt < sourceLine.size) sourceLine[numberAt] else "" }
            .joinToString("")
            .replace(oldValue = " ", newValue = "")
            .takeIf { it.isNotEmpty() }
            ?.toInt()
}
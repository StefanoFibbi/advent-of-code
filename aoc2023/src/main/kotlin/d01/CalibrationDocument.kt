package d01

data class CalibrationDocument(
    val lines: Collection<String>
) {
    fun calibrationValues(matchingFn: (line: String) -> Collection<Int>): Collection<Int> =
        lines
            .map(matchingFn)
            .map { numbers -> "${numbers.first()}${numbers.last()}".toInt() }

    companion object {
        fun numMatches(line: String): Collection<Int> =
            Regex("\\d")
                .findAll(line)
                .toList()
                .map { stringDigitToInt(it.value) }

        fun wordsAndNumMatches(line: String): Collection<Int> {
            val matchingRules = Num.entries.map { Regex(it.name.lowercase()) } + Regex("\\d")
            return matchingRules
                .fold(emptyList()) { acc: Collection<MatchResult>, matchingRule: Regex ->
                    acc + matchingRule.findAll(line).toList()
                }.sortedBy {
                    it.range.first
                }.map {
                    stringDigitToInt(it.value)
                }
        }

        private fun stringDigitToInt(value: String): Int =
            if (value.length == 1) value.toInt()
            else Num.valueOf(value.uppercase()).ordinal
    }

    enum class Num {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE
    }
}

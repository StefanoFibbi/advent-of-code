package aoc2025.day03

data class BatteryBank(
    val batteries: List<Battery>
) {
    fun findLargestJoltage(batteriesNumberToUse: Int): Joltage {
        require(batteriesNumberToUse <= batteries.size) { "Hey! There are not enough batteries in the bank!" }

        tailrec fun pickBatteries(
            remainingBatteriesCount: Int,
            availableBatteriesToPick: List<Battery>,
            batteriesToUse: List<Battery>,
        ): List<Battery> = when {
            // All batteries to use have been selected
            batteriesToUse.size == batteriesNumberToUse -> batteriesToUse

            // There's no battery that can be discarded in order to use exactly batteriesNumberToUse batteries
            availableBatteriesToPick.size + batteriesToUse.size == batteriesNumberToUse -> batteriesToUse + availableBatteriesToPick

            // Find the next highest battery
            else -> {
                var candidateJoltageIdx: Int = 0
                for (joltageCandidate in Battery.MAX_JOLTAGE downTo Battery.MIN_JOLTAGE) {
                    candidateJoltageIdx =
                        availableBatteriesToPick.indexOfFirst { it.joltage == joltageCandidate.toLong() }

                    if (candidateJoltageIdx >= 0 && candidateJoltageIdx <= availableBatteriesToPick.size - remainingBatteriesCount) break
                }

                pickBatteries(
                    remainingBatteriesCount = remainingBatteriesCount - 1,
                    availableBatteriesToPick = availableBatteriesToPick.drop(candidateJoltageIdx + 1),
                    batteriesToUse = batteriesToUse + availableBatteriesToPick[candidateJoltageIdx]
                )
            }
        }

        val batteries = pickBatteries(
            remainingBatteriesCount = batteriesNumberToUse,
            availableBatteriesToPick = batteries,
            batteriesToUse = emptyList(),
        )
        return batteries.joinToString("") { it.joltage.toString() }.toLong()
    }

    companion object {
        fun from(batteryBankStr: String): BatteryBank =
            batteryBankStr
                .toCharArray()
                .map { it.digitToInt().toBattery() }
                .let { BatteryBank(batteries = it) }
    }
}
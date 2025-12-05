package aoc2025.day02

import adventofcode.utils.countOccurrencesOf

typealias RepeatedSequenceCount = Int

object ProductRangeAnalyser {
    fun findDuplicateSequenceIdsIn(range: ProductIdRange): Set<ProductId> =
        ((range.min.value)..(range.max.value))
            .map { it.toString() }
            .filterNot { it.length % 2 != 0 }
            .filter { productIdStr ->
                val half1 = productIdStr.take(productIdStr.length / 2)
                val half2 = productIdStr.substring(productIdStr.length / 2)
                half1 == half2
            }
            .map { it.toProductId() }
            .toSet()

    fun findAtLeastTwiceAppearingSequenceIn(range: ProductIdRange): Map<ProductId, RepeatedSequenceCount> =
        ((range.min.value)..(range.max.value))
            .map { it.toString() }
            .mapNotNull { productIdString ->
                countRepeatedSequences(productIdString)
                    .takeIf { it > 0 }
                    ?.let { Pair(productIdString.toProductId(), it) }
            }
            .toMap()

    fun countRepeatedSequences(productIdString: String): Int {
        // No pattern possible
        if (productIdString.isEmpty() || productIdString.length == 1) return 0

        // Duplicated pattern only in case the 2 digits are the same
        if (productIdString.length == 2) return 1.takeIf { productIdString.first() == productIdString.last() } ?: 0

        /* All numbers are equals (e.g. 11, 22, 33333, ...) */
        val firstCharOccurrences = productIdString.count { it == productIdString.first() }
        if (firstCharOccurrences == productIdString.length) return firstCharOccurrences

        // Find fancy patterns (e.g. 123123, 1212, ...)
        (1 ..< productIdString.length / 2 ).forEach { patternLength ->
            val candidatePattern = productIdString.take(patternLength + 1)
            val repetitionsCount = productIdString.countOccurrencesOf(candidatePattern)
            if (repetitionsCount * candidatePattern.length == productIdString.length) return repetitionsCount
        }

        return 0
    }
}

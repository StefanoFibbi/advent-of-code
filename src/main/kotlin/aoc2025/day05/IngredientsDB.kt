package aoc2025.day05

data class IngredientsDB(
    val freshIngredientIdRanges: List<FreshIdRange>,
    val availableIngredients: Set<IngredientId>,
) {
    val aggregatedFreshIngredientIdRanges: List<FreshIdRange>

    init {
        aggregatedFreshIngredientIdRanges = mergeOverlaps()
    }

    fun countAllPossibleFreshIngredientIds(): Long {
        var count = 0L
        for (range in aggregatedFreshIngredientIdRanges) {
            count += range.ingredientIdsNumberInRange
        }
        return count
    }

    fun getFreshIngredientIds(): List<IngredientId> =
        availableIngredients
            .filter { ingredientId ->
                aggregatedFreshIngredientIdRanges.firstOrNull { ingredientId isIn it } != null
            }

    private fun mergeOverlaps(): List<FreshIdRange> {
        val sortedRanges = freshIngredientIdRanges.sortedBy{ it.minIngredientId.value }

        tailrec fun mergeOverlappingRanges(
            rangeIdx: Int,
            candidateRange: FreshIdRange,
            aggregatedRanges: List<FreshIdRange>,
        ): List<FreshIdRange> {
            // All ranges checked, process done
            if (rangeIdx == sortedRanges.size) return when {
                aggregatedRanges.last() == candidateRange -> aggregatedRanges
                else -> aggregatedRanges + candidateRange
            }

            val currentRange = sortedRanges[rangeIdx]
            return when {
                // No overlap -> No need to merge
                candidateRange.maxIngredientId < currentRange.minIngredientId -> mergeOverlappingRanges(
                    rangeIdx = rangeIdx + 1,
                    candidateRange = currentRange,
                    aggregatedRanges = aggregatedRanges + candidateRange
                )

                else -> mergeOverlappingRanges(
                    rangeIdx = rangeIdx + 1,
                    candidateRange = candidateRange.copy(
                        maxIngredientId = maxOf(
                            currentRange.maxIngredientId.value,
                            candidateRange.maxIngredientId.value
                        ).toIngredientId(),
                    ),
                    aggregatedRanges = aggregatedRanges
                )
            }
        }

        return mergeOverlappingRanges(
            rangeIdx = 1,
            candidateRange = sortedRanges.first(),
            aggregatedRanges = emptyList(),
        )
    }
}
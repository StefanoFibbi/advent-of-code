package aoc2025.day05

object Day05PuzzleParser {
    fun buildIngredientsDB(source: List<String>): IngredientsDB {
        val emptyLineIdx = source.indexOfFirst { it.isEmpty() }
        val freshIngredientsRangeId =
            source
                .take(emptyLineIdx)
                .map { rangeStr ->
                    val regex = Regex("""(\d+)-(\d+)""")
                    val match = regex.find(rangeStr) ?: error("Invalid range format: $rangeStr")
                    val (minStr, maxStr) = match.destructured
                    FreshIdRange(
                        minIngredientId = minStr.toIngredientId(),
                        maxIngredientId = maxStr.toIngredientId(),
                    )
                }
        val ingredientsId =
            source
                .subList(emptyLineIdx + 1, source.size)
                .map { it.toIngredientId() }
                .toSet()

        return IngredientsDB(
            freshIngredientIdRanges = freshIngredientsRangeId,
            availableIngredients = ingredientsId
        )
    }
}
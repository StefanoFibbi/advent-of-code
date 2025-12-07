package aoc2025.day05

data class FreshIdRange(
    val minIngredientId: IngredientId,
    val maxIngredientId: IngredientId,
) {
    val ingredientIdsNumberInRange: Long
        get() = maxIngredientId.value - minIngredientId.value + 1
}

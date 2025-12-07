package aoc2025.day05

@JvmInline
value class IngredientId(val value: Long) {
    operator fun compareTo(other: IngredientId): Int = this.value.compareTo(other.value)

    infix fun isIn(range: FreshIdRange): Boolean =
        this >= range.minIngredientId && this <= range.maxIngredientId
}

fun Long.toIngredientId() = IngredientId(this)
fun Int.toIngredientId() = this.toLong().toIngredientId()
fun String.toIngredientId() = this.toLong().toIngredientId()
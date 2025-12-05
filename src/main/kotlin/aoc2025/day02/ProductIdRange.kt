package aoc2025.day02

data class ProductIdRange(
    val min: ProductId,
    val max: ProductId,
) {
    companion object {
        fun from(rangeString: String): ProductIdRange {
            val regex = Regex("""(\d+)-(\d+)""")
            val match = regex.find(rangeString) ?: error("Invalid range format: $rangeString")
            val (minStr, maxStr) = match.destructured

            return ProductIdRange(
                min = minStr.toProductId(),
                max = maxStr.toProductId(),
            )
        }
    }
}

@JvmInline
value class ProductId(val value: Long)

fun Long.toProductId() = ProductId(this)
fun String.toProductId() = this.toLong().toProductId()
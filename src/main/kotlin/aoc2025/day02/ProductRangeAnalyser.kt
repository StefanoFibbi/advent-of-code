package aoc2025.day02

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
}

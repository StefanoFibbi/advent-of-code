package aoc2025.day02

import utils.FileUtils

class AdventOfCode25Day02 {
    fun part1(path: String): Long {
        val ranges =
            FileUtils
                .parseToList(path)
                .first()
                .split(",")
                .map(ProductIdRange.Companion::from)

        val invalidProductIds = ranges.flatMap(ProductRangeAnalyser::findDuplicateSequenceIdsIn)
        return invalidProductIds.sumOf { it.value }
    }
}

fun main() {
    val solution = AdventOfCode25Day02()
    val exampleP1 = solution.part1("aoc2025/day02/example")
    val solutionP1 = solution.part1("aoc2025/day02/puzzle-input-1")

    println("Day 02, part 1 (example): $exampleP1")
    println("Day 02, part 1 (solution): $solutionP1")
}

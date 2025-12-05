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

    fun part2(path: String): Long {
        val ranges =
            FileUtils
                .parseToList(path)
                .first()
                .split(",")
                .map(ProductIdRange.Companion::from)

        return ranges
            .map(ProductRangeAnalyser::findAtLeastTwiceAppearingSequenceIn)
            .sumOf { duplicateProductIdsInRangeMap ->
                duplicateProductIdsInRangeMap.keys.sumOf { it.value }
            }
    }
}

fun main() {
    val solution = AdventOfCode25Day02()
    val exampleP1 = solution.part1("aoc2025/day02/example")
    val solutionP1 = solution.part1("aoc2025/day02/puzzle-input-1")
    val exampleP2 = solution.part2("aoc2025/day02/example")
    val solutionP2 = solution.part2("aoc2025/day02/puzzle-input-1")

    println("Day 02, part 1 (example): $exampleP1")
    println("Day 02, part 1 (solution): $solutionP1")
    println("Day 02, part 2 (example): $exampleP2")
    println("Day 02, part 2 (solution): $solutionP2")
}

package aoc2025.day04

import utils.FileUtils

class AdventOfCode25Day04 {
    fun part1(path: String): Int {
        val matrix =
            FileUtils
                .parseToList(path)
                .let { PaperGrid.from(it) }

        return matrix.markPaperRolls()
    }

    fun part2(path: String): Int {
        val matrix =
            FileUtils
                .parseToList(path)
                .let { PaperGrid.from(it) }

        return matrix.removeAllPossiblePaperRolls(totalRemovedPaperRollsNumber = 0)
    }
}

fun main() {
    val solution = AdventOfCode25Day04()

    val exampleP1 = solution.part1("aoc2025/day04/example")
    val solutionP1 = solution.part1("aoc2025/day04/puzzle-input-1")
    val exampleP2 = solution.part2("aoc2025/day04/example")
    val solutionP2 = solution.part2("aoc2025/day04/puzzle-input-1")


    println("Day 03, part 1 (example): $exampleP1")
    println("Day 03, part 1 (solution): $solutionP1")
    println("Day 03, part 2 (example): $exampleP2")
    println("Day 03, part 2 (solution): $solutionP2")
}


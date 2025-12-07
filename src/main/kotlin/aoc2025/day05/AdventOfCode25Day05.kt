package aoc2025.day05

import utils.FileUtils

class AdventOfCode25Day05 {
    fun part1(path: String): Long {
        val db =
            FileUtils
                .parseToList(path)
                .let { Day05PuzzleParser.buildIngredientsDB(it) }

        return db.getFreshIngredientIds().count().toLong()
    }

    fun part2(path: String): Long {
        val db =
            FileUtils
                .parseToList(path)
                .let { Day05PuzzleParser.buildIngredientsDB(it) }

        return db.countAllPossibleFreshIngredientIds()
    }
}

fun main() {
    val solution = AdventOfCode25Day05()

    val exampleP1 = solution.part1("aoc2025/day05/example")
    val solutionP1 = solution.part1("aoc2025/day05/puzzle-input-1")
    val exampleP2 = solution.part2("aoc2025/day05/example")
    val solutionP2 = solution.part2("aoc2025/day05/puzzle-input-1")

    println("Day 05, part 1 (example): $exampleP1")
    println("Day 05, part 1 (solution): $solutionP1")
    println("Day 05, part 2 (example): $exampleP2")
    println("Day 05, part 2 (solution): $solutionP2")
}
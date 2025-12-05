package aoc2025.day03

import utils.FileUtils

class AdventOfCode25Day03 {
    fun part1(path: String): Joltage {
        val batteriesBanksCollection =
            FileUtils
                .parseToList(path)
                .map(BatteryBank::from)

        return batteriesBanksCollection.sumOf { it.findLargestJoltage(batteriesNumberToUse = 2) }
    }

    fun part2(path: String): Joltage {
        val batteriesBanksCollection =
            FileUtils
                .parseToList(path)
                .map(BatteryBank::from)

        return batteriesBanksCollection.sumOf { it.findLargestJoltage(batteriesNumberToUse = 12) }
    }
}

fun main() {
    val solution = AdventOfCode25Day03()
    val exampleP1 = solution.part1("aoc2025/day03/example")
    val solutionP1 = solution.part1("aoc2025/day03/puzzle-input-1")
    val exampleP2 = solution.part2("aoc2025/day03/example")
    val solutionP2 = solution.part2("aoc2025/day03/puzzle-input-1")


    println("Day 03, part 1 (example): $exampleP1")
    println("Day 03, part 1 (solution): $solutionP1")
    println("Day 03, part 2 (example): $exampleP2")
    println("Day 03, part 2 (solution): $solutionP2")
}

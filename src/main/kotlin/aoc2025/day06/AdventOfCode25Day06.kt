package aoc2025.day06

import adventofcode.utils.transpose
import utils.FileUtils

typealias CephalopodsProblemNumbers = List<Int>

class AdventOfCode25Day06 {
    fun part1(path: String): Long {
        val homeworks =
            FileUtils
                .parseToList(path)
                .let {
                    Day06PuzzleParser.parseHomeworks(
                        source = it,
                        parseNumbersFn = Day06PuzzleParser::parseNumbers
                    )
                }

        return homeworks.problems.sumOf { it.solve() }
    }

    fun part2(path: String): Long {
        val homeworks =
            FileUtils
                .parseToList(path)
                .let {
                    Day06PuzzleParser.parseHomeworks(
                        source = it,
                        parseNumbersFn = Day06PuzzleParser::parseNumbersLikeACephalopods
                    )
                }

        return homeworks.problems.sumOf { it.solve() }
    }
}

fun main() {
    val solution = AdventOfCode25Day06()

    solution.part1("aoc2025/day06/puzzle-input-1")

    val exampleP1 = solution.part1("aoc2025/day06/example").also {
        require(it == 4277556L) { "Invalid result for Part1 (example)" }
    }
    val solutionP1 = solution.part1("aoc2025/day06/puzzle-input-1").also {
        require(it == 4076006202939) { "Invalid result for Part1 (solution)" }
    }
    val exampleP2 = solution.part2("aoc2025/day06/example").also {
        require(it == 3263827L) { "Invalid result for Part2 (example)" }
    }
    val solutionP2 = solution.part2("aoc2025/day06/puzzle-input-1").also {
        require(it == 7903168391557) { "Invalid result for Part2 (example)" }
    }

    println("Day 06, part 1 (example): $exampleP1")
    println("Day 06, part 1 (solution): $solutionP1")
    println("Day 06, part 2 (example): $exampleP2")
    println("Day 06, part 2 (solution): $solutionP2")
}

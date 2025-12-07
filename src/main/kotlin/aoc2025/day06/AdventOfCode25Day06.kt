package aoc2025.day06

import utils.FileUtils

class AdventOfCode25Day06 {
    fun part1(path: String): Long {
        val homeworks =
            FileUtils
                .parseToList(path)
                .let(Day06PuzzleParser::parseHomeworks)

        return homeworks.problems.sumOf { it.solve() }
    }
}

object Day06PuzzleParser {
    fun parseHomeworks(source: List<String>): Homeworks {
        val numbersRegex = Regex("""\d+""")
        val numbers =
            source
                .take(source.size - 1)
                .map { line ->
                    numbersRegex
                        .findAll(line)
                        .map { it.value.toInt() }
                        .toList()
                }

        val operationsRegex = Regex("""[*+]""")
        val operations =
            source
                .last()
                .let { line ->
                    operationsRegex
                        .findAll(line)
                        .map {
                            when(it.value) {
                                "+" -> CephalopodProblemOperation.Sum
                                "*" -> CephalopodProblemOperation.Multiply
                                else -> throw IllegalArgumentException("Unexpected operation: ${it.value}")
                            }
                        }
                        .toList()
                }

        // Validate all numbers input has same size
        if (numbers.map { it.size }.distinct().size != 1) throw IllegalStateException("Error while parsing numbers: number of problems mismatch")

        // Validate operations are matching problems number
        if (operations.size != numbers.first().size) throw IllegalStateException("Error while parsing numbers: problems / operations mismatch")

        return buildHomeworks(
            problemIndex = 0,
            problemsNumber = numbers.first().size,
            numbers = numbers,
            operations = operations,
            problems = emptyList()
        )
    }

    private tailrec fun buildHomeworks(
        problemIndex: Int,
        problemsNumber: Int,
        numbers: List<List<Int>>,
        operations: List<CephalopodProblemOperation>,
        problems: List<CephalopodProblem>,
    ): Homeworks {
        if (problemIndex == problemsNumber) return Homeworks(problems)

        val problem = CephalopodProblem(
            numbers = numbers.map { it[problemIndex] },
            operation = operations[problemIndex]
        )

        return buildHomeworks(
            problemIndex = problemIndex + 1,
            problemsNumber = problemsNumber,
            numbers = numbers,
            operations = operations,
            problems = problems + problem
        )
    }
}

data class Homeworks(
    val problems: List<CephalopodProblem>
)

data class CephalopodProblem(
    val numbers: List<Int>,
    val operation: CephalopodProblemOperation,
) {
    fun solve(): Long = when(operation) {
        CephalopodProblemOperation.Sum -> numbers.sum().toLong()
        CephalopodProblemOperation.Multiply -> numbers.fold(1L) { acc, n -> acc * n }
    }
}

sealed class CephalopodProblemOperation {
    data object Sum : CephalopodProblemOperation()
    data object Multiply : CephalopodProblemOperation()
}

fun main() {
    val solution = AdventOfCode25Day06()

    val exampleP1 = solution.part1("aoc2025/day06/example")
    val solutionP1 = solution.part1("aoc2025/day06/puzzle-input-1")
//    val exampleP2 = solution.part2("aoc2025/day06/example")
//    val solutionP2 = solution.part2("aoc2025/day06/puzzle-input-1")

    println("Day 06, part 1 (example): $exampleP1")
    println("Day 06, part 1 (solution): $solutionP1")
//    println("Day 06, part 2 (example): $exampleP2")
//    println("Day 06, part 2 (solution): $solutionP2")
}
package aoc2025.day06

import adventofcode.utils.transpose

object Day06PuzzleParser {
    fun parseHomeworks(
        source: List<String>,
        parseNumbersFn: (List<String>) -> List<CephalopodsProblemNumbers>
    ): Homeworks {
        val numbers: List<CephalopodsProblemNumbers> = parseNumbersFn(source)
        val operations = parseOperators(source)

        // Validate operations are matching problems number
        if (operations.size != numbers.size)
            throw IllegalStateException("Error while parsing numbers: problems / operations mismatch")

        return buildHomeworks(
            problemIndex = 0,
            problemsNumber = numbers.size,
            numbers = numbers,
            operations = operations,
            problems = emptyList()
        )
    }

    fun parseNumbers(source: List<String>): List<CephalopodsProblemNumbers> =
        source
            .take(source.size - 1)
            .map { line ->
                Regex("""\d+""")
                    .findAll(line)
                    .map { it.value.toInt() }
                    .toList()
            }
            .transpose()

    fun parseNumbersLikeACephalopods(source: List<String>): List<CephalopodsProblemNumbers> =
        CephalopodsPuzzleParser(source).parseProblems()

    fun parseOperators(source: List<String>): List<CephalopodProblemOperation> =
        source
            .last()
            .let { line ->
                Regex("""[*+]""")
                    .findAll(line)
                    .map {
                        when (it.value) {
                            "+" -> CephalopodProblemOperation.Sum
                            "*" -> CephalopodProblemOperation.Multiply
                            else -> throw IllegalArgumentException("Unexpected operation: ${it.value}")
                        }
                    }
                    .toList()
            }

    private tailrec fun buildHomeworks(
        problemIndex: Int,
        problemsNumber: Int,
        numbers: List<CephalopodsProblemNumbers>,
        operations: List<CephalopodProblemOperation>,
        problems: List<CephalopodProblem>,
    ): Homeworks {
        if (problemIndex == problemsNumber) return Homeworks(problems)

        val problem = CephalopodProblem(
            numbers = numbers[problemIndex],
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
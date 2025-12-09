package aoc2025.day06

data class Homeworks(
    val problems: List<CephalopodProblem>
)

data class CephalopodProblem(
    val numbers: CephalopodsProblemNumbers,
    val operation: CephalopodProblemOperation,
) {
    fun solve(): Long = when (operation) {
        CephalopodProblemOperation.Sum -> numbers.sum().toLong()
        CephalopodProblemOperation.Multiply -> numbers.fold(1L) { acc, n -> acc * n }
    }
}

sealed class CephalopodProblemOperation {
    data object Sum : CephalopodProblemOperation()
    data object Multiply : CephalopodProblemOperation()
}

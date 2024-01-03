package d01

import FileUtils

class Day01 {
    fun run() {
        part1()
        part2()
    }

    private fun part1() = run(
        sourceFilePath = "d01/input",
        resultMessage = "Part 1",
        matchingFun = CalibrationDocument::numMatches
    )

    private fun part2() = run(
        sourceFilePath = "d01/input",
        resultMessage = "Part 2",
        matchingFun = CalibrationDocument::wordsAndNumMatches
    )

    private fun run(
        sourceFilePath: String,
        resultMessage: String,
        matchingFun: (String) -> Collection<Int>
    ) {
        val lines = FileUtils.parseToList(sourceFilePath)
        val document = CalibrationDocument(lines)

        val result = document.calibrationValues(matchingFun).sum()
        println("$resultMessage: $result")
    }
}

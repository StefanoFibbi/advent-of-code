package aoc2025.day04

typealias GridRowNumber = Int
typealias GridColumnNumber = Int

data class PaperGrid(
    val rows: GridRowNumber,
    val cols: GridColumnNumber,
    val grid: Array<Array<PaperGridSlot>>,
) {
    fun markPaperRolls(): Int {
        var selectedPaperCount = 0
        for (currentRow in 0..<grid.size) {
            for (currentColumn in 0..<grid.first().size) {
                if (canTakePaperRollAt(currentRow, currentColumn)) {
                    grid[currentRow][currentColumn] = PaperGridSlot.SelectedPaper
                    selectedPaperCount++
                }
            }
        }

        return selectedPaperCount
    }

    tailrec fun removeAllPossiblePaperRolls(totalRemovedPaperRollsNumber: Int): Int =
        when (val removedPaperRollsNumber = removeAllPossiblePaperRolls()) {
            0 -> totalRemovedPaperRollsNumber
            else -> removeAllPossiblePaperRolls(
                totalRemovedPaperRollsNumber = totalRemovedPaperRollsNumber + removedPaperRollsNumber
            )
        }

    private fun removeAllPossiblePaperRolls(): Int {
        var removedPaperRollsNumber = 0
        for (row in 0..<grid.size) {
            for (column in 0..<grid.first().size) {
                if (canTakePaperRollAt(row, column)) {
                    grid[row][column] = PaperGridSlot.Empty
                    removedPaperRollsNumber++
                }
            }
        }

        return removedPaperRollsNumber
    }

    private fun canTakePaperRollAt(row: Int, column: Int): Boolean =
        grid[row][column] is PaperGridSlot.RollOfPaper &&
                countAdjacentPaperRolls(row, column) <= MAX_ADJACENT_PAPER_ROLLS_NUM

    private tailrec fun countAdjacentPaperRolls(row: GridRowNumber, col: GridColumnNumber): Int {
        val minRow = if (row == 0) row else row - 1
        val maxRow = if (row == rows - 1) row else row + 1
        val minCol = if (col == 0) col else col - 1
        val maxCol = if (col == cols - 1) col else col + 1

        var paperCount = 0
        for (currentRow in minRow..maxRow) {
            for (currentColumn in minCol..maxCol) {
                if (currentRow == row && currentColumn == col) continue
                if (grid[currentRow][currentColumn] is PaperGridSlot.RollOfPaper) paperCount++
            }
        }

        return paperCount
    }

    companion object {
        const val MAX_ADJACENT_PAPER_ROLLS_NUM = 3
        fun from(gridStrings: List<String>): PaperGrid =
            gridStrings
                .map { gridLine -> gridLine.toCharArray().map { PaperGridSlot.from(it) }.toTypedArray() }
                .toTypedArray()
                .let {
                    PaperGrid(
                        rows = it.size,
                        cols = it.first().size,
                        grid = it,
                    )
                }
    }
}

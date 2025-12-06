package aoc2025.day04

sealed class PaperGridSlot {
    data object Empty : PaperGridSlot()

    interface RollOfPaper
    data object Paper : PaperGridSlot(), RollOfPaper
    data object SelectedPaper : PaperGridSlot(), RollOfPaper

    companion object {
        fun from(char: Char) = when (char) {
            '@' -> Paper
            'x' -> SelectedPaper
            else -> Empty
        }
    }
}

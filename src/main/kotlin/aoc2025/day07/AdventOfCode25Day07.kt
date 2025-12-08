package aoc2025.day07

import utils.FileUtils

class AdventOfCode25Day07 {
    fun part1(path: String): Int {
        val manifold =
            FileUtils
                .parseToList(path)
                .let { TachyonMainfoldParser.parse(it) }

        manifold.shotBeam()
        return manifold.grid.sumOf { line -> line.filter { it is ManifoldCell.BeamSplitter.Activated }.size }
    }

}

object TachyonMainfoldParser {
    fun parse(source: List<String>): TachyonManifold =
        source
            .map { line ->
                line.toCharArray().map(ManifoldCell::from).toTypedArray()
            }
            .let { TachyonManifold(it) }
}

data class TachyonManifold(
    val grid: List<Array<ManifoldCell>>
) {
    fun shotBeam() {
        for (lineIndex in 1..<grid.size) {
            for (cellIndex in 0..<grid[lineIndex].size) when {
                // Cell above was beam -> propagate beam
                grid[lineIndex][cellIndex] is ManifoldCell.Empty && grid[lineIndex - 1][cellIndex] is ManifoldCell.TachyonBeam ->
                    grid[lineIndex][cellIndex] = ManifoldCell.TachyonBeam

                // Cell above was source for beam -> propagate beam
                grid[lineIndex][cellIndex] is ManifoldCell.Empty && grid[lineIndex - 1][cellIndex] is ManifoldCell.BeamSource ->
                    grid[lineIndex][cellIndex] = ManifoldCell.TachyonBeam

                // Splitter and cell above was beam -> split beam
                grid[lineIndex][cellIndex] is ManifoldCell.BeamSplitter.Deactivated && grid[lineIndex - 1][cellIndex] is ManifoldCell.TachyonBeam -> when (cellIndex) {
                    // Splitter on extreme left side -> propagate right only
                    0 -> {
                        grid[lineIndex][1] = ManifoldCell.TachyonBeam
                        grid[lineIndex][cellIndex] = ManifoldCell.BeamSplitter.Activated
                    }

                    // Splitter on extreme right side -> propagate right only
                    grid[lineIndex].size - 1 -> {
                        grid[lineIndex][cellIndex - 1] = ManifoldCell.TachyonBeam
                        grid[lineIndex][cellIndex] = ManifoldCell.BeamSplitter.Activated
                    }

                    // Double split
                    else -> {
                        grid[lineIndex][cellIndex - 1] = ManifoldCell.TachyonBeam
                        grid[lineIndex][cellIndex + 1] = ManifoldCell.TachyonBeam
                        grid[lineIndex][cellIndex] = ManifoldCell.BeamSplitter.Activated
                    }
                }
            }
        }
    }
}

sealed class ManifoldCell {
    data object Empty : ManifoldCell()
    data object BeamSource : ManifoldCell()
    data object TachyonBeam : ManifoldCell()

    sealed class BeamSplitter : ManifoldCell() {
        data object Activated : BeamSplitter()
        data object Deactivated : BeamSplitter()
    }

    override fun toString(): String = when (this) {
        is Empty -> "."
        is BeamSource -> "S"
        is TachyonBeam -> "|"
        is BeamSplitter -> "^"
    }

    companion object {
        fun from(value: Char): ManifoldCell = when (value) {
            '.' -> Empty
            'S' -> BeamSource
            '|' -> TachyonBeam
            '^' -> BeamSplitter.Deactivated
            else -> throw IllegalArgumentException("Invalid value provided: $value")
        }
    }
}


fun main() {
    val solution = AdventOfCode25Day07()

    solution.part1("aoc2025/day07/example").also { println("Day 07, part 1 (example): $it") }
    solution.part1("aoc2025/day07/puzzle-input-1").also { println("Day 07, part 1 (solution): $it") }

//    val exampleP2 = solution.part2("aoc2025/day07/example")
//    println("Day 07, part 2 (example): $exampleP2")

//    val solutionP2 = solution.part2("aoc2025/day07/puzzle-input-1")
//    println("Day 07, part 2 (solution): $solutionP2")
}
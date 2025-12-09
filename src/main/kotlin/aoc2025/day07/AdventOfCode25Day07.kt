package aoc2025.day07

import utils.FileUtils

class AdventOfCode25Day07 {
    fun part1(path: String): Int {
        val manifold =
            FileUtils
                .parseToList(path)
                .let { TachyonMainfoldParser.parse(it) }

        manifold.shotBeam()
        return manifold.activatedBeamSplitCount()
    }

    fun part2(path: String): Long {
        val manifold =
            FileUtils
                .parseToList(path)
                .let { TachyonMainfoldParser.parse(it) }

        manifold.shotBeam()
        return manifold.allParticlesTimelinesCount()
    }
}

fun main() {
    val solution = AdventOfCode25Day07()

    solution.part1("aoc2025/day07/example").also { println("Day 07, part 1 (example): $it") }
    solution.part1("aoc2025/day07/puzzle-input-1").also { println("Day 07, part 1 (solution): $it") }
    solution.part2("aoc2025/day07/example").also { println("Day 07, part 2 (example): $it") }
    solution.part2("aoc2025/day07/puzzle-input-1").also { println("Day 07, part 2 (solution): $it") }
}
package aoc2025.day01

import utils.FileUtils


class AdventOfCode25Day01 {
    fun part1(): ZeroTicksCount {
        val path = "aoc2025/day01/puzzle-input-1"
        val lines = FileUtils.parseToList(path)
        val rotations = lines.map { DialRotation.from(it) }

        return rotations
            .fold(SafeDial()) { dial, rotation ->
                dial.rotate(
                    rotation = rotation,
                    zeroCountStrategy = SafeDial.ZeroTickCountStrategy.ENDS_AT_ZERO,
                )
            }.zeroTickCount
    }

    fun part2(): ZeroTicksCount {
        val path = "aoc2025/day01/puzzle-input-1"
        val lines = FileUtils.parseToList(path)
        val rotations = lines.map { DialRotation.from(it) }

        return rotations
            .fold(SafeDial()) { dial, rotation ->
                dial.rotate(
                    rotation = rotation,
                    zeroCountStrategy = SafeDial.ZeroTickCountStrategy.EACH_TIME_ZERO_IS_REACHED,
                )
            }.zeroTickCount
    }
}

fun main() {
    val day1 = AdventOfCode25Day01()
    val p1 = day1.part1()
    val p2 = day1.part2()

    println("Day 01, part 1: $p1")
    println("Day 01, part 2: $p2")
}

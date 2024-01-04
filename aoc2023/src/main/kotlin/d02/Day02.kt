package d02

import FileUtils
import d02.model.CubeColor
import d02.model.Game
import kotlin.math.max

class Day02 {
    fun run() {
        part1()
        part2()
    }

    private fun part1() {
        val lines = FileUtils.parseToList("d02/input")
        val games = lines.map(::parseGame)
        val result: Int = games.filter {
            val enoughReds = it.cubesFor(CubeColor.RED) <= 12
            val enoughGreens = it.cubesFor(CubeColor.GREEN) <= 13
            val enoughBlues = it.cubesFor(CubeColor.BLUE) <= 14

            enoughReds && enoughGreens && enoughBlues
        }.sumOf {
            it.id
        }

        println("Part 1: $result")
    }

    private fun part2() {
        val lines = FileUtils.parseToList("d02/input")
        val games = lines.map(::parseGame)
        val result: Int = games.sumOf {
            val reds = cubesNumForColor(it.highestCubesNumPerColor, CubeColor.RED)
            val greens = cubesNumForColor(it.highestCubesNumPerColor, CubeColor.GREEN)
            val blues = cubesNumForColor(it.highestCubesNumPerColor, CubeColor.BLUE)

            reds * greens * blues
        }

        println("Part 2: $result")
    }

    private fun parseGame(line: String): Game {
        val regex = Regex("Game \\d+|\\d+ \\w+(, \\d+ \\w+)*")
        val groups: List<MatchResult> = regex.findAll(line).toList()
        val baseCubesMap = mapOf(
            CubeColor.RED to 0,
            CubeColor.GREEN to 0,
            CubeColor.BLUE to 0
        )

        return Game(
            id = groups[0].value.split(" ")[1].toInt(),
            highestCubesNumPerColor = groups.subList(1, groups.size).fold(baseCubesMap) { acc, drawnCubes ->
                val cubes = parseDrawnCubes(drawnCubes.value)
                mapOf(
                    CubeColor.RED to max(
                        cubesNumForColor(acc, CubeColor.RED),
                        cubesNumForColor(cubes, CubeColor.RED)
                    ),
                    CubeColor.GREEN to max(
                        cubesNumForColor(acc, CubeColor.GREEN),
                        cubesNumForColor(cubes, CubeColor.GREEN)
                    ),
                    CubeColor.BLUE to max(
                        cubesNumForColor(acc, CubeColor.BLUE),
                        cubesNumForColor(cubes, CubeColor.BLUE)
                    )
                )
            }
        )
    }

    private fun parseDrawnCubes(drawnCubes: String): Map<CubeColor, Int> =
        drawnCubes
            .split(", ")
            .associate { handCube ->
                val handCubeParts = handCube.split(" ")
                val num = handCubeParts[0].toInt()
                val color = handCubeParts[1]
                Pair(CubeColor.valueOf(color.uppercase()), num)
            }

    private fun cubesNumForColor(cubes: Map<CubeColor, Int>, color: CubeColor): Int = cubes[color] ?: 0
}

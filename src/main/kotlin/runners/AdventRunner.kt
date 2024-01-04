package runners

import GameDay

sealed class AdventRunner {
    fun run(day: GameDay) = when (day) {
        GameDay.ALL -> runAll()
        else -> runOne(day)
    }

    private fun runOne(day: GameDay) {
        println("""
            ######################################################
                                    Day ${day.ordinal}
            ######################################################
        """.trimIndent())
        when (day) {
            GameDay.D01 -> d01()
            GameDay.D02 -> d02()
            GameDay.ALL -> throw IllegalArgumentException("Unsupported day provided: $day")
        }
        println()
    }

    private fun runAll() =
        GameDay.entries
            .filterNot { it == GameDay.ALL }
            .forEach { runOne(GameDay.valueOf(it.ordinal)) }


    abstract fun d01()
    abstract fun d02()
}

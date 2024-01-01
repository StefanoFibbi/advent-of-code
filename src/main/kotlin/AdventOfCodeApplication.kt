object ArgsName {
    const val YEAR = "year"
    const val DAY = "day"
}

data class RunSpecs(
    val year: GameYear,
    val day: GameDay,
)

typealias ArgsMap = Map<String, String>

class AdventOfCodeApplication {
    companion object {
        private val aocRunners = mapOf(
            GameYear.Y2023 to runners.Y2023
        )

        @JvmStatic
        fun main(args: Array<String>) {
            val specs: RunSpecs = argsToSpecs(
                argsMap = argsToMap(args)
            )
            aocRunners[specs.year]?.run(specs.day)
        }

        private fun argsToMap(args: Array<String>): ArgsMap =
            args.associate { argument ->
                val arg = argument.split("=")[0].replace("-", "")
                val value = argument.split("=")[1]
                arg to value
            }

        private fun argsToSpecs(argsMap: ArgsMap) = RunSpecs(
            day = argsMap[ArgsName.DAY]?.let { day -> GameDay.valueOf(day.toInt()) } ?: GameDay.ALL,
            year = argsMap[ArgsName.YEAR]?.let { year -> GameYear.valueOf(year.toInt()) } ?: GameYear.entries.last()
        )
    }
}

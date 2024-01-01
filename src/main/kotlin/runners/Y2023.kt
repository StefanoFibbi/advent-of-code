package runners

import d01.Day01

data object Y2023 : AdventRunner() {
    private val d1 = Day01()
    override fun d01() = d1.run()
}

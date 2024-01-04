package runners

import d01.Day01
import d02.Day02

data object Y2023 : AdventRunner() {
    private val d1 = Day01()
    private val d2 = Day02()
    override fun d01() = d1.run()
    override fun d02() = d2.run()
}

package aoc2025.day07

object TachyonMainfoldParser {
    fun parse(source: List<String>): TachyonManifold =
        source
            .map { line -> line.toCharArray().map(ManifoldCell::from).toTypedArray() }
            .let { TachyonManifold(it) }
}
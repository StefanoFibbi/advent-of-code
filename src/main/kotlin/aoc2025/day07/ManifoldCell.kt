package aoc2025.day07

sealed class ManifoldCell {
    data object Empty : ManifoldCell()
    data object BeamSource : ManifoldCell()
    data class TachyonBeam(val particlesNum: Long = 1) : ManifoldCell()

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
            '|' -> TachyonBeam()
            '^' -> BeamSplitter.Deactivated
            else -> throw IllegalArgumentException("Invalid value provided: $value")
        }
    }
}
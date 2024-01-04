package d02.model

data class Game(
    val id: Int,
    val highestCubesNumPerColor: Map<CubeColor, Int>
) {
    fun cubesFor(color: CubeColor) = highestCubesNumPerColor[color] ?: 0
}

enum class GameYear {
    Y2023
    ;

    companion object {
        fun valueOf(year: Int) = GameYear.valueOf("Y$year")
    }
}

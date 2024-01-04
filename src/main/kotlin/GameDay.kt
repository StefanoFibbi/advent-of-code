enum class GameDay {
    ALL,
    D01,
    D02,
    ;

    companion object {
        fun valueOf(day: Int) = if (day > 0 && day < entries.size) entries[day] else entries[0]
    }
}

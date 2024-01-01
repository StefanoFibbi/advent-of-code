enum class GameDay {
    ALL,
    D01,
    ;

    companion object {
        fun valueOf(day: Int) = if (day > 0 && day < entries.size) entries[day] else entries[0]
    }
}

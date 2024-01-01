package utils

object FileUtils {
    fun parseToList(path: String): List<String> {
        val inputStream = javaClass.classLoader.getResourceAsStream(path)
            ?: throw IllegalArgumentException("Resource not found at: $path")

        return inputStream.bufferedReader().useLines { it.toList() }
    }
}

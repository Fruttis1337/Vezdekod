import java.io.File
import java.io.IOException

fun getJsonDataFromSDcard(fileName: String): String? {
    val jsonString: String
    try {
        jsonString = File(fileName).inputStream().bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}
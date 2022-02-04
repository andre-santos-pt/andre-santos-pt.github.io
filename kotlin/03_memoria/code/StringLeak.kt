data class StringLeak(val content: CharArray, val first: Int = 0, val length: Int = content.size) {
    fun substring(from: Int, to: Int) = StringLeak(content, from, to - from + 1)
    fun charAt(index: Int) = content[first + index]
}

fun main() {
    val longCharArray = CharArray(100) { 'a' + it }
    val sub = StringLeak(longCharArray).substring(2, 4)
    println(sub.length)
}



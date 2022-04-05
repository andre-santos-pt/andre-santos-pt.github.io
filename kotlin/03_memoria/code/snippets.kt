import java.util.*

fun sizeIteration(a: IntArray, i: Int = 0): Int =
    if (i == a.size) i
    else sizeIteration(a, i + 1)

fun main() {
    val a = intArrayOf(1, 2, 3)

    println(sizeIteration(a, 0))

    val sel = Array(9) { Position(it / 3, it % 3) }


    println(sel.contentToString())

    val diagonal = arrayOf(
        Position(0, 0),
        Position(1, 1),
        Position(2, 2)
    )
    val first = diagonal[0]

    val matrix = Array<IntArray>(4) { IntArray(3) }
    val last = matrix[3]

    val m = Array(2) { line ->
        IntArray(5) { col ->
            1 + line * 5 + col
        }
    }

    val stairs = Array(5) { line -> IntArray(line) }

    val t = 1000

    println(stairs.contentDeepToString())
}


data class Position(val line: Int, val col: Int)


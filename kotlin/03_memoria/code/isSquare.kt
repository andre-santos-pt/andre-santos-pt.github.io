
fun isSquare(m: Array<IntArray>): Boolean {
    require(m.size > 0)
    tailrec fun aux(i: Int): Boolean =
        if(i == m.size) true
        else if(m[i].size != m.size) false
        else aux(i + 1)
    return aux(1)
}

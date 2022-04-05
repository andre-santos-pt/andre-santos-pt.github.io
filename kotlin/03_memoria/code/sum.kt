fun sum(a: IntArray): Int {
    tailrec fun aux(i: Int = 0, sum: Int = 0): Int =
        if (i == a.size) sum
        else aux(i + 1, sum + a[i])
    return aux()
}
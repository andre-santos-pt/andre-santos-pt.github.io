fun isOrdered(a: IntArray): Boolean {
    tailrec fun aux(i: Int = 0): Boolean =
        if (i == a.lastIndex) true
        else if (a[i] > a[i + 1]) false
        else aux(i + 1)
    return aux()
}
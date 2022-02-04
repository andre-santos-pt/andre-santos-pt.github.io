fun factorial(n: Int): Int {
    tailrec fun aux(i: Int, r: Int): Int =
        if(i <= 1) r
        else aux(i - 1,r * i)
    return aux(n, 1)
}
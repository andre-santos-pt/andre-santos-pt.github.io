fun sqrt(n: Double) : Double {
    tailrec fun aux(r: Double) : Double =
        if(approxEqual(r * r, n)) r
        else aux((r + n / r) / 2)

    return aux(n / 2)
}

fun sqrt(n: Double) : Double {
    tailrec fun aux(r: Double) =
        if(approxEqual(r * r, n)) r
        else sqrt((r + n / r) / 2)

    return aux(r / 2)
}

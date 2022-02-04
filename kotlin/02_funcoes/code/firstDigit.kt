tailrec fun firstDigit(n: Int): Int =
    if(n < 10) n
    else firstDigit(n / 10)

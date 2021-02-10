tailrec fun firstDigit(n: Int) =
    if(n < 10) n
    else firstDigit(n / 10)

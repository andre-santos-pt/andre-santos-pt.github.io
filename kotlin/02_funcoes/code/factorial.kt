fun factorialRec(n: Int): Int =
    if(n <= 1) 1
    else n * factorialRec(n - 1)
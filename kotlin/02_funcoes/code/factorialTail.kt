fun factorialTail(n: Int) = factTailRec(n, 1)

tailrec fun factTailRec(n: Int, r: Int): Int =
  if(n <= 1) r
  else factTailRec(n - 1, r * n)



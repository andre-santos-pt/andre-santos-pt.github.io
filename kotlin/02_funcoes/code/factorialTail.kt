

tailrec fun factTail(n: Int, r: Int): Int =
  if(n <= 1) r
  else factTail(n - 1, r * n)

fun factorial(n: Int) = factTail(n, 1)

fun factorialTail(n: Int) = factorialTail(n, 1)
tailrec fun factorialTail(n: Int, r: Int): Int =
  if(n <= 1) r
  else factorialTail(n-1, r*n)

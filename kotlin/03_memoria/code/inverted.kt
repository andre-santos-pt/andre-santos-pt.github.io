fun inverted(a: IntArray) =
    IntArray(a.size) { i -> a[a.lastIndex - i] }
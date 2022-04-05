fun naturals(min: Int, max: Int): IntArray {
    require(min <= max)
    return IntArray(max - min + 1) { i -> min + i }
}
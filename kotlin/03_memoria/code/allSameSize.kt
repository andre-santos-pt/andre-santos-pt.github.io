tailrec fun allSameSize(m: Array<IntArray>, fromIndex: Int = 0): Boolean {
    require(fromIndex >= 0 && fromIndex < m.size)
    return if (fromIndex == m.lastIndex) true
    else if (m[fromIndex].size != m[fromIndex + 1].size) false
    else allSameSize(m, fromIndex + 1)
}
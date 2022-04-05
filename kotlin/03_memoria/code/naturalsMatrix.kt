fun naturalsMatrix(lines: Int, cols: Int): Array<IntArray> {
    require(lines >= 0 && cols >= 0)
    return Array(lines) { l ->
        IntArray(cols) { c ->
            1 + l * cols + c
        }
    }
}

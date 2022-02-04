fun naturalsMatrix(lines: Int, cols: Int) =
    Array(lines) { l ->
        IntArray(cols) { c ->
            1 + l * cols + c
        }
    }

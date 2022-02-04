fun transpose(m: Array<IntArray>): Array<IntArray> {
    require(allSameSize(m))
    return Array(m[0].size) { l ->
        IntArray(m.size) { c ->
            m[c][l]
        }
    }
}
---
title: Cálculos com vetores inteiros
exer: true
---

{% include code code="
fun doubleElements(a: IntArray) : IntArray =
    IntArray(a.size * 2) { a[it / 2] }
" %}

{% include code code="
fun subArray(a: IntArray, from: Int, to: Int) : IntArray =
    IntArray(to - from + 1) { a[it + from] }
" %}


{% include code code="
tailrec fun findIndex(a: IntArray, n: Int, fromIndex: Int = 0) : Int =
    if(fromIndex > a.lastIndex) - 1
    else if(a[fromIndex] == n) fromIndex
    else findIndex(a, n, fromIndex + 1)
" %}
    {% include code code="
fun contains(a: IntArray, n: Int) : Boolean = findIndex(a, n) != -1
" %}

# media

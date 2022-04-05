---
title: Vetores de referências
image: imagens/arquivo.png
---

>Numa [secção anterior](vetores) foram introduzidos vetores que contêm valores elementares. Aqui abordaremos vetores cujas posições contêm referências outros vetores ou valores compostos.

Tal como podemos ter uma referência, podemos também ter vetores de referências. Ou seja, vetores cujas posições apontam para outros elementos.

# Matrizes
Comecemos pelo caso onde um vetor contém referências para outros vetores. Esta possibilidade pode ser utilizada para representações matriciais, onde tipicamente cada elemento do vetor é outro vetor que corresponde a uma linha de uma matriz.

Para definir um vetor de referências utilizamos o tipo *Array*, em conjunto com uma função geradora de elementos (como no caso dos [vetores de valores](vetores)). No seguinte exemplo estamos a alocar um vetor de comprimento 5, onde cada posição terá uma referência para um vetor de inteiros de comprimento igual ao índice de cada posição.

{% include code code="
val stairs = Array(5) { line -> IntArray(line) }
"
%}

{% include code code="
val matrix = Array(4) { IntArray(3) }
val last = matrix[3]
"
msg="Alocação de vetor com comprimento 4, onde cada elemento é uma referência para um vetor de inteiros, sendo cada um alocado na função geradora com comprimento 3. Esta forma é adequada para representar uma matriz de inteiros de 4 linhas e 3 colunas.<br><br>A referência guardada em <b>last</b>, obtida através de um acesso ao vetor, aponta para o vetor correspondente à última linha."
img="imagens/matrix43.png"
%}

## Funções sobre matrizes
{% include code code="
fun isSquare(m: Array<IntArray>): Boolean {
    require(m.size > 0)
    tailrec fun aux(i: Int): Boolean =
        if(i == m.size) true
        else if(m[i].size != m.size) false
        else aux(i + 1)
    return aux(1)
}"
msg="Exemplo: função para verificar se uma matriz é quadrada (i.e. número de linhas igual ao número de colunas)."
%}


{% include code file="naturalsMatrix.kt"
msg="Exemplo: função para criar uma matriz com os números naturais."
%}

# Vetores de valores compostos

{% include code code="
data class Position(val line: Int, val col: Int)
"
%}


{% include code code="
val diagonal = arrayOf(
        Position(0, 0),
        Position(1, 1),
        Position(2, 2)
    )
    val first = diagonal[0]
"
%}

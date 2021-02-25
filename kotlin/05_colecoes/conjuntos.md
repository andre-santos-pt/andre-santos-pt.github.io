---
title: Conjuntos
---

Um **conjunto** é uma coleção de elementos sem duplicados. Por comparação a uma lista, a inserção de um elemento repetido não terá efeito.

A principal vantagem do conjunto é a eficiência da operação de verificação de existência de elemento (*contains*), pois a concretização baseia-se numa tabela de dispersão (custo constante, por comparação a custo linear numa lista).

Normalmente, num conjunto a ordem pela qual os elementos são inseridos não dá garantias sobre a ordem de interação.  Por conseguinte, um conjunto não é adequado para manter informação de algo onde a relação de ordem dos elementos é relevante. Porém, se utilizarmos a implementação por omissão em Kotlin, o conjunto mantém informação sobre a ordem de inserção dos elementos.

Tal como nas listas, temos uma abstração para leitura de conjuntos e outra para alteração.

# Conjuntos para leitura (*read-only*)

{% include code code="
val stringSet = setOf(\"um\", \"dois\", \"tres\")
"
%}

Nas operações, temos algumas idênticas à lista, mas note-se que não existem operações com indexação (pois não existe tal concepção em conjuntos). Na verdade, o Kotlin fornece algumas funções adicionais (via extensões) que manipulam índices em conjuntos. Porém, essas operações são desaconselhadas em termos de desempenho, pois têm um custo linear.

| Operação       | Descrição     | Custo | Exemplo | Resultado |
| ----------- | ----------- |--------|
| size | Número de elementos | Constante | stringSet.size | 3 |
| isEmpty | Conjunto é vazio? | Constante | stringSet.isEmpty | false |
| contains(*) | Conjunto contém elemento? | Constante | list.contains("quatro") | false |


# Conjuntos mutáveis

Também no caso dos conjuntos mutáveis, algumas operações são equivalentes às listas mutáveis, mas não temos operações com base em índices. A principal diferença é que invocar *add* com um elemento que já existe não terá efeito no conjunto. Relativamente a desempenho, de realçar que o custo de *remove* é constante.

| Operação       | Descrição   | Custo   | Exemplo | Resultado |
| ----------- | ----------- |--------|
| add(*) | Adiciona elemento | Constante | stringSet.add("quatro") | ["um", "dois", "tres", "quatro"] |
| remove(*) | Remove elemento | Constante | stringSet.remove("dois") | ["um", "tres"] |
| clear() | Remove todos os elementos | Constante | stringSet.clear() | [] |


{% include code code="
fun <T> removeDuplicates(list: List<T>) : Set<T> {
    val s = mutableSetOf<T>()
    for(e in list)
        s.add(e)
    return s
}
"
msg="Exemplo: Função para obter os elementos de uma lista sem duplicados (num conjunto). O custo temporal é linear: a lista é iterada na totalidade, mas a inserção no conjunto tem um custo constante."
%}

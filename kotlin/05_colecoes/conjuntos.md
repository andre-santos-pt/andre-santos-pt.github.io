---
title: Conjuntos
---

Um **conjunto** é uma coleção de elementos sem duplicados. Por comparação a uma lista, a inserção de um elemento repetido não terá efeito.

A principal vantagem do conjunto é a eficiência da operação de verificação de existência de elemento (*contains*), pois a concretização baseia-se numa tabela de dispersão (custo constante, por comparação a custo linear numa lista).

Normalmente, num conjunto a ordem pela qual os elementos são inseridos não dá garantias sobre a ordem de interação.  Por conseguinte, um conjunto não é adequado para manter informação de algo onde a relação de ordem dos elementos é relevante. Porém, se utilizarmos a implementação por omissão em Kotlin, o conjunto mantém informação sobre a ordem de inserção dos elementos.

Tal como nas listas, temos uma abstração para leitura de conjuntos e outra para alteração.

# Conjuntos para leitura (*read-only*)

{% include code code="
val list = setOf(\"um\", \"dois\", \"tres\")"
%}

Nas operações, temos algumas idênticas à lista, mas note-se que não existem operações com indexação (pois não existe tal concepção em conjuntos).

| Operação/Propriedade       | Descrição      | Exemplo |
| ----------- | ----------- |--------|
| size | Número de elementos | list.size |
| isEmpty | Lista é vazia? | list.isEmpty
| contains(*) | Lista contém elemento? | list.contains("B") |


# Conjuntos imutáveis

Também no caso dos conjuntos imutáveis, algumas operações são equivalentes às listas imutáveis, mas não temos operações com base em índices.

| Operação/Propriedade       | Descrição      | Exemplo |
| ----------- | ----------- |--------|
| add(*) | Adiciona elemento | list.add(5) |
| remove(*)
| clear()

# Iteração

A iteração num conjunto po

---
title: ArrayList
exer: true
---

Objetivo deste exercício é implementar uma versão simplificada da coleção *ArrayList*.

Uma *ArrayList* é no fundo um objeto que gere a ocupação de um vetor (*array*), onde é alocado um espaço em memória (vetor), e um ponteiro indica até onde o vetor está preenchido. Quando já não há mais espaço, é alocado um novo vetor.

![arraylist.png](arraylist.png)

<hr>

### 1. Classe

Escreva a classe *SimpleArrayList*, cujos objetos têm como propriedades:
- size (número de elementos)
- isEmpty (a lista está vazia?)

e operações:
- add (adiciona elemento)
- get (obtém elemento em índice)
- contains (verifica se existe elemento)
- merge (juntar com outra lista, produzindo uma nova)

Teste as operações desenvolvidas.

### 2. Contagem com critério flexível

Acrescente uma operação para permitir uma contagem de elementos dado um critério (lambda).

{% include code code="
val list = SimpleArrayList<Int>()
// ...
val c = list.count { it > 0 }
"
%}

### 3. Partição com critério flexível

Acrescente uma operação para fazer uma partição em duas listas, dado um critério (lambda). Por forma a devolver as duas listas utilize `Pair<SimpleArrayList, SimpleArrayList>`.

{% include code code="
val list = SimpleArrayList<Int>()
//...
val (neg, pos) = list.partition { it > 0 }
"
%}

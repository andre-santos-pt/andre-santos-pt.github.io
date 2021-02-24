---
title: Coleções e iteradores
---

Nas secções anteriores abordámos as abstrações de **lista** e **conjunto**. É possível constatar que ambas têm uma natureza semelhante, e partilham operações comuns (pe. `size`, `contains`, `add`). Embora com propriedades ligeiramente diferentes, tanto uma lista como um conjunto consiste numa **coleção** de elementos.

Desta forma, temos a abstração de *Collection* para representar as características comuns a listas e conjuntos (e possivelmente outros).


# Iteradores
Muito frequentemente processamos uma coleção através de um "varrimento", i.e. uma passagem pelos seus elementos. Este tipo de processo designa-se por **iteração**. *Collection* é por sua vez baseada noutra abstração chamada *Iterable*, para representar algo iterável de onde possamos obter elementos.

O processo de iteração é representado na abstração *Iterator*. Este objeto controla o progresso da iteração, solicitando sucessivamente o próximo elemento até que não existam mais para processar. A estrutura típica de utilização de um iterador na sua forma mais elementar é a seguinte.

{% include code code="
val list = listOf(1, 2, 3, 4, 5)
val iterator: Iterator<Int> = list.iterator()
while(iterator.hasNext()) {
  val e: Int = iterator.next()
  // ...
}
"
%}

Neste excerto de código, estamos processar uma lista de inteiros. Se se tratasse de um conjunto, o código seria igual. Desta forma, podemos utilizar o tipo *Collection* por forma aceitar tanto listas como conjuntos. No seguinte exemplo temos uma função que devolve o somatório dos inteiros de uma coleção.

{% include code code="
fun sum(col: Collection<Int>): Int {
    val iterator: Iterator<Int> = col.iterator()
    var sum = 0
    while(iterator.hasNext()) {
        val e = iterator.next()
        sum += e
    }
    return sum
}
"
%}

Esta função pode ser utilizada tanto com uma lista, como com um conjunto.
{% include code code="
val s1 = sum(listOf(1, 2, 2, 3))
val s2 = sum(setOf(1, 2, 3))
"
%}

Suponhamos agora que temos uma função para calcular o produto de uma coleção de inteiros. Note-se que há bastante semelhança com a função anterior, no que toca ao processo de iteração.

{% include code code="
fun prod(col: Collection<Int>): Int {
    val iterator: Iterator<Int> = col.iterator()
    var prod = 1
    while(iterator.hasNext()) {
        val e = iterator.next()
        prod *= e
    }
    return prod
}
"
%}

Por forma a evitar que se repita o código relativo à iteração, podemos ter uma função que dada uma coleção, recebe uma função com a acção a realizar com cada elemento.

{% include code code="
fun <Int> iterate(col: Collection<Int>, action:(Int) -> Unit) {
    val iterator: Iterator<Int> = col.iterator()
    while(iterator.hasNext()) {
        action(iterator.next())
    }
}
"
%}

Desta forma, poderíamos efetuar os cálculos do somatório e produto da seguinte forma.

{% include code code="
val l = listOf(1, 2, 3)
val sum = 0
iterate(l) { sum += it }

val s = setOf(4, 5, 6)
val prod = 1
iterate(s) { prod *= it }
"
%}

Por fim, a função poderá ficar ainda mais genérica utilizando *Iterable* e um tipo genérico para os elementos.

{% include code code="
fun <T> iterate(col: Iterable<T>, action:(T) -> Unit) {
    val iterator: Iterator<T> = col.iterator()
    while(iterator.hasNext()) {
        action(iterator.next())
    }
}
"
%}

{% include code code="
val sum = 0
iterate(1..10) { sum += it }
"
%}

Dado que a necessidade de iterações é muito comum, existe na linguagem uma função pré-definida para tal chamada *forEach* ("para cada"). Esta função é muito semelhante a *iterate*, apenas difere em ser invocada em objetos.

{% include code code="
list.forEach { sum += it }
"
%}


# Transformações

As bibliotecas de Kotlin incluem muitas funções para processamento de coleções, das quais detalharemos as mais frequentes. A lógica de funcionamento segue o princípio *map-reduce* originário da programação funcional.

## Mapeamento
Por vezes é necessário transformar os elementos de uma coleção de forma uniforme. Para este propósito existe a operação de mapeamento.
Neste tipo de transformação, uma coleção é produzida a partir da transformação de cada um dos elementos da mesma. A função *map* recebe a função que define a transformação a efetuar.

{% include code code="
val list = listOf(1, 2, 3, 4)
val powers = list.map { it * it } // [1, 4, 9, 16]
"
%}

## Seleção
Frequentemente temos que selecionar elementos de uma coleção, deixando outros for do processo. Isto pode ser feito com a operação *filter* que recebe uma função booleana que decide quais os elementos a incluir.

{% include code code="
val list = listOf(1, -1, -3, 4)
val positive = list.filter { it % 2 == 0 } // [1, 4]
"
%}

Outra forma de selecionar elementos é através da obtenção de elementos distintos (únicos, sem duplicados), utilizando a operação *distinct*.
{% include code code="
val list = listOf(1, -1, 1, 4, 4)
val unique = list.distinct() // [1, -1, 4]
"
%}

## Ordenação
Para além da transformação e seleção de elementos, também é frequente alterar a sua ordem mediante um critério de ordenação.

Quanto estamos perante uma coleção de elementos numéricos podemos utilizar a operação *sorted* para ordenação crescente, e *sortedDescending* para ordenação decrescente.

{% include code code="
val list = listOf(1, -1, 1, 4, 3)
val sortedList = list.sorted() // [-1, 1, 1, 3, 4]
"
%}

Por forma a definir critérios de ordenação específicos para um tipo T, utilizamos a operação *sortedWidth* fornecendo uma função que dados dois valores do tipo T decide qual tem prioridade (*Comparable*).



<!--
## Redução
As operações anteriores transformam, filtram, ou reordenam elementos. O processo de **redução** consiste em calcular algo a partir dos elementos resultantes das transformações anteriores.

A operação *reduce* efetua um cálculo
recebe uma função que dado

{% include code code="
fun factorial2(n: Int): Int = (1..n).reduce { r, d ->
    r * d
}
"
%}
-->

<!--
### Funções auxiliares de coleções numéricas

count
maxOrNull, minOrNull
average
-->

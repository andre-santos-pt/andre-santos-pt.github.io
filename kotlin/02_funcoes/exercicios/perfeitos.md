---
title: Números perfeitos
exer: true
---


O objetivo deste conjunto de exercícios é praticar:
- recursão
- recursão na cauda
- funções de ordem superior
- decomposição funcional

É proibido utilizar:
- variáveis (**var**)
- ciclos
- listas
- funções externas ao exercício

## Tema: números perfeitos

Um número natural *perfeito* é aquele cuja soma dos seus divisores *próprios* (exclui o número em si) é igual ao número. Por exemplo, *6* é um número perfeito:
- os seus divisores próprios são 1, 2, e 3
- 1 + 2 + 3 = *6*

Os primeiros números naturais perfeitos são:
- 6
- 28
- 496
- 8128

A sequência de passos propostos (funções) conduzirá ao resultado final, ter uma forma de determinar se um número é perfeito.



### 1. Recursão regular
Escreva uma função *recursiva* para somar números inteiros num dado intervalo.
{% include code code="
val s = sumRangeRec(1, 5) // 15
"%}

- Experimente executar a função em *debug*. Qual a profundidade da pilha de chamadas para sumRangeRec(1, 5)?
- Experimente invocar a função com um intervalo grande (p.e. um milhão de diferença)

### 2. Recursão na cauda
Escreva uma função equivalente à anterior, mas utilizando *recursão na cauda* -- poderá utilizar uma função auxiliar aninhada.

{% include code code="
fun sumRange(min: Int, max: Int): Int {
    tailrec fun aux(...): Int =

    return aux(min, 0)
}
"%}

- Experimente novamente executar a função em *debug*. Qual a profundidade da pilha de chamadas para sumRange(1, 5)?
- Experimente invocar a função com um intervalo grande (p.e. um milhão de diferença)

A partir deste ponto, trabalharemos idealmente com esta versão da função. Caso não esteja disponível, utilizamos a inicial.

### 3. Expressões lambda
Experimente escrever diferentes expressões lambda em valores (**val**), que dado um inteiro indicam se o mesmo é:
- par
- ímpar
- divisor de 10

Teste as funções desenvolvidas isoladamente e para conjuntos de valores.
<!--
{% include code code="
println(isEven(7))
(1..10)
        .filter { i -> isEven(i) }
        .forEach { println(it) }
"
%}
-->

### 4. Função de ordem superior
Escreva uma variante da função *sumRange* de ordem superior, por forma a que seja permitido especificar quais os números que serão somados segundo algum critério opcional (p.e. pares, ímpares, primos, etc). O critério será passado numa expressão lambda do tipo do passo anterior. Na ausência de critério, todos os números do intervalo serão considerados no somatório.

Teste a função com os critérios da alínea anterior, por exemplo:
{% include code code="
val s = sumRange(1, 5) { isEven(it) } // 6
"
%}

### 5. Composição funcional
Escreva uma função para verificar se um número é perfeito, recorrendo a funcões anteriores.

Teste a função para os primeiros 10000 números naturais.

<!--
{% include code code="
(1..10000)
        .filter { n -> isPerfectNumber(n) }
        .forEach { n -> println(n) }  // 6, 28, 496, 8128
"%}
>

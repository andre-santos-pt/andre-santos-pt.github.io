---
title: Variáveis e blocos de instruções
---

No [primeiro capítulo](../01_expressoes) começámos por introduzir a definição de valores, utilizando **val**. Vários valores podem ser definidos em sequência, sendo a sua avaliação de cima para baixo.

<kotlin>
val a = 3
val b = a + 1
val c = a
</kotlin>

Uma **variável** é um espaço em memória, tal como os valores, mas que contudo, pode ser alterado (variar) durante a execução de um programa. Uma variável é declarada mediante a palavra reservada **var**, muito semelhante aos valores.

<kotlin>
var v = 3
</kotlin>

# Atribuição

A instrução para alterar o valor de uma variável é designada por **atribuição**. A sintaxe consiste apenas em escrever o nome da variável, seguido de **=** e do novo valor. Ao fazer uma atribuição, o valor que a variável detinha é substituído e perde-se. É possível fazer atribuições ilimitadas a uma variável.

## Substituição
<kotlin>
var v = 8
v = 6
</kotlin>

O valor de uma variável só é alterado mediante uma instrução de atribuição à mesma, não existindo outra forma de isso acontecer.

## Acumulação
<kotlin>
var v = 4
v = v + 4
</kotlin>

## Incrementação e decrementação

Uma **incrementação** consistem em atribuir a uma variável o valor correspondente ao valor anterior mais 1, ou seja, uma acumulação de 1.

<kotlin>
var v = 0
v = v + 1
</kotlin>

... e analogamente para **decrementação**


# Bloco de instruções

Um **bloco** consiste numa sequência de instruções, delimitada por chavetas (**{** ... **}**). Um bloco é executado de cima para baixo.

<kotlin>
{
    var v = 5
    var t = v + 1
    v = 2
}
</kotlin>


# Variáveis em funções

As variáveis são utilizadas em funções para concretizar algoritmos. Para as utilizar, a função terá que ser definida com um bloco de instruções. A juntar aos parâmetros, as variáveis declaradas irão determinar o espaço em memória que a função necessita para executar.

<kotlin>
fun square(n:Int) {
    var v = n
    v = v * v
    return v
}
</kotlin>

# Bloco de instruções alternativo
No [primeiro capítulo](01_expressoes) foi introduzida a expressão condicional **if-else**. As mesmas palavras reservadas são também utilizadas para definir caminhos alternativos na execução de um bloco.

<kotlin>
fun abs(n: Int) {
  if(n < 0) {
    return -n
  }
  else {
    return n
  }
}
</kotlin>

<kotlin>
fun abs(n: Int) {
  if(a < 0) {
    return -a
  }
  return a
}
</kotlin>



# Fluxo de execução

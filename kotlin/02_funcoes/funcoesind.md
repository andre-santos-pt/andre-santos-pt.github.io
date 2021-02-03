---
title: Funções independentes
---

# Definição

Uma função, no sentido matemático do termo, permite obter um valor (contradomínio) dado um valor do seu domínio. Por exemplo, a função *f(x) = x*<sup>2</sup> fornece o quadrado de *x*, e desta forma, *f(4)* correspondendo a *16*, *f(5)* a *32*, etc. Em programação, *x* é designado por **parâmetro**, ao passo que valores concretos para o mesmo (4, 5, etc) são referidos como **argumentos**.

Utilizando a palavra reservada **fun** (abreviatura de *function*), é possível definir a função acima. O identificador *f* corresponde ao nome da função, entre parêntesis está definido o parâmetro *x* do tipo *Double*. À direita do símbolo **=** está a expressão que define a função.

{% include code file="square.kt" %}

Note-se que enquanto que no [capítulo anterior](../01_expressoes/expressoesdep) as expressões podiam depender de outras definições de valores, aqui a situação é semelhante, no sentido em que a expressão que define a função depende dos seus parâmetros (*x* no exemplo).

## Identificador da função

Ao definir uma função, é necessário definir um identificador, i.e. um *nome* que a identifica. No primeiro exemplo apresentado, a escolha do nome *f* baseou-se apenas no exemplo da Matemática. Na verdade, não é uma boa escolha, pois *f* não informa nada sobre o que a função calcula.

<u>O nome de uma função é muito importante, e deve refletir o que a mesma calcula.</u> Existem duas razões principais para esta importância:
- Quando alguém tem que entender código escrito por terceiros, o nome da função será das primeiras coisas que lerá, e isso poderá fornecer uma pista para o propósito da função, e consequentemente, ajudar a entender a sua definição.
- Uma função utilizada na definição de outras, irá ser identificada pelo seu nome, sendo que a qualidade do mesmo facilitará (ou não) a leitura e compreensão dessas funções que a utilizam. (Imagine-se a confusão que seria nas fórmulas de uma folha de cálculo se as funções para calcular o somatório e a média se chamassem *f1* e *f2*.)

À luz deste princípio, o último exemplo não tem certamente um identificador apropriado. Algo como o seguinte seria uma escolha melhor, dado que *square* informa que se trata do quadrado de um número.

{% include code file="square2.kt" %}


## Parâmetros
Uma função poderá ter um ou mais parâmetros, sendo que cada um terá um identificador único no conjunto de parâmetros. É também obrigatório indicar qual o tipo de cada parâmetros.

{% include code file="avg.kt" %}

## Tipo de retorno
Uma função devolve um valor de um determinado tipo. Nalguns casos, como os exemplos acima, esse tipo pode ser inferido através da expressão. No caso de *square* o tipo de retorno será inteiro (*Int*), ao passo que o de *avg* será decimal (*Double*).

## Instrução de Retorno

**return**
{% include code code="
fun square(n: Int): Int {
    return n * n
}
"%}


# Funções baseadas em expressão condicional

{% include code file="abs.kt" %}

{% include code file="max.kt" %}


# Funções baseadas em expressão booleana

{% include code file="isOdd.kt" %}

{% include code file="inInterval.kt" %}

# Parâmetros opcionais

# Invocação

À utilização de uma função no contexto de um programa designamos por **invocação**, vulgarmente também referida como *chamar uma função* (do Inglês, *call*). A experimentação das funções da secção anterior com valores concretos consistiu no fundo em fazer invocações às mesmas. Dado que é devolvido um valor, a invocação consiste numa expressão cujo tipo é dado pelo tipo de retorno.

A sintaxe para uma invocação consiste no nome da função, seguido de parêntesis contendo uma lista de *n* argumentos separados por vírgula, sendo *n* o número de parâmetros da função. Cada argumento é uma expressão cujo tipo tem ser o mesmo que o tipo do parâmetro correspondente.

<code>
val m = max(a = 4, b = -2)
</code>

O exemplo seguinte ilustra como podemos definir um valores com o resultado da invocação de funções (dadas como exemplo na [secção anterior](funcoesind)).

<code>
val a = abs(4)
val b = abs(-2)
val m = max(a, b)
</code>

A forma de calcular os valores em sequência é a mesma explicada anteriormente, mas neste caso a obtenção do valor é delegada na função em questão. As invocações de *abs* apenas têm um argumento (pois a função tem apenas um parâmetro), ao passo que a invocação de *max* tem dois.

As invocações são expressões, e desta forma, podem ser utilizadas como argumentos. Quando assim é, as invocações são avaliadas da esquerda para a direita, e após é avaliada a função. No exemplo seguinte, seria calculado *abs(4)*, 4, seguido de *abs(-2)*, -2, e seguido de *max(4, -2)*, 4, utilizando o resultado das invocações anteriores.

<code>
val m = max(abs(4), abs(-2))
</code>



# Sumário

As funções independentes simples, tais como as baseadas em expressões, são bastante utilizadas em programas reais, apesar da sua simplicidade. A possibilidade de "dar um nome" a um simples cálculo pode facilita a comunicação da intenção do código. Por exemplo 

Na próxima secção
Na [próxima secção](bwimages) ilustraremos a sua aplicação com efeitos simples sobre imagens.

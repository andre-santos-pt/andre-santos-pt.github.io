---
title: Funções independentes
layout: default
---

# Definição

Uma função, no sentido matemático do termo, permite obter um valor (contradomínio) dado um valor do seu domínio. Por exemplo, a função *f(x) = x*<sup>2</sup> fornece o quadrado de *x*, e desta forma, *f(4)* correspondendo a *16*, *f(5)* a *32*, etc. Em programação, *x* é designado por **parâmetro**, ao passo que valores concretos para o mesmo (4, 5, etc) são referidos como **argumentos**.

Utilizando a palavra reservada **fun** (abreviatura de *function*), é possível definir a função acima. O identificador *f* corresponde ao nome da função, entre parêntesis está definido o parâmetro *x* do tipo *Double*. À direita do símbolo **=** está a expressão que define a função.

{% include code file="square.kt" %}

Note-se que enquanto que no [capítulo anterior](../01_expressoes/expressoesdep) as expressões podiam depender de outras definições de valores, aqui a situação é semelhante, no sentido em que a expressão que define a função depende dos seus parâmetros (*x* no exemplo).


## Identificador da função

Ao definir uma função, é necessário definir um identificador, i.e. um *nome* que a identifica. No primeiro exemplo apresentado, a escolha do nome *f* baseou-se apenas no exemplo da Matemática. Na verdade, não é uma boa escolha, pois *f* não informa nada sobre o que a função calcula.

O nome de uma função é *muito importante*, e deve refletir o que a mesma calcula. Existem duas razões principais para esta importância:
- Quando alguém tem que entender código escrito por terceiros, o nome da função será das primeiras coisas que lerá, e isso poderá fornecer uma pista para o propósito da função, e consequentemente, ajudar a entender a sua definição.
- Uma função utilizada na definição de outras, irá ser identificada pelo seu nome, sendo que a qualidade do mesmo facilitará (ou não) a leitura e compreensão dessas funções que a utilizam. (Imagine-se a confusão que seria nas fórmulas de uma folha de cálculo se as funções para calcular o somatório e a média se chamassem *f1* e *f2*.)

À luz deste princípio, o último exemplo não tem certamente um identificador apropriado. Algo como o seguinte seria uma escolha melhor, dado que *square* informa que se trata do quadrado de um número.

{% include code file="square2.kt" %}


## Parâmetros
Uma função poderá ter um ou mais parâmetros, sendo que cada um terá um identificador único no conjunto de parâmetros. É obrigatório indicar qual o tipo de cada parâmetro.

{% include code code="
fun avg(a: Double, b: Double) = (a + b) / 2.0
"
%}

## Tipo de retorno
Uma função devolve um valor de um determinado tipo. Nalguns casos, como os exemplos acima, esse tipo pode ser inferido através da expressão, tal como na [definição de valores](../01_expressoes/expressoescon#inferencia). No caso de *square* o tipo de retorno será inteiro (*Int*), ao passo que o de *avg* será decimal (*Double*).

## Instrução de Retorno
Nos casos onde a função não pode ser definida apenas através de uma expressão, teremos de definir o corpo da função entre chavetas, e  utilizar a instrução **return** para definir o valor a devolver. Nesta situação, é obrigatório indicar explicitamente o tipo da função após a lista de parâmetros, tal como no exemplo seguinte.

{% include code code="
fun square(x: Double): Double {
    return x * x
}
"%}

# Invocação

À utilização de uma função no contexto de um programa designamos por **invocação**, vulgarmente também referida como *chamar uma função* (do Inglês, *call*). Dado que é devolvido um valor, a invocação consiste numa expressão cujo tipo é dado pelo tipo de retorno da função.

A sintaxe para uma invocação consiste no nome da função, seguido de parêntesis contendo uma lista de *n* argumentos separados por vírgula, sendo *n* o número de parâmetros da função. Cada argumento é uma expressão cujo tipo tem de ser o mesmo que o tipo do parâmetro correspondente (ou compatível).

{% include code code="
val a = square(2.0)
val b = square(a)
val c = square(b * 3.5)
"
msg="Três exemplos de invocação utilizando três tipos de expressão decimal como argumento. a) valor literal decimal; b) valor do tipo decimal, obtido pela invocação; c) expressão aritmética do tipo decimal, pois <b>b</b> e 3.5 são decimais. As invocações de apenas têm um argumento (pois a função tem apenas um parâmetro)."
%}

A forma de calcular os valores em sequência é a mesma explicada [anteriormente](../01_expressoes/expressoesdep), mas neste caso a obtenção do valor é delegada na função em questão.

As invocações são expressões, e desta forma, podem ser utilizadas como argumentos. Perante uma expressão *f(g(...))*, onde o argumento para uma invocação de uma função *f* é uma invocação de uma função *g*, *g(...)* irá primeiro ser avaliada para dar origem ao argumento de *f*, para depois então *f(...)* poder ser avaliada.

{% include code code="
val a = square(avg(4.0, 5.0))
val b = square(square(square(2.0)))
"
msg="O cálculo do seguintes valores desenvolvem-se da seguinte forma:<br>
square(<u>avg(4.0, 5.0)</u>)) &rarr; square(4.5) &rarr; 20.25

<br>
square(square(<u>square(2.0)</u>)) &rarr; square(<u>square(4.0)</u>) &rarr; <u>square(16.0)</u>  &rarr; 256.0"
%}

## Erros

Quando uma invocação não obedece aos tipos dos parâmetros da função que está a ser chamada, teremos um erro de compilação.

{% include error code="
val a = square(2)
val b = square(2.0, 4.0)
"
msg="Não correspondência de argumentos/parâmetros.<br> a) um valor decimal está a ser fornecido (2.5), ao invés de um inteiro; b) estão a ser fornecidos dois inteiros, ao passo que a função apenas tem um parâmetro."
%}

Por outro lado, poderemos ter um desalinhamento entre o tipo declarado de um valor e da função que o define. A invocação em si não tem qualquer problema, mas a compatibilidade entre o tipo do valor e o retorno.

{% include error code="
val c: Boolean = square(2)
"
msg="Desalinhamento entre tipo de valor e invocação. A função devolve um inteiro, ao passo que o valor <b>c</b> está declarado como booleano."
%}

# Funções baseadas em expressão condicional
Muitas funções simples podem ser definidas utilizando uma expressão condicional (**if-else**). Apresentamos aqui três exemplos de tais funções.

{% include code file="abs.kt"
msg="Exemplo: Função para obter o valor absoluto (módulo) de um número inteiro."%}

{% include code file="max.kt"
msg="Exemplo: Função para obter o valor máximo entre dois inteiros."%}

{% include code code="
fun ageGroup(age: Int) =
          if(age < 12) 'i'
          else if(age < 15) 'c'
          else if(age < 18) 'j'
          else 's'
"
msg="Exemplo: Obter código de escalão etário com base numa idade. Este caso reflete uma situação típica que consiste em estabelecer uma correspondência entre valores."
%}


# Funções baseadas em expressão booleana

{% include code anchor="isEven" code="
fun isEven(n: Int): Int = n % 2 == 0
"
msg="Exemplo: Função para verificar se um número é par, recorrendo ao resto da divisão por 2."
%}

{% include code file="inInterval.kt"
msg="Exemplo: Função para verificar se um número <b>n</b> está contido num intervalo [<b>min</b>, <b>max</b>]"
%}






# Sumário

As funções independentes simples, tais como as baseadas em expressões, são bastante utilizadas em programas reais, apesar da sua simplicidade. A possibilidade de "dar um nome" a um simples cálculo pode facilita a comunicação da intenção do código. À medida que lidamos com situações mais complexas, necessitaremos de [funções dependentes](funcoesdep) que se baseiam noutras funções.

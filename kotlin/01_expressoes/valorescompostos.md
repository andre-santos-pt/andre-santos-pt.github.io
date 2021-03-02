---
title: Valores compostos
---

Nas secções anteriores abordámos valores simples, isto é, que consistem num único número/valor. Dependendo do problema que estamos a resolver, um valor representará alguma coisa (uma tonalidade, temperatura, dimensão, etc). Porém é frequente que os elementos com que temos que lidar sejam definidos como uma composição de valores. Por exemplo, um ponto num espaço bi-dimensional será composto por dois valores (*x* e *y*), ao passo que num espaço tri-dimensional serão três (acresce *z*). Outros exemplos são as cores RGB (*red*, *green*, *blue*) com três valores, dimensões, intervalos, etc.


# Definição de tipos de valor

Para ilustrar a definição de um tipo de valor composto vamos utilizar a noção de ponto bi-dimensional de coordenadas inteiras. Ao definir um tipo de valor teremos que escolher um nome para o mesmo. O nome deve refletir aquilo que um valor do tipo em questão representa. Neste caso utilizaremos *Point*. Atenção que por norma o nome deve referir-se a um valor no singular, ao invés do conjunto (por exemplo, *Points* poderá dar a entender que o valor representa um conjunto de vários pontos).

A sintaxe consiste em utilizar a declaração **data class**, seguido do nome do tipo de valor, e de uma lista de valores que o compõem, utilizando **val**. Designaremos estes valores do tipo composto por *campos*. Atenção que neste caso, cada campo tem que explicitamente indicar qual o seu tipo.

{% include code code="
data class Point(val x: Int, val y: Int)
" %}

Um valor composto pode ser obtido através do nome do tipo, seguido de um valor para cada campo, atribuindo 3 ao campo *x* e 4 ao campo *y*.

{% include code code="
val p = Point(3, 4)
" %}



## Valores por omissão
Os valores compostos podem definir valores por omissão para alguns dos seus campos. Quando assim é, o fornecimento de valores pode ser omitido, sendo que será utilizado o valor por omissão.

{% include code code="
data class Color(val red: Int = 0, val green: Int = 0, val blue: Int = 0)
"
%}

{% include code code="
val red1 = Color(255, 0, 0)
val red2 = Color(255, 0)
val red3 = Color(255)
val black = Color()
"
msg="Temos quatro formas de inicializar o tipo de valor acima (isto sem recorrer à possibilidade da secção seguinte), pois a omissão de valores tem que ser feita de forma contínua da direita para a esquerda. Os três valores para a cor vermelho são equivalentes."
%}

## Atribuição de campos por nome

Para efeitos de legibilidade, especialmente quanto estamos perante valores com diversos campos, é possível utilizar uma sintaxe que ao invés de utilizar apenas a ordem de valores passados para os atribuir aos campos, permite que os mesmos sejam identificados por nome.

{% include code code="
val red = Color(red = 255)
val yellow = Color(green = 255, red = 255)
val gray = Color(red = 128, green = 128, blue = 128)
"
msg="Perante valores para cores RGB, poderíamos utilizar a atribuição de campos por nome, oferecendo maior legibilidade. Podemos utilizar uma ordem diferente da dos campos, embora isso não seja muito comum, podendo até confundir quem lê o código, dadas as diferentes formas para o mesmo propósito. "%}


## Validação de valores

Embora não seja muito frequente, um tipo de valor composto pode ter apenas um campo. Isto geralmente acontece quando se pretende restringir os valores possíveis na gama de um tipo de valor elementar. Por exemplo, embora um número decimal seja apropriado para representar uma percentagem (0-100%), não é qualquer valor que é válido. Porém, apenas utilizando Double não temos forma de restringir isso.

No seguinte exemplo, vemos a definição de um tipo de valor para percentagens 0-100%, onde na inicialização (**init**) é incluída uma validação do valor (**require**) com uma expressão booleana.

{% include code code="
data class Percentage(val value: Double) {
    init {
        require(value >= 0.0 && value <= 1.0)
    }
}
" %}

{% include error code="
val perc = Percentage(3.4)
"
msg="Ao tentar obter o valor em tempo de execução, a validação não o permitirá."
exc="java.lang.IllegalArgumentException: Failed requirement."%}


## Campos de valores compostos

Nos exemplos acima, os campos são sempre de tipo elementar. Porém, podemos definir campos também eles de tipo de valor composto.

{% include code code="
data class Line(val from: Point, val to: Point)

val diagonal = Line(Point(0, 0), Point(100, 100))
"
msg="Exemplo: Tipo de dados para representar linhas entre dois pontos."
%}


# Manipulação de valores compostos

Esta forma de definir valores faz com que os mesmos sejam **imutáveis**, isto é, o valor dos seus campos não pode ser alterado (**val**). Embora seja possível definir tipos de valores **mutáveis**, onde os seus campos podem ser alterados após a definição do valor, desencorajamos essa possibilidade, pois é considerada uma boa prática ter tipos de valor imutáveis.

## Consulta

Dado um valor composto, podemos consultar o valor dos seus campos individualmente, utilizando um ponto (**.**) entre o valor e o seu campo.

{% include code code="
val p = perc.value
val r = yellow.red
"
%}

Outra possibilidade consiste em decompor o valor, obtendo os valores em separado (*destructuring*).
{% include code code="
val (r, g, b) = yellow
"
%}
A instrução acima é equivalente à seguinte sequência.
{% include code code="
val r = yellow.r
val g = yellow.g
val b = yellow.b
"
%}

A consulta de campos pode ser feita encadeando consultas de campos sucessivamente com a mesma sintaxe acima.

{% include code code="
val line = Line(Point(0,0), Point(10,  15))
val xEnd = line.to.x
"
%}

## Comparação

A comparação de igualdade/diferença entre dois valores compostos pode ser feita utilizando os operadores **==** e **!=** apresentados [anteriormente](expressoescon).

{% include code code="
val areSame = red1 == red2
val areDifferent = yellow != black
"
msg="Ambos os valores <b>areSame</b> e <b>areDifferent</b> serão verdadeiros."
%}

# Sumário
Esta secção abordou a definição de tipos de valor compostos. Esta primeira abordagem focou-se apenas no elementar, mais à frente mostraremos como é possível enriquecer os [tipos de valores com funções](../02_funcoes/funcoesvalores).

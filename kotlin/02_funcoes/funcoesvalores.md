---
title: Funções sobre valores compostos
---

No primeiro capítulo, abordámos [valores compostos](../01_expressoes/valorescompostos). Nesta secção iremos abordar como enriquecer tipos de valores compostos com funções.

Para ilustração, utilizaremos o tipo de valor *Point* que representa uma coordenada cartesiana.

{%include code code="
data class Point(val x: Double, val y: Double)
"
%}

Utilizando a forma de definir funções já abordada, podemos ter a definição da seguinte função para somar dois pontos.

{%include code code="
fun sum(a: Point, b: Point) = Point(a.x + b.x, a.y + b.y)
"
%}


{%include code code="
val a = Point(0.0, 0.2)
val b = Point(0.5, 1.0)
val c = sum(a, b)
"
%}

Esta forma de somar pontos não tem nenhum problema, porém, vamos observá-la por comparação às operações aritméticas. Os  valor elementares (*Int*, *Double*, etc) têm associadas a operações que nos permitem obter outros valores.  Essas operações são no fundo funções, que são invocadas com uma sintaxe própria. Por exemplo, podemos pensar em *a + b* como sendo uma invocação de uma função que soma dois números *a* e *b* (*plus(a, b)* --- notação prefixa (ou Polaca) -- *"mais a e b"*), devolvendo um novo valor resultante da adição.

Outra forma de conceber *a + b*, equivalente à anterior, é que estamos a somar *a* com o valor de *b*, obtendo um novo valor. Na sintaxe de Kotlin, isso será expresso na forma *a.plus(b)*. Note-se que o argumento *a* é "movido" para o início da expressão, e ficamos com uma ordem de operandos e operadores em linha com *a + b*, e por conseguinte, mais aproximada da forma como falamos (*"a mais b"* --- notação infixa).


# Funções associadas a tipos
Veremos então como podemos alcançar a possibilidade de manipular valores compostos com a forma infixa. A definição de um tipo composto pode incluir definições funções (entre as chavetas), como no seguinte exemplo, onde é definida uma função equivalente à *sum* acima.

{%include code code="
data class Point(val x: Double, val y: Double) {
  fun sum(p: Point) = ...
}
"
%}


{%include code code="
val c = a.sum(b)
"
%}

Ao utilizar esta forma, o que está a acontecer no fundo é uma função que implicitamente tem um parâmetro **this** (palavra reservada, e por isso, o código seguinte não seria válido). "This" significa "este", e logo, refere-se ao valor onde a função for invocada (*a* no caso acima).

{%include code code="
fun sum(this: Point, p: Point) = ...   // inválido, devido à utilização da palavra reservada this
"
%}

Desta forma, utilizamos **this** para nos referirmos ao valor onde a função foi invocada (lado esquerdo).

{%include code code="
data class Point(val x: Double, val y: Double) {
  fun sum(p: Point) = Point(this.x + p.x, this.y + p.y)
}
"
%}

A utilização de **this** pode ser omitida quando o campo referido não tem o mesmo identificador que um parâmetro da função, e logo, não constituindo ambiguidade.  Por outro lado, **this** pode ser utilizado de forma isolada se pretendermos devolver o próprio valor, ou invocar uma função sobre o mesmo.

{%include code code="
import kotlin.math.pow
import kotlin.math.sqrt

data class Point(val x: Double, val y: Double) {
  fun isOrigin() = x == 0.0 && y == 0.0

  infix fun sum(p: Point) =
       if(this.isOrigin()) p           // this.isOrigin() equivalente a isOrigin()
       else if(p.isOrigin()) this
       else Point(x + p.x, y + p.y)

  fun sum(x: Double, y: Double) =
       if(x == 0.0 && y == 0.0) this
       else Point(this.x + x, this.y + y)

  infix fun subtract(p: Point) = sum(-p.x, -p.y)  // sum(...) equivalente a this.sum(...)

  infix fun distanceTo(p: Point) = sqrt((x - p.x).pow(2) + (y - p.y).pow(2))
}
"
msg="Exemplo: Tipo de valor composto para pontos cartesianos com funções associadas. O próprio valor (ou o passado como argumento) são devolvidos quando a soma não resulta num valor diferente. A função para cálculo da distância utiliza funções da biblioteca de matemática (kotlin.math)."
%}

# Invocação com notação infixa

Tal como ilustrado no exemplo acima, podemos utilizar o modificador **infix** para indicar que a função pode ser invocada com notação infixa. Isto só se aplica no caso de funções com exatamente um parâmetro.

Esta possibilidade sintática permite que o nome da função seja utilizado como se se tratasse de um operador (+, -, etc). A parte esquerda é o valor cujo tipo define a função, o operador corresponde ao nome da função , e a parte direita consiste no argumento (para o parâmetro único).

{% include code code="
val a = Point(0.0, 0.2)
val b = Point(0.5, 1.0)
val dist = a distanceTo b
"
%}

# Utilização de operadores existentes
Tal como os valores numéricos utilizam operadores com os símbolos +, -, etc, também os tipos de valor que definimos o podem fazer. Para tal, recorremos ao modificador **operator** seguido da definição de uma função com identificador predefinido para cada operador. No caso do operador binário **+** é *plus*, e do operador binário **-** é *minus*.

{% include kref link="https://kotlinlang.org/docs/operator-overloading.html"
msg="Identificadores para definição de operadores."
%}

{% include code code="
data class Point(val x: Double, val y: Double) {
    // ...

    operator fun plus(p: Point) = sum(p)

    operator fun minus(p: Point) = subtract(p)
}
"
msg="Exemplo: Continuação da classe anterior, definindo dois operadores (+ e -) com base nas funções existentes."
%}

O acrescento apresentado no exemplo permitirá que os pontos possam ser somados e subtraídos utilizando a mesma notação que os números.

{% include code code="
val p1 = Point(3.1, 2.0) + Point(3.3, 1.0)
val p2 = p1 - Point(0.1, 0.1)
"
%}

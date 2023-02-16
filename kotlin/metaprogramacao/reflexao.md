---
title: Reflexão
image: mirror.png
---

As primitivas de reflexão em Kotlin têm como base as do Java, sendo possível utilizar diretamente as deste. Contudo, são definidas classes adicionais próprias de Kotlin, que sumarizamos no seguinte diagrama UML.

*KType* representa a presença de um tipo, que pode estar marcado como *nullable* ou não (utilizando **?**). O tipo propriamente dito é dado por *KClass* (ou *KTypeParameter* para os genéricos).  As classes e os seus membros podem ter diferentes níveis de visibilidade (*KVisibility*). Uma classe é composta por membros, sejam propriedades (*KProperty*) ou funções (*KFunction*), bem como construtores (que são tratados como funções). Todos estes são subtipos de *KCallable*, isto é, algo que se pode invocar e devolve algo (*returnType*), sendo o primeiro argumento da invocação o objeto alvo. As funções e construtores poderão ter parâmetros (*KParameter*).

![](uml.png)

# Reflexão estática
A reflexão estática é aquela que consiste em analisar elementos de um programa, mas sem efetuar nenhum tipo de operação com os mesmos.

Dada uma classe/módulo, é-nos permitido saber:
- quais as suas propriedades (nome, tipo, modificadores)
- quais os construtores disponíveis (tipo de parâmetros)
- quais as operações disponíveis (nome, tipo de parâmetros, modificadores)

Podemos também obter as anotações presentes em cada um dos elementos acima, um tema a abordar na [próxima secção](anotacoes).

## Classe
O primeiro passo para fazer uso de reflexão é obter uma referência para uma classe que pretendemos analisar, utilizando **::class**.

{% include code code="
val clazz: KClass<String> = String::class
"
%}

No exemplo acima a classe é conhecida, porém muitas vezes queremos trabalhar de forma genérica com objetos cujas classes não conhecemos. Desta forma, podemos interrogar um objeto pela sua classe.


{% include code code="
class Point(val x: Int, val y: Int) {
    constructor() : this(0, 0)

    val isOrigin: Boolean get() = x == 0 && y == 0

    fun moveDown() = Point(x, y + 1)
    fun moveRight() = Point(x + 1, y)
    fun sum(x: Int, y: Int) = Point(this.x + x, this.y + y)
}

val obj = Point(3, 2)
val clazz = obj::class
"
%}

## Propriedades
Podemos aceder às propriedades da classe através de *declaredMemberProperties*, contendo objetos *KProperty*. Esta forma de consulta excluí as propriedades herdadas (para tal, utilizar *memberProperties*).

{% include code code="
val propNames = clazz.declaredMemberProperties.map { it.name } // [x, y, isOrigin]
"
%}


## Construtores
Os construtores de uma classe podem ser obtidos através de *constructors*, sendo que existirá sempre pelo menos um construtor. Ainda que a classe não defina um construtor, existirá sempre um primário por omissão. O código seguinte obtém o número de construtores da classe.

{% include code code="
val nConstructors = clazz.constructors.size // 2
"
%}

## Operações
As operações disponíveis de uma classe podem ser obtidas através de *declaredMemberFunctions*. Tal como nas propriedades, esta forma de consulta excluí as funções herdadas (para tal, utilizar *memberFunctions*). O seguinte exemplo faz uma procura de funções que não tenham parâmetros.

{% include code code="
val funNoParams = clazz.declaredMemberFunctions.filter {it.valueParameters.isEmpty()} // moveDown(), moveRight()
"
%}

Ao consultar os parâmetros com *valueParameters* estamos a excluir o parâmetro implícito (*this*) (para o incluir utilizaríamos *parameters*).



# Reflexão dinâmica
A reflexão dinâmica é aquela que, dados os elementos estáticos recolhidos da forma explicada anteriormente, os utiliza de forma indireta, isto é, sem *depender* dos identificadores concretos.  

## Propriedades
O seguinte exemplo recolhe o nome de cada propriedade, sendo invocado *call* com o objeto do qual se pretende a propriedade. Se for passado um objeto cuja classe não tem a propriedade teremos um erro de execução.

{% include code code="
val propValues = clazz.declaredMemberProperties.map { Pair(it.name, it.call(point)) } // [(isOrigin, false), (x, 2), (y, 3)]
"
%}

## Construtores
No seguinte exemplo apresentamos duas formas de invocar construtores por reflexão. No primeiro caso, é obtido o construtor primário (*primaryConstructor*), e invocado como uma função. O segundo caso consiste numa operação de conveniência para invocar o construtor sem argumentos. Caso não exista, teremos um erro de execução. Logicamente, os argumentos passados na chamada terão que ser compatíveis com os parâmetros do construtor.

{% include code code="
val c1 = clazz.primaryConstructor!!.call(2, 3)  // equivalente: Point(2, 3)
val c2 = clazz.createInstance()           // equivalente: Point()
"
%}

## Operações
Por fim, ilustramos como invocar uma operação por reflexão, que é semelhante à invocação de construtor. Contudo, realçamos que perante uma operação num objeto é necessário passar o mesmo (instância) como primeiro argumento.

{% include code code="
val p = clazz.declaredMemberFunctions.find {it.name == \"sum\"}?.call(Point(2, 3), 2, 1)  // Point(4, 4)
"
%}

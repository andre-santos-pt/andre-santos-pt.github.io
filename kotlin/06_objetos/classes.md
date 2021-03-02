---
title: Classes
---

Ao passo que na secção anterior abordámos a definição de *objeto*, nesta trataremos da noção de **classe**. Num dicionário, *classe* pode ser definida como "grupo de pessoas, animais ou coisas com atributos semelhantes" ou "categoria de funções da mesma natureza". Fazendo o paralelo para a programação, as coisas são os objetos e os atributos semelhantes são os dados neles contidos. A segunda definição é útil para enfatizar que as coisas podem ser simplesmente funções/procedimentos (e não conter dados necessariamente, veja-se os comparadores).

As classes são um mecanismo, fulcral em programação orientada para objetos, que visa resolver o problema da incoerência de dados através da definição de tipos de dados que garantem que a sua a coerência seja mantida. Esta possibilidade é considerada uma mais-valia que ajuda a gerir a complexidade na implementação de sistemas e coordenação entre módulos. Ao definir uma classe de objetos, a mesma deverá ser desenhada por forma a impossibilitar que as suas instâncias tenham um estado incoerente face àquilo que o objeto é suposto representar. Se tal acontece, então o objeto "sai fora" da classe pois já não obedece às características esperadas, e logo, a classe não estará bem desenhada. As condições que definem o que é considerado um estado válido do objeto são denominadas de *invariantes*, pois deverão ser sempre verdade (daí o nome invariante: são sempre verdadeiras, nunca variam).

A implementação de classes caracteriza-se por agregar dados (atributos) e operações válidas para esses mesmos dados. No momento imediato após a sua criação, um objeto deverá estar num estado coerente. Por outro lado, as operações disponíveis nunca deverão permitir que o objeto fique com dados incoerentes. Desta forma, as operações podem assumir que os dados estão coerentes, e produzirão resultados válidos com base neste pressuposto.


# Definição de classes

Sintaticamente, a definição de uma classe assemelha-se à definição de [valores compostos](../01_expressoes/valorescompostos) (porém, sem utilizar o modificador **data**).

Como exemplo, suponhamos um objeto contador (para já sem limite máximo). Um contador é no fundo um inteiro, porém queremos garantir que este é sempre maior ou igual a zero, e que só aumenta em uma unidade de cada vez.


## Propriedades
Os objetos são compostos apenas por um inteiro (variável *current*), que começa com o valor zero. Vamos referirmo-nos a estas características que definem o objeto como **propriedades**. Estas são definidas no corpo da classe, embora hajam outras possibilidades sintáticas.

{% include code code="
class CounterUnbound {
    var value: Int = 0
}
"
%}

É necessário fornecer sempre o valor inicial da propriedade. No exemplo, temos o valor atual da contagem a começar em zero.

## Operações
Operações representam as interrogações e acções que temos disponíveis no objeto, sendo importante a distinção entre **funções** (não alteram propriedades) e **procedimentos** (eventualmente alteram propriedades).

As funções devolvem um valor, ao passo que os procedimentos tipicamente não (poderão devolver um valor complementar para uma informação adicional). No seguinte exemplo temos a função *isZero* que responde verdadeiro quando o valor do contador está a zero, e o procedimento *inc* que incrementa o valor de contagem em uma unidade.

{% include code code="
class Counter {
    var value: Int = 0

    fun isZero(): Boolean = value == 0

    fun inc() {
      value++
    }
}
"
%}

{% include code code="
val c = Counter()
c.inc()
val v = c.value // 1
val zero = c.isZero() // false
"
%}

### Propriedades calculadas
Uma função sem parâmetros pode ser definida como uma **propriedade calculada**, que ao invés de ter o seu valor próprio, é calculada com base noutras propriedades. Desta forma, *isZero* poderia ser uma propriedade.

{% include code code="
class Counter {
    var value: Int = 0

    val isZero: Boolean = value == 0
}
"
%}

Neste caso, o acesso à propriedade é feito como se de uma propriedade normal se tratasse (sem parênteses).


{% include code code="
val c = Counter()
val zero = c.isZero // true
"
%}

## Encapsulamento de propriedades

A versão da classe apresentada até ao momento tem um problema, pois a lógica de um contador pode facilmente ser comprometida. No código seguinte, começamos por fazer uma incrementação (sem problemas), mas de seguida alteramos o valor para -5 (que não faz sentido como contagem). Ora, isto compromete a coerência do objeto face ao que se pretendia, e logo, é indesejável. O que gostaríamos é que a propriedade *value* não pudesse ser alterada arbitrariamente, mas sim apenas por incrementação.

{% include code code="
val c = Counter()
c.value++
c.value = -5
val v = c.value // -5
"
%}

A forma de controlar a manipulação de uma propriedade consiste em *encapsular* a mesma, isto é, fazer com que não seja possível manipulá-la em código externo ao da classe. Isso é possível recorrendo ao modificador **private**.

{% include code code="
class Counter {
    private var value: Int = 0
}
"
%}

Desta forma, o código anterior já não é válido.

{% include error code="
val c = Counter()
c.value = -5
val v = c.value
"
msg="Cannot access 'current': it is private in 'Counter'"
%}

Ao tornar um atributo privado, o mesmo não pode ser nem alterado, nem tão pouco consultado. No caso do contador, embora não seja desejável que o valor do atributo de contagem seja alterado externamente, faz sentido poder consultar o seu valor. Desta forma, podemos restringir o acesso apenas de escrita, anotando a definição com **private set**. A alteração do valor da contagem apenas poderá ser feita no código interno da classe, e assim sendo, apenas é modificado mediante o procedimento *inc*.

{% include code code="
class Counter {
    var value: Int = 0
        private set

    fun inc() {
        value++
    }
}
"
%}

{% include code code="
val c = Counter()
c.inc()
c.inc()
val v = c.value // 2
"
%}

## Inicialização
O exemplo apresentado anteriormente consiste em objetos que são criados sem fornecer qualquer informação. Porém, muitas vezes a criação de objetos requer informação para que o objeto exiba determinadas características.

Por forma a ilustrar a inicialização, consideremos uma versão do objeto contador anterior onde é possível fixar um valor máximo para a contagem, acima do qual não deverão ser permitidas incrementações. Sendo assim, ao criar um contador iremos fornecer um valor inteiro positivo para o máximo.

## Construtor
A passagem de valores para a criação de um objeto é feita mediante o **construtor**, composto por parâmetros, enumerados da mesma forma que nas funções. Este é o construtor *primário* (principal), e podem ser definidos outros alternativos. Esses parâmetros podem ser utilizados nos valores iniciais das propriedades.

{% include code code="
class CounterBounded constructor(max: Int) {
    val max: Int = max

    var value: Int = 0
        private set

    fun inc() {
        require(value < max) { \"value on max\" }
        value++
    }
}
"
%}

Ao inicializar o objeto passamos o valor desejado, da mesma forma que passaríamos argumento a uma função.

{% include code code="
val c = CounterBounded(10)
val m = c.max // 10
"
%}

Normalmente, a palavra reservada **constructor** pode ser omitida. Por outro lado, no caso frequente de o valor do argumento ser diretamente atribuído a uma propriedade, podemos utilizar a forma dos campos dos [valores compostos](../01_expressoes/valorescompostos), declarando diretamente **val** no parâmetro, e dispensando a definição da propriedade.

{% include code code="
class CounterBounded(val max: Int) {
    var value: Int = 0
        private set
    // ...
}
"
%}

### Bloco de inicialização
Para efeitos de inicializações mais complexas ou para validação de argumentos, podemos utilizar o bloco de inicialização (**init**). Este bloco executará após o construtor primário.

{% include code code="
class CounterBounded(val max: Int) {
    // ...
    init {
      require(max > 0) { \"max must be positive\" }
    }
}
"
%}

### Construtores secundários
Para além do construtor primário, podemos ter outros alternativos, que forçosamente serão baseados no primário. Todos os construtores *secundários* têm que invocar o construtor primário (**this(...)**). Estes construtores executam após o bloco de inicialização (caso exista).

{% include code code="
class CounterBounded(max: Int) {
    val max: Int = max
    var value: Int = 0
        private set

    init {
        require(max > 0) { \"max must be positive\"}
    }

    constructor(max: Int, start: Int) : this(max) {
        value = start
        require(start >= 0) { \"start value must be >= 0\"}
    }
}
"
%}

Um construtor alternativo pode ser utilizado com a mesma sintaxe utilizada para um primário, obedecendo ao tipo dos parâmetros do mesmo.

{% include code code="
val c = CounterBounded(10, 5)
val m = c.value // 5
"
%}

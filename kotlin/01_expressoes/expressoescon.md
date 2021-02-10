---
title: Expressões constantes
---
As expressões constantes consistem provavelmente no aspeto da programação mais familiar à generalidade das pessoas, pois estamos habituados a lidar com as mesmas nas calculadoras. Numa calculadora podemos inserir a expressão *(12 + 13.2 + 15.7) / 3* para saber que ficaremos com 14 na nota final de uma disciplina, obtida através da média de 3 testes (*13.6*).

Designamos por **avaliação** o processo de obtenção do valor resultante de uma expressão. Designamos estas expressões como sendo **constantes**, pois o valor da sua avaliação nunca muda. A expressão acima resultará sempre em *13.6*.

# Expressões literais
Um simples número é considerado uma expressão *literal*. Isto é, não carece de avaliação, dado que o próprio número corresponde ao valor da expressão. Tal como quando afirmamos *significado literal*, pretendendo transmitir uma palavra ou frase no sentido exato do que ela representa, sem interpretações adicionais.

O seguinte exemplo ilustra este caso com a definição do valor de *pi* através da sua expressão literal. Utilizamos a palavra reservada **val** (abreviatura de *value* [valor]), para definir um espaço em memória para guardar um valor.

{% include code code="val pi = 3.1415926" %}


# Definição de valores
Em programação, as expressões surgem em vários contextos, sendo o mais simples a atribuição do valor resultante da avaliação de uma expressão a um espaço em memória. Este espaço em memória é identificado no programa através de um nome.

{% include code code="val mean = (12 + 13.2 + 15.7) / 3" %}

O símbolo **=** não tem o mesmo significado do que numa equação. Neste caso, é utilizado para representar a **atribuição** de uma expressão a um valor. Neste exemplo, associámos o valor *13.6*, resultante da avaliação da expressão, ao identificador *mean* (média).

A noção de **variável** está intrinsicamente ligada a programação, e dessa forma, podemos associar o código acima a esse conceito, dada a similaridade sintática com outras linguagens. Porém, ao definir valores com **val** não estamos a definir uma variável, pois nada irá *variar*, dado que não é permitido alterar o valor atribuído.  

{% include error code="mean = 13.4" msg="Redefinição de valor." %}


Um **identificador** válido consiste numa sequência de letras, sem espaços, podendo incluir número excepto no primeiro caracter. Quando queremos expressar um identificador utilizando mais do que uma palavra é comum seguir a forma *camelCase*, ou seja, um caracter maiúsculo representa o início de uma palavra para facilitar a leitura. Por convenção em Kotlin, os identificadores de valores devem começar com uma letra minúscula.

{% include code code="val maxValue = 1000" %}

Uma **palavra reservada** é um termo que não pode ser utilizado como identificador (de valores por exemplo), devido a fazer parte da linguagem. Ao tentarmos utilizar uma palavra reservada, iremos ter um erro de sintaxe.

{% include error code="val val = 1" msg="Identificador igual a palavra reservada."%}



<!--
# Valores em memória
Um valor é guardado num espaço reservado em memória, sendo a unidade de alocação o *byte* (8 *bits*). Desta forma, só é possível alocar um valor inteiro de *bytes* para guardar valores. Quanto maiores sejam os valores que precisamos de manipular, mais espaço é necessário.
-->


# Tipo de valores
As expressões ao serem avaliadas dão origem a valores, os quais podem ter **tipos** diferentes. Ao nível mais elementar, temos o tipo **numérico** para valores que representam números. Dentro deste tipo existem dois sub-tipos **inteiros** e **decimais**. Ambos os valores *mean* e *pi* são do tipo decimal. A separação entre a parte inteira e decimal é feita por meio de um ponto (vírgula não é válida).

Como exemplo de um valor inteiro, no seguinte exemplo é definido um valor para o número de dias da semana.

{% include code code="val weekdays = 7" %}

Outro tipo de valor elementar é o **booleano**, que representa valores de verdade, *true* (verdadeiro) e *false* (falso).

{% include code code="val married = false" %}

Por fim, existe o tipo de valor elementar para caracteres, sendo que cada valor representa o símbolo do caracter (no fundo, um número para o codificar). O caracter a ser representado tem que ser escrito entre plicas, como no exemplo seguinte.

{% include code code="val vowel = 'a'" %}

### Tipos de valor elementares mais comuns

| Tipo        | Valor       | Exemplo |
| ----------- | ----------- |--------|
| Int         | inteiro     | 0, 1, 10 |
| Double      | decimal     | 0.2, 1.2, .3 |
| Boolean     | booleano    | true, false  |
| Character   | caracter    | 'a', '1', '!'|

{% include kref msg="Tipos de valor elementares" link="https://kotlinlang.org/docs/reference/basic-types.html" %}

## <a name="inferencia"> Inferência de tipos
Cada expressão tem sempre um tipo associado, e o mesmo acontece para os valores definidos através de **val**. Ainda que o tipo de valor não esteja explícito, em Kotlin é feita
 *inferência de tipos*. Isto significa que com base no tipo da expressão atribuída, o tipo do valor é determinado. Ainda assim, é possível declarar o tipo pretendido junto com o valor que queremos definir.

{% include code code="val intValue: Int = 3 / 2" %}

Quanto o tipo declarado para o valor não corresponde ao tipo da expressão, temos um erro de compilação.

{% include error code="val intValue: Boolean = 0" msg="Tipos incompatíveis." %}



# Expressões aritméticas

Expressões **aritméticas** são aquelas cujos valores são números que resultam de operações aritméticas (adição, subtração, multiplicação, e divisão).

## Operadores

Uma operação aritmética é realizada mediante um operador, representado por um símbolo na linguagem. Um operador **binário**, tal como o nome indica (*bi*), atua sobre dois operandos.

Os símbolos utilizados são intuitivos, com a exceção do operador **%**, representado com um símbolo normalmente associado à percentagem, mas que neste contexto representa o resto da divisão inteira. Por outro lado, o operador de divisão **/** tem uma nuance, pois tem dois comportamentos possíveis, dependendo do tipos dos operandos. Perante dois operandos inteiros, efetuará uma divisão inteira, caso contrário, uma divisão decimal. A sintaxe de utilização de operadores binários consiste ter o *primeiro operando*, seguido do *operador*, e o *segundo operando*.


{% include code code="val players = 11 + 11" msg="O tipo do valor é inteiro, pois a expressão atribuída também o é."%}

{% include code code="val grade = (14.5 + 15.5) / 2" msg="O tipo do valor é decimal, pois embora a conta resulte num número inteiro (15.0), o tipo da expressão determinado é decimal dado que a divisão entre dois números decimais somados resultará num número decimal (apesar de neste caso particular resultar num inteiro)."%}


### Operatores Aritméticos

| Operador      | Operação | Exemplo | Avaliação |
| ----------- | ----------- | -------- | ------- |
| +           | adição                 | 2 + 3 | 5 |
| -           | subtração              | 2 - 4 | -2|
| *           | multiplicação          | 3 * 5 | 15|
| /           | divisão                | 5.0 / 2 | 2.5|
| /           | divisão inteira    | 11 / 3 | 3 |
| %           | resto da divisão inteira  | 11 % 3 | 2

Divisões por zero resultarão num erro de execução, pelo que o código deverá acautelar que isso não acontece.

{% include error msg="Divisão por zero." code="val mean = (14 + 15) / 0"
exc="java.lang.ArithmeticException: / by zero"%}


### Elemento simétrico (adição)

Ao passo que um operador binário atua sobre dois operandos, um operador **unário** atua sobre *um* operador apenas. Para aritmética, o único operador unário que faz sentido é simetria, i.e. trocando o sinal, expressa através do operador **-**. No seguinte exemplo, a expressão seria avaliada em *-10*.

{% include code code="val v = -(5*2)" %}

### Ordem de operações
Uma expressão é avaliada da esquerda para a direita. Porém, tal como em Matemática, a avaliação de uma expressão obedece a precedências de operadores, sendo que as operações multiplicativas (multiplicação e divisão) são efetuadas anteriormente às aditivas (adição e subtração). Desta forma, a expressão *14 + 16 / 2* é avaliada em *22* (e não em *15*), porque *16 / 2* é calculado primeiro, resultando em *14 + 8*.

Por forma a alterar a ordem das operações podemos recorrer a parênteses, envolvendo as sub-expressões a avaliar primeiro. Na continuação do exemplo anterior, ao termos *(14 + 16) / 2* seria obtido o valor *15*, porque a adição é efetuada antes da divisão.

{% include kref msg="Especificação de precedências" link="https://kotlinlang.org/docs/reference/grammar.html#expressions" %}



## Overflow
Cada valor é guardado num espaço em memória de capacidade fixa, sendo 4 *bytes* para os valores inteiros, permitindo guardar valores no intervalo [-2147483648, 2147483647]. Quanto é efetuada uma operação aritmética que ultrapassa os valores do intervalo, é excedida a capacidade, estamos perante *overflow* (transbordar). Ao contrário, por exemplo, da divisão por zero,  esta situação não dá origem a um erro de execução que interrompe o programa. O que acontece é que o valor em excesso para lá do limite passa para o extremo oposto do intervalo. Desta forma, o seguinte valor será avaliado em *-2147483648*, pois ao somar 1 ao valor máximo, obtemos o valor mínimo.

Embora o *overflow* não se manifeste num erro de execução, pode dar origem a um erro na lógica do que estamos a programar. Por exemplo, num programa que manipule somas avultadas de valores monetários, 2147 milhões poderá não ser suficiente (p.e. orçamentos de estado). Cálculos com *overflow* iriam dar origem a valores negativos, o que iria certamente comprometer o funcionamento do programa.

Problemas de *overflow* podem manifestar-se na compilação, quando o valor literal atribuído ultrapassa os limites.
{% include error msg="Overflow em tempo de compilação." code="val largeNumber = 2147483647 + 1" %}







<!--
# Determinação de tipo de expressão
-->

# Expressões booleanas

Uma expressão **booleana** é aquela cuja avaliação resulta num valor de verdade (booleana), obtido através da relações entre valores. Dois valores podem ser comparados para averiguar se diferem, ou como se relacionam em termos de grandeza.

### Operadores relacionais

| Operador      | Operação | Exemplo | Avaliação |
| ----------- | ----------- | -------- | ------- |
| ==           | igualdade              | 2 == 3 | false |
| !=           | diferença              | 2 + 1 != 2 | true |
| <           | menor                   | -1 < 0| true |
| <=           | menor ou igual    | 2 <= 0 | false |
| >           | maior    | 3 > 1 | true |
| >=           | maior ou igual  | 0 >= 0 | true



Valores booleanos podem ser combinados através de expressões lógicas, obtendo novos valores booleanos.

### Operadores lógicos

| Operador      | Operação | Expressão | Avaliação |
| ----------- | ----------- | -------- | ------- |
| &&          | conjunção condicional  | 1 > 0 && 2 > 0  | true |
| \|\|        | disjunção condicional  | -1 < 0 \|\| 1 > 0 | true |
| ^           | disjunção exclusiva    | 1 > 0 && 2 > 0  | false |


### Negação
Os valores lógicos podem ser **negados**, com vista a obter o valor simétrico, através do operador unário **!**.

{% include code msg="O resultado é falso, pois é negada a avaliação de 3 > 0 (true), obtendo o  (false)." code="val negative = !(3 > 0)" %}





<!--
## Ordem de operações

Os operadores relacionais comparam valores...

## Árvore de avaliação

# Conversão entre valores de diferentes tipos
-->

# Sumário
Esta secção abordou expressões apenas utilizando valores literais, mas ilustrando todos os operadores principais para manipular e relacionar valores. Escrever expressões constantes não reflete a atividade de programar, pois tipicamente lidamos com expressões que dependem de valores externos, que é o tópico da [secção seguinte](expressoesdep).

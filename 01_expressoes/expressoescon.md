---
title: Expressões constantes
---
As expressões constantes consistem provavelmente no aspeto da programação mais familiar à generalidade das pessoas, pois estamos habituados a lidar com as mesmas nas calculadoras. Numa calculadora podemos inserir a expressão *(12 + 13.2 + 15.7) / 3* para saber que ficaremos com 14 na nota final de uma disciplina, obtida através da média de 3 testes (*13.6*).

Designamos por **avaliação** o processo de obtenção do valor resultante de uma expressão. Designamos estas expressões como **constantes**, pois o valor da sua avaliação nunca muda. A expressão acima resultará sempre em *13.6*.

# Expressões literais
Um simples número é considerado uma expressão *literal*. Isto é, não carece de avaliação, dado que o próprio número corresponde ao valor da expressão, tal como quando afirmamos *significado literal*, pretendendo transmitir uma palavra ou frase no sentido exato do que ela representa, sem interpretações adicionais.

O seguinte exemplo ilustra este caso com a definição do valor de *pi* através da sua expressão literal. Utilizamos a palavra reservada **val**, abreviatura de *value* (valor), para definir um espaço em memória para guardar um valor.

{% include code content="val pi = 3.1415926" %}


# Definição de valores
Em programação, as expressões surgem em vários contextos, sendo o mais simples a atribuição do valor resultante da avaliação de uma expressão a um espaço em memória, sendo este identificado com um nome.

{% include code content="val mean = (12 + 13.2 + 15.7) / 3" %}

O símbolo **=** não tem o mesmo significado do que numa equação. Neste caso, é utilizado para representar a **atribuição** de uma expressão a um valor. Neste exemplo, associámos o valor *13.6*, resultante da avaliação da expressão, ao identificador *mean* (média).

Um **identificador** válido consiste numa sequência de letras, sem espaços, podendo incluir número excepto no primeiro caracter. Quando queremos expressar um identificador utilizando mais do que uma palavra é comum seguir a forma *camelCase*, ou seja, um caracter maiúsculo representa o início de uma palavra para facilitar a leitura. Por convenção em Kotlin, os identificadores de valores devem começar com uma letra minúscula.

{% include code content="val maxValue = 1000" %}

Uma **palavra reservada** é um termo que não pode ser utilizado como identificador (de valores por exemplo), devido a fazer parte da linguagem de programação. Ao tentarmos utilizar uma palavra reservada, iremos ter um erro de sintaxe.

{% include error msg="val val = 1" %}




# Valores em memória
Um valor é guardado num espaço reservado em memória, sendo a unidade de alocação o *byte* (8 *bits*). Desta forma, só é possível alocar um valor inteiro de *bytes* para guardar valores. Quanto maiores sejam os valores que precisamos de manipular, mais espaço é necessário.

...


# Tipo das expressões
As expressões ao serem avaliadas dão origem a valores, os quais podem ter **tipos** diferentes. Ao nível mais elementar, temos o tipo **numérico** para valores que representam números. Dentro deste tipo existem dois sub-tipos **inteiros** e **decimais**. Ambos os valores *mean* e *pi* são do tipo decimal. A separação entre a parte inteira e decimal é feita por meio de um ponto (vírgula não é válida).

Como exemplo de um valor inteiro, no seguinte exemplo é definido um valor para o número de dias da semana.

{% include code content="val weekdays = 7" %}

Outro tipo de valor elementar é o **booleano**, que representa valores de verdade, *true* (verdadeiro) e *false* (falso).

{% include code content="val married = false" %}

Por fim, existe o tipo de valor elementar para caracteres, sendo que cada valor representa o símbolo do caracter (no fundo, um número para o codificar). O caracter a ser representado tem que ser escrito entre plicas, como no exemplo seguinte.

{% include code content="val vowel = 'a'" %}


| Tipo        | Valor       | Exemplo |
| ----------- | ----------- |--------|
| Int         | inteiro     | 0, 1, 10 |
| Double      | decimal     | 0.2, 1.2, .3 |
| Boolean     | booleano    | true, false  |
| Character   | caracter    | 'a', '1', '!'|

Cada valor tem sempre um tipo associado, sendo que Kotlin tem *inferência de tipos*, significando que com base nos literais inseridos, o tipo do valor é determinado. Contudo, é possível declarar o tipo pretendido junto com o valor

{% include code content="val intValue : Int = 3 / 2" %}

{% include error msg="val intValue : Boolean = 0" %}



# Expressões aritméticas

Expressões **aritméticas** são aquelas cujos valores são números que resultam de operações aritméticas, tais como a adição, subtração, multiplicação, e divisão.

## Operadores

Uma operação aritmética é realizada mediante um operador, representado por um símbolo na linguagem. Um operador **binário**, tal como o nome indica (*bi*), atua sobre dois operandos.

Os símbolos utilizados são intuitivos, com a exceção do operador **%**, representado com um símbolo normalmente associado à percentagem, mas que neste contexto representa o resto da divisão inteira. Por outro lado, o operador de divisão **/** tem uma nuance, pois tem dois comportamentos possíveis, dependendo do tipos dos operandos. Perante dois operandos inteiros, efetuará uma divisão inteira. Caso contrário, uma divisão decimal.

A sintaxe de utilização de operadores binários consiste ter o *primeiro operando*, seguido do *operador*, e o *segundo operando*.

{% include code content="val players = 11 + 11" %}

| Operador      | Operação | Exemplo expressão | Avaliação |
| ----------- | ----------- | -------- | ------- |
| +           | adição                 | 2 + 3 | 5 |
| -           | subtração                       | | |
| +           | multiplicação                   | | |
| /           | divisão    | | |
| /           | divisão inteira    | 11 / 3 | 3 |
| %           | resto da divisão inteira  | 11 % 3 | 2


## Ordem de operações
Uma expressão é avaliada da esquerda para a direita. Porém, tal como em Matemática, a avaliação de uma expressão obedece a precedências de operadores, sendo que as operações multiplicativas (multiplicação e divisão) são efetuadas anteriormente às aditivas (adição e subtração). Desta forma, a expressão *14 + 16 / 2* é avaliada em *22* (e não em *15*), porque *16 / 2* é calculado primeiro, resultando em *14 + 8*.

Por forma a alterar a ordem das operações podemos recorrer a parênteses, envolvendo as sub-expressões a avaliar primeiro. Na continuação do exemplo anterior, ao termos *(14 + 16) / 2* seria obtido o valor *15*, porque a adição é efetuada antes da divisão.




## Erro de divisão por zero
{% include error title="divisão por zero" msg="val mean = (14 + 15) / 0" %}

## Overflow
Tal como explicado, para cada tipo de valor é reservado um espaço fixo em memória, sendo 4 *bytes* para os valores inteiros, permitindo guardar valores no intervalo [-2147483648, 2147483647]. Quanto é efetuada uma operação aritmética que ultrapassa os valores do intervalo, é excedida a capacidade, estamos perante *overflow* (transbordar). Ao contrário, por exemplo, da divisão por zero,  esta situação não dá origem a um erro de execução que interrompe o programa. O que acontece é que o valor em excesso para lá do limite passa para o extremo oposto do intervalo. Desta forma, o seguinte valor será avaliado em *-2147483648*, pois ao somar 1 ao valor máximo, obtemos o valor mínimo.

{% include error msg="val largeNumber = 2147483647 + 1" %}

Embora o *overflow* não se manifeste num erro de execução, pode dar origem a um erro na lógica do que estamos a programar. Por exemplo, num programa que manipule somas avultadas de valores monetários, 2147 milhões poderá não ser suficiente (p.e. orçamentos de estado). Cálculos com *overflow* iriam dar origem a valores negativos, o que iria certamente comprometer o funcionamento do programa.



## Elemento simétrico (adição)

Ao passo que um operador binário atua sobre dois operandos, um operador **unário** atua sobre *um* operador apenas. Para aritmética, o único operador unário que faz sentido é simetria, i.e. trocando o sinal, expressa através do operador **-**. No seguinte exemplo, a expressão seria avaliada em *-10*.

{% include code content="val v = -(5*2)" %}



## Resto a divisão inteira
No quotidiano não é frequente efetuarmos cálculos que envolvem o resto da divisão, e logo alguns iniciantes na programação ficam confusos com a utilidade desta operação. Porém, existem muitas situações onde o resto da divisão acontece. Por exemplo,

13 % 5 = 2

# Determinação de tipo de expressão

# Expressões booleanas

Uma expressão **booleana** é aquela cuja avaliação resulta num valor de verdade (booleana), obtidas através da relações entre valores. Dois valores podem ser comparados para averiguar se diferem, ou como se relacionam em termos de grandeza.


## Operadores relacionais

**Tabela Operatores**

| Operador      | Operação | Exemplo expressão | Avaliação |
| ----------- | ----------- | -------- | ------- |
| ==           | igualdade                 | 2 + 3 | 5 |
| !=           | diferença                        | | |
| <           | menor                   | | |
| <=           | menor ou igual    | | |
| >           | maior    | 11 / 3 | 3 |
| >=           | maior ou igual  | 11 % 3 | 2



## Operadores lógicos

| Operador      | Operação | Exemplo expressão | Avaliação |
| ----------- | ----------- | -------- | ------- |
| &&           | conjunção condicional                 | 2 + 3 | 5 |
| \|\|           | disjunção condicional                        | | |
| ^           | disjunção exclusiva                | | |

Os valores lógicos podem ser **negados**, com vista a obter o valor simétrico, através do operador unário **!**.

{% include code content="val negative = !(3 > 0)" %}


## Ordem de operações

Os operadores relacionais comparam valores...


## Árvore de avaliação


# Conversão entre valores de diferentes tipos


# Sumário
Esta secção expôs a escrita de expressões apenas utilizando valores literais, mas ilustrando todos os operadores principais para manipular e relacionar valores. Escrever expressões constantes não reflete a atividade de programar, pois esta envolve lidar permanentemente com expressões que dependem de valores externos, que é o tópico da [secção seguinte](expressoesdep).

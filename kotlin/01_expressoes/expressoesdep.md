---
title: Expressões dependentes
image: excel.png
---

> A [secção anterior](expressoescon) abordou expressões, apenas utilizando valores concretos. Nesta secção introduzimos expressões **dependentes**, isto é que dependem de valores externos, por sua vez obtidos por via de avaliação de expressões. As aplicações de folha de cálculo baseiam-se nesta noção, onde cada célula define uma expressão que pode utilizar valores provenientes de outras células.

# Sequência de definição de valores
Na secção anterior foi introduzida a definição de valores através de **val**, sendo cada expressão independente. Num programa, podemos definir uma sequência de valores, sendo que as expressões podem referir-se a valores definidos anteriormente. No seguinte exemplo temos a definição do valor *n*, seguida da definição de *nSquare* (*n* ao quadrado).

{% include code code="
val n = 3
val nSquare = n * n
" %}

 As definições são interpretadas de cima para baixo, e não possível que uma expressão se refira a um valor que ainda não foi definido anteriormente. Logo, a ordem das definições numa sequência *não* é indiferente.

 No último exemplo, se as definições estivessem na ordem inversa, teríamos um erro. Ao ser interpretada a seguinte sequência, surge um erro, pois no momento da definição de *nSquare* ainda não é conhecido o identificador *n*.

{% include error code="
val nSquare = n * n
val n = 3"
 msg="identificador indefinido (<b>n</b>)" %}

A **avaliação** de uma sequência consiste em substituir os identificadores de valores numa expressão pelos valores definidos anteriormente.

{% include code code="
val n = 3
val nSquare = n * n
val nCube = nSquare * n
"
msg="Sendo <b>n</b> igual a 3, em <b>nSquare</b> <b>n</b> será substituído por 3, e teremos 3 x 3, ou seja, 9. Caso fosse acrescentada a definição seguinte de <b>nCube</b>, que depende das duas anteriores, <b>nSquare</b> seria substituído por 9 e <b>n</b> por 3, resultando em 9 x 3, ou seja, 27. Caso o valor de <b>n</b> fosse outro, as outras duas avaliações seriam afetadas."
img="deps1.png" %}


{% include code code="
val pi = 3.1415926
val r = 4.5
val area = pi * r * r
val perimeter = 2 * pi * r
"
msg="Exemplo: cálculo de área e perímetro de circunferência."
img="deps2.png" %}



Os dois últimos exemplos consistem na definição de valores com dependências, ilustrados nas figuras com uma seta a tracejado. Uma seta de *a* para *b* denota que *a* depende de *b*, ou seja, necessita que *b* seja avaliada previamente. No primeiro caso, as dependências não permitem que as definições dos valores sejam ordenadas de outra forma. Já no segundo caso, existem quatro formas de ordenar as definições.




# Expressões condicionais
Outro tipo de expressão dependente é a **expressão condicional**. Tal como o nome indica, é uma expressão depende de uma condição, que por sua vez é também uma expressão, para determinar entre duas alternativas qual a expressão que será utilizada na avaliação.

As palavras reservadas para expressões condicionais são **if** e **else**, utilizadas em conjunto. No exemplo seguinte, a definição de *nAbs* (módulo de *n*) é feita através de uma expressão condicional. A interpretação é a seguinte:

{% include code code="
val n = -3
val nAbs = if(n < 0) -n else n
"
msg="Caso <b>n</b> < 0 seja avaliado em verdadeiro (<b>n</b> é negativo), então será calculado -<b>n</b> (valor simétrico: negativo passa a positivo), caso contrário será utilizado o valor do próprio <b>n</b>. Sendo <b>n</b> igual a -3, <b>nAbs</b> é avaliado em -(-3), ou seja, 3."
%}
As expressões contidas no **if** terão que ser booleanas, pois os valores de verdadeiro ou falso é que determinarão qual das expressões (esquerda ou direita) será utilizada.  Estas condições são normalmente referidas como **guardas**.

## Expressões aninhadas

Como exemplo, suponhamos um programa que tem que manipular escalões etários de uma certa modalidade, sendo esses *infantis* (sub-12), *cadetes* (sub-15), *juniores* (sub-18), e *seniores* (18+). Como forma de codificar o escalão vamos utilizar as letras (caracteres) iniciais, ou seja 'i', 'c', 'j', e 's'. Na seguinte definição, *ageGroup* é definido em função de *age*, através de expressões condicionais aninhadas.

{% include code code="
val age = 16
val ageGroup = if(age < 12) 'i' else (if(age < 15) 'c' else (if(age < 18) 'j' else 's'))
"

 msg="Sendo <b>age</b> igual a 16, o valor de <b>ageGroup</b> seria 'j' (juniores). Em primeiro lugar, <b>age</b> < 12 é avaliada, resultando em falso, a expressão à direita de <b>else</b> é avaliada. Sendo esta também uma condicional, <b>age</b> < 15 é avaliada também em falso, e o processo prossegue para a última expressão condicional. Neste caso, <b>age</b> < 18 é avaliada em verdadeiro (16 < 18), e logo, o resultado é 'j'."
  %}

 De realçar que as guardas dos **if** apenas são avaliadas até ao ponto em que uma é verdadeira. Por exemplo, para uma idade de infantis, por exemplo *11*, o facto da primeira guarda resultar em verdadeiro determina de imediato o resultado da avaliação da expressão.

 Os parênteses que estão incluídos em torno dos sucessivos pares **if-else** não são necessários, e foram escritos neste exemplo para facilitar a compreensão. Expressões aninhadas longas podem não ser fáceis de ler para muitas pessoas. Desta forma, uma forma típica de organizar o código para facilitar a leitura consiste em definir um caso por linha, como no exemplo seguinte.

{% include code code="
val age = 16
val ageGroup =  if(age < 12) 'i'
                else if(age < 15) 'c'
                else if(age < 18) 'j'
                else 's'
"
msg="A árvore ilustra o processo de decisão para obter o resultado da avaliação."
img="iftree.png"
%}




>Esta secção introduziu sequências de expressões, tendo em conta que existem dependências entre definições, e essas dependências condicionam a ordem pela qual a sequência deve ser definida. Foi também introduzido o conceito de expressão condicional, que determina que expressões com base na avaliação de outras expressões (guardas). A utilização somente de valores elementares é normalmente insuficiente para gerir a complexidade de um problema. A próxima secção aborda a definição de [valores compostos](valorescompostos).

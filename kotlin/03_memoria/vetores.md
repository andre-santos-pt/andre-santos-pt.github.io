---
title: Vetores de valores
image: imagens/parking.png
---

 >Nesta secção ilustraremos o conceito de **vetor** com números inteiros (*int*). Para já apenas será abordada a criação e leitura de vetores, e não a possibilidade de modificação.

**Vetores** são forma mais elementar de lidar com conjuntos de dados. Um vetor consiste numa sequência de valores de determinado tipo. O **comprimento** do vetor corresponde ao número de elementos. Os elementos são acedidos mediante **índices**, tendo o primeiro elemento índice zero e o último comprimento menos um. No exemplo seguinte, temos um vetor com comprimento 5, sendo os índices válidos {0, 1, 2, 3, 4}.

![vector](imagens/vetor.png)

# Alocação e inicialização

## Inicialização por omissão

Ao alocar um novo vetor temos que fornecer o comprimento (número de elementos desejado), bem como o tipo dos mesmos. Nesta secção iremos utilizar o tipo *IntArray* para vetores de inteiros.

No exemplo seguinte é inicializado um vetor de inteiros com comprimento 7. Os elementos iniciais contidos no vetor após inicialização são valores determinados por omissão. No caso de valores numéricos, o valor por omissão é o zero.

{% include code code="
val zeroArray = IntArray(7)
"
%}

![vector](imagens/zeroArray.png)


{% include error code="
val array = IntArray(-1)
"
msg="Ao fornecer um valor negativo para a inicialização teremos um erro de execução."
exc="java.lang.NegativeArraySizeException"
%}

## Inicialização com função para gerar elementos

Por forma a alocar um vetor que contenha outros valores iniciais que não os por omissão, é possível fornecer uma função de inicialização que será utilizada para gerar os elementos. Dado o índice de uma posição, esta função deverá devolver qual o valor a colocar na mesma.

No exemplo seguinte, é criado um vetor com os primeiros 10 números naturais ({1..10}). Ao parâmetro *i* da expressão lambda serão atribuídos os valores {0..9} (dadas as 10 posições do vetor), sendo que a expressão para obter os elementos é *i* mais 1, ou seja, {1..10}.

{% include code code="val naturals = IntArray(5) { i -> i + 1 }" %}

![vector](imagens/naturals5.png)

No exemplo seguinte são gerados os primeiros 10 números pares, sendo que no código foi utilizada a simplificação sintática recorrendo a *it* para o parâmetro único.

{% include code code="val evenNumbers = IntArray(10) { it * 2 }" %}


## Inicialização com elementos literais

Outra forma possível de inicializar um vetor é fornecer uma sequência dos seus elementos. Neste caso, o comprimento do vetor corresponderá ao número de elementos fornecidos. No exemplo em baixo são fornecidos 5 elementos, e logo o vetor terá esse comprimento e os valores {1, 3, 5, 7, 9}.

{% include code code="val oddDigits = intArrayOf(1, 3, 5, 7, 9)" %}

![vector](imagens/oddDigits.png)

## Comprimento
O comprimento de um vetor pode ser consultado através do campo **size**. A partir deste valor é possível saber qual o último índice válido (*size - 1*). Porém, dado que é muito frequente a necessidade de saber esse valor, existe um campo **lastIndex** que o fornece.

{% include code code="
val a = intArrayOf(2, 4, 6, 8)
val aSize = a.size // 4
val aLastIndex = a.lastIndex // 3
" %}

O comprimento de um vetor é fixo. Uma vez alocado um vetor, já não é possível alterar o seu comprimento. Ao tratar um conjunto de valores que necessita de mais espaço para outros elementos, torna-se necessário alocar um novo vetor.

O comprimento de um vetor pode ser zero, isto é, um vetor vazio sem elementos. Esta possibilidade é útil para representar conjuntos de dados vazios. Por exemplo, a interceção dos conjuntos {1, 2} e {3, 4} corresponde ao conjunto vazio ({}). Uma pesquisa para a qual não foram encontrados resultados, também consiste num conjunto vazio.

{% include code code="
val empty = IntArray(0)
val empty = empty.size // 0
"%}



# Acesso por indexação
O acesso a um elemento de um vetor é feito mediante um *índice*, o qual terá que estar compreendido no intervalo [0, *último índice*]. A notação para nos referirmos ao índice consiste em utilizar parêntesis retos, como exemplificado em baixo.

{% include code code="
val even = intArrayOf(2, 4, 6, 8)
val first = even[0] // 2
val last = even[a.lastIndex] // 8
val sum = first + last // 10
" %}

## Acesso com índice inválido
Um dos erros mais frequentes quando manipulamos vetores consistem em tentar aceder a uma posição do vetor com um valor de índice inválido (frequentemente com a diferença de uma unidade dos limites válidos). Este erro será manifestado numa interrupção da execução com a exceção *ArrayIndexOutOfBounds*.

{% include error code="
val naturals = intArrayOf(1, 2, 3, 4, 5)
val last = naturals[5]
"
msg="O valor 5 não é válido para o vetor em questão, cujos índices válidos estão no intervalo [0, 4]"
exc="java.lang.ArrayIndexOutOfBounds"%}

# Funções que alocam vetores
Utilizando a inicialização de vetores com função geradora, podemos definir funções que alocam  e devolvem vetores.

{% include code file="inverted.kt"
msg="Exemplo: Função para obter um vetor com os elementos na ordem inversa." %}

{% include code file="naturals.kt"
msg="Exemplo: Função para gerar vetores de sequências de números naturais num intervalo [min, max] dado."%}


# Funções sobre vetores


{% include code file="isOrdered.kt"
msg="Exemplo: Função para verificar se os valores de um vetor estão por ordem crescente, utilizando uma função auxiliar com recursão na cauda. O processo consiste em procurar um par adjacente de índices que viola a condição de ordem crescente, e nesse caso devolver falso. Quando não foi encontrada nenhuma violação da condição devolve verdadeiro." %}

{% include code file="sum.kt"
msg="Exemplo: Função para somar os elementos de um vetor, recorrendo a uma função auxiliar com recursão na cauda. O somatório vai sendo acumulado à medida que a recursão avança, até obter o resultado final (caso base)."
%}




>Vetores consistem numa estrutura de dados elementar que não é frequentemente utilizada diretamente no desenvolvimento de aplicações, devido essencialmente a não ser prático. O normal é utilizar [*listas*](../05_colecoes/listas) ou outras estruturas. Porém, é importante dominar a manipulação de vetores para efeitos de implementação de algoritmos e estruturas de dados ou em situações que requerem uma optimização de desempenho. A [secção seguinte](referencias) aborda a forma como os vetores são guardados em memória. 

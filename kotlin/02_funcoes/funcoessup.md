---
title: Funções de ordem superior
---

>Uma função de **ordem superior**, herda seu nome da Matemática, onde também existe essa categorização. A ideia consiste numa função que ao invés de manipular apenas valores (numéricos, etc), manipula também funções como se se tratassem de valores.

# Motivação

Dada a aparente complexidade deste assunto, apresentamos primeiro um caso exemplo para motivar o seu propósito. No seguinte código temos duas funções que realizam uma contagem. A primeira conta quantos números pares existem no intervalo *[min, max]*, ao passo que a segunda quantos números são ímpares. Na verdade, esta forma de contagem é claramente *naïve*, mas o propósito é a apenas ilustrativo.

{% include code code="
fun countEven(min: Int, max: Int): Int =                    
    if(min > max) 0         
    else if(min % 2 == 0) 1 + countEven(min + 1, max)          
    else countEven(min + 1, max)                   

fun countOdd(min: Int, max: Int): Int =                  
    if(min > max) 0                                          
    else if(min % 2 != 0) 1 + countOdd(min + 1, max)        
    else countOdd(min + 1, max)   
"
%}

Como se pode observar, as funções têm uma estrutura muito semelhante. O único aspeto que é diferente (para além do nome) é a guarda do segundo **if** para decidir se o número é considerado para a contagem. Outra função para efetuar uma contagem com outro critério (p.e. contar ímpares) poderia também ter a mesma estrutura. Porém, *duplicação é um fenómeno que devemos evitar* sempre que possível.

Um dos objetivos das funções de ordem superior é conseguir que as mesmas sejam adaptáveis, generalizando as partes comuns e externalizando as partes específicas (que variam). No exemplo acima, a parte específica é o critério de contagem, ou seja, dado um número decidir se o mesmo é para considerar na contagem (booleano). A ideia é ter apenas uma função que permite que esse critério de contagem seja fornecido num argumento através de uma função que o define.

# Expressões lambda

Uma expressão **lambda** é um conceito essencial em Programação Funcional, que permite que definições de funções sejam tratadas como valores. Desta forma, podemos atribuir tais expressões a definições de valores (**val**), utilizando a sintaxe seguinte: **{** *lista de parâmetros* **->** *expressão* **}**. A lista de parâmetros e a expressão são feitas da mesma forma que nas funções.


{% include code code="
val isEven = {n: Int -> n % 2 == 0}
"
msg="Exemplo: Expressão lambda para definir uma função que verifica se um número é par."
%}

Uma vez tendo um valor dado por uma expressão lambda, podemos utilizá-lo sintaticamente como se de uma função se tratasse.
{% include code code="
val e = isEven(7)
"
%}

Tal como na invocação de funções, o número e tipo dos argumentos tem que bater certo com a definição da expressão lambda.

{% include error code="
val e = isEven(7.2)
"
msg="Não correspondência de argumentos/parâmetros."
%}

## Sequência de instruções
Uma expressão lambda pode conter uma sequência de instruções, ao invés de apenas que determina o resultado. Nesse caso a última instrução deverá ser a expressão que corresponde ao resultado, sendo opcional a utilização de *return*.

{% include code code="
val isEven: (Int) -> Boolean = { n ->
    val mod = n % 2
    mod == 0
}
"
%}

## Inferência de tipo

Tal como na definição de valores simples, o tipo da expressão lambda foi inferido a partir da mesma. Ou seja, dado um inteiro, devolve um booleano. Porém, podemos também ser explícitos e de declarar o tipo do valor. A sintaxe para o fazer é a seguinte: **(** *lista de tipos* **) ->** *tipo de retorno*. Desta forma, a mesma definição acima poderia ser dada fornecendo explicitamente o tipo da lambda.

{% include code code="
val isEven: (Int) -> Boolean = {n: Int -> n % 2 == 0}
"
%}

Quando o tipo da expressão é explícito na definição do valor, podemos omiti-lo na expressão lambda, pois pode ser inferido no sentido inverso.

{% include code code="
val isEven: (Int) -> Boolean = {n -> n % 2 == 0}
"
%}

{% include error code="
val isEven: (Int) -> Int = {n: Int -> n % 2 == 0}

val isOdd: (Int) -> Boolean = {n: Int -> n % 2}
"
msg="Incompatibilidades de expressão lambda."
%}



# Função de ordem superior

Uma função de ordem superior é caracterizada por ter pelo menos uma das seguintes características:
  - Receber pelo menos uma função como argumento
  - Retornar uma função como resultado



## Função que consome função
Retomamos o exemplo inicial de motivação para ilustrar uma função que recebe outra como argumento. A ideia é ter uma função que efetua contagens num intervalo de números inteiros

{% include code code="
fun countIf(min: Int, max: Int, accept: (Int) -> Boolean): Int =
    if(min > max) 0
    else if(accept(min)) 1 + countIf(min + 1, max, accept)
    else countIf(min + 1, max, accept)
"
msg="Exemplo: Função recursiva de ordem superior para contar quantos números inteiros num intervalo [min, max] obedecem a determinado critério variável."
%}

De seguida apresentamos diversas formas de utilizar a função. Para o valor **nEven** é passado um valor que é uma expressão lambda, ao passo que para as restantes é passada uma expressão lambda diretamente.

{% include code code="
val checkEven: (Int) -> Boolean = {n -> n % 2 == 0}
val nEven = countIf(1, 100, checkEven)
val nOdd = countIf(1, 100, {n -> !isEven(n)})
val divOf7 = countIf(1, 7, {n -> 7 % n == 0})
val divOf10 = countIf(1, 10, {n -> 10 % n == 0})
"
%}

### Referência a função
Existe ainda outra forma de passar uma função para outra função, por via de uma **referência a função**. Para tal, utilizamos o símbolo **::** seguido do nome da função. Naturalmente, será verificado se a função indicada obedece ao tipo do tipo do parâmetro.

{% include code code="
val nOdd = countIf(1, 100, ::isOdd)
"
%}

## Função que devolve função
Outra possibilidade, talvez menos intuitiva que a anterior, é termos uma função que devolve outra função como retorno. Como motivação, olhemos para a definição dos valores **divOf7** e **divOf8**. O parâmetro da expressão lambda de ambos os casos, embora curto, é na verdade muito semelhante. As duas expressões servem para verificar se o número é divisor de outro número (7 ou 10 no exemplo). Ora, a ideia aqui é generalizar a função para determinar se um número é divisor de outro número *x* variável.

Para que uma função devolva outra função, isso terá que ser declarado no tipo de retorno através da mesma sintaxe apresentada acima. O retorno será uma expressão lambda compatível com o tipo de retorno.

{% include code code="
fun divisorOf(x: Int): (Int) -> Boolean = { n -> x % n == 0 }
"
msg="Exemplo: Função de ordem superior para produzir uma função que determina se um número é divisor de um dado <b>x</b> fornecido como argumento."
%}

A invocação deste tipo de funções é feita de forma normal, ainda que o valor retornado seja uma expressão lambda. Este valor pode ser utilizado como uma função, ou como argumento lambda de outra função.
{% include code code="
val isDivOf7 = divisorOf(7)
val a = isDivOf7(5)
val b = isDivOf7(7)
val divOf7 = countIf(1, 7, isDivOf7)
val divOf10 = countIf(1, 10, divisorOf(10))
"
%}


O tipo de retorno pode ser omitido, desde que a expressão lambda indique o tipo de todos os parâmetros.
{% include code code="
fun multipleOf(x: Int) = { n: Int -> n % x == 0 }
"
%}

## Função baseada em funções de ordem superior
Para concluir esta secção, apresentamos uma função que faz uso das duas funções de ordem superior apresentadas, *countIf* e *divisorsOf*, para verificar se um número *n* é primo. É feita uma contagem no intervalo [1, *n*] com o critério de ser divisor de *n*. Se a contagem for igual a 2, o número é primo. Esta forma de calcular números primos é *naïve* e serve apenas o propósito de ilustrar conceitos.

{% include code code="
fun isPrime(n: Int) = countIf(1, n, divisorOf(n)) == 2
"
msg="Exemplo: Função baseada em funções de ordem superior, para verificar se um número é primo."
%}


# Simplificações sintáticas

As invocações de funções com lambdas como argumentos podem revelar-se sintaticamente um pouco densas. Veja-se este caso apresentado anteriormente.

{% include code code="
val nOdd = countIf(1, 100, { n -> !isEven(n) })
"
%}

Existe uma possibilidade para aligeirar a sintaxe que podemos aplicar quando o último argumento de uma invocação é uma expressão lambda, consistindo em extrair esse último argumento para fora dos parênteses da invocação.

{% include code code="
val nOdd = countIf(1, 100) { n -> !isEven(n) }
"
%}

A maioria das expressões lambda têm apenas um argumento. Quando aplicada a simplificação acima, podemos ainda tornar a sintaxe mais leve, omitindo a parte esquerda e referimo-nos ao argumento único por **it** (a "coisa" em questão).

{% include code code="
val nOdd = countIf(1, 100) { !isEven(it) }
"
%}


>As funções de ordem superior permitem definições adaptáveis, permitindo generalizar várias funções semelhantes. As bibliotecas de software quando oferecem formas de adaptabilidade/extensibilidade recorrem muito a este tipo de solução. Ainda que um programador possa não as definir frequentemente, irá certamente utilizar constantemente este tipo de funções quando recorre a bibliotecas.

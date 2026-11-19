# Expressões constantes




## Tipos de valores
Os valores resultantes da avaliação de uma expressão podem ter **tipos** diferentes. Ilustramos nesta secção os tipos de valores mais comuns utilizando expressões literais.

| Tipo        | Valor       | Exemplo      |
| ----------- | ----------- |--------------|
| int         | inteiro     | 0, 1, 10     |
| double      | decimal     | 0.2, 1.2, .3 |
| boolean     | booleano    | true, false  |
| char        | caracter    | 'a', '1', '!'|

Ao nível mais elementar, temos o tipo **numérico** para valores que representam números. Dentro deste tipo existem dois sub-tipos **inteiros** e **decimais**. 

<matte-ace>
int weekdays = 7;
</matte-ace>

<matte-ace>
double pi = 3.1415926;
</matte-ace>

Outro tipo de valor elementar é o **booleano**, que representa valores de verdade:
- `true` (verdadeiro)
- `false` (falso)

<matte-ace>
boolean married = false;
</matte-ace>

Por fim, existe o tipo de valor elementar para caracteres, sendo que cada valor representa o símbolo do caracter (no fundo, um número para o codificar). O caracter a ser representado tem que ser escrito entre plicas, como no exemplo seguinte.

<matte-ace>
char vowel = 'a';
</matte-ace>



# Definição de valores
Em programação, as expressões surgem em vários contextos, sendo o mais simples a atribuição do valor resultante da avaliação de uma expressão a um espaço em memória. Este espaço em memória é identificado no programa através de um nome.

<matte-ace>
double mean = (12 + 13.2 + 15.7) / 3;
</matte-ace>


O símbolo `=` não tem o mesmo significado do que numa equação. Neste caso, é utilizado para representar a **atribuição** de uma expressão a um valor. Neste exemplo, associámos o valor *13.6*, resultante da avaliação da expressão, ao identificador `mean` (média).

Um **identificador** válido consiste numa sequência de letras, sem espaços, podendo incluir número excepto no primeiro caracter. Quando queremos expressar um identificador utilizando mais do que uma palavra é comum seguir a forma *camelCase*, ou seja, um caracter maiúsculo representa o início de uma palavra para facilitar a leitura. Por convenção em Kotlin, os identificadores de valores devem começar com uma letra minúscula.


> Uma **palavra reservada** é um termo que não pode ser utilizado como identificador (de valores por exemplo), devido a fazer parte da linguagem. Ao tentarmos utilizar uma palavra reservada, iremos ter um erro de sintaxe. Os nomes dos tipos de valores são palavras reservadas: `int`, `double`, etc.

<matte-ace>
double double = 10 * 2;
</matte-ace>


# Expressões aritméticas

Expressões **aritméticas** consistem em operações sobre números (adição, subtração, multiplicação, divisão, divisão inteira, e resto da divisão inteira).

## Operadores

Uma operação aritmética é realizada mediante um operador, representado por um símbolo na linguagem. Um operador **binário**, tal como o nome indica (*bi*), atua sobre dois operandos.

Os símbolos utilizados são intuitivos, com a exceção do operador **%**, representado com um símbolo normalmente associado à percentagem, mas que neste contexto representa o resto da divisão inteira. Por outro lado, o operador de divisão **/** tem uma nuance, pois tem dois comportamentos possíveis, dependendo do tipos dos operandos. Perante dois operandos inteiros, efetuará uma divisão inteira, caso contrário, uma divisão decimal. A sintaxe de utilização de operadores binários consiste ter o *primeiro operando*, seguido do *operador*, e o *segundo operando*.

O tipo do valor é decimal, pois embora a conta resulte num número inteiro (15.0), o tipo da expressão determinado é decimal dado que a divisão entre dois números decimais somados resultará num número decimal (apesar de neste caso particular resultar num inteiro

<matte-ace expressiontree>
double grade = (14.5 + 15.5) / 2;
</matte-ace>


### Operatores Aritméticos

| Operador      | Operação | Exemplo | Avaliação |
| ----------- | ----------- | -------- | ------- |
| +           | adição                 | 2 + 3 | 5 |
| -           | subtração              | 2 - 4 | -2|
| *           | multiplicação          | 3 * 5 | 15|
| /           | divisão                | 5.0 / 2 | 2.5|
| /           | divisão inteira    | 11 / 3 | 3 |
| %           | resto da divisão inteira  | 11 % 3 | 2

<quiz id="intdiv" question="Qual o resultado de avaliar a expressão 7 / 2?">
  <option value="3.5">3.5</option>
  <option value="3" correct>3</option>
<option value="4">4</option>
 <option value="erro">Causa um erro</option>
</quiz>

<quiz id="intmod" question="Qual o resultado de avaliar a expressão 11 % 3?">
<option value="2" correct>2</option>
<option value="33">33</option>
<option value="3">3</option>
 <option value="14">14</option>
</quiz>

## Precedências
`( )`

Divisões por zero resultarão num erro de execução, pelo que o código deverá acautelar que isso não acontece.

<matte-ace>
double mean = (14 + 15) / 0;
</matte-ace>

### Elemento simétrico (adição)

Ao passo que um operador binário atua sobre dois operandos, um operador **unário** atua sobre *um* operador apenas. Para aritmética, o único operador unário que faz sentido é simetria, i.e. trocando o sinal, expressa através do operador **-**. No seguinte exemplo, a expressão seria avaliada em *-10*.

<matte-ace>
int value = -(5 * 2);
</matte-ace>

### Ordem de operações
Uma expressão é avaliada da esquerda para a direita. Porém, tal como em Matemática, a avaliação de uma expressão obedece a precedências de operadores, sendo que as operações multiplicativas (multiplicação e divisão) são efetuadas anteriormente às aditivas (adição e subtração). Desta forma, a expressão *14 + 16 / 2* é avaliada em *22* (e não em *15*), porque *16 / 2* é calculado primeiro, resultando em *14 + 8*.

Por forma a alterar a ordem das operações podemos recorrer a parênteses, envolvendo as sub-expressões a avaliar primeiro. Na continuação do exemplo anterior, ao termos *(14 + 16) / 2* seria obtido o valor *15*, porque a adição é efetuada antes da divisão.

<matte-ace expressiontree>
double mean = 14 + 15 / 2;
</matte-ace>

<matte-ace expressiontree>
double mean = (14 + 15) / 2;
</matte-ace>

<quiz id="doublevsint" question="Qual o tipo da expressão 1.5 + 1.5?">
  <option value="int">int</option>
  <option value="double" correct>double</option>
<option value="boolean">boolean</option>
 <option value="none">Nenhuma das anteriores</option>
</quiz>

<matte-ace data-storage-id="constant-expressions-arith" call="" tracing firstValues title="Indica o valor resultante de cada expressão.">
int a = 30 + 2;
double b = 10 + .2;
int c = 10 / 3;
int d = 10 % 3;
int e = 12 % 4;
double f = -(10 * -3);
double g = 
</matte-ace>

## Overflow
Cada valor é guardado num espaço em memória de capacidade fixa, sendo 4 *bytes* para os valores inteiros, permitindo guardar valores no intervalo [-2147483648, 2147483647]. Quanto é efetuada uma operação aritmética que ultrapassa os valores do intervalo, é excedida a capacidade, estamos perante *overflow* (transbordar). Ao contrário, por exemplo, da divisão por zero,  esta situação não dá origem a um erro de execução que interrompe o programa. O valor em excesso para lá do limite passa para o extremo oposto do intervalo. Desta forma, o seguinte valor será avaliado em *-2147483648*, pois ao somar 1 ao valor máximo, obtemos o valor mínimo.

Embora o *overflow* não se manifeste num erro de execução, pode dar origem a um erro na lógica do que estamos a programar. Por exemplo, num programa que manipule somas avultadas de valores monetários, 2147 milhões poderá não ser suficiente. Cálculos com *overflow* iriam dar origem a valores negativos, o que iria certamente comprometer o funcionamento do programa.

Problemas de *overflow* podem manifestar-se na compilação, quando o valor literal atribuído ultrapassa os limites.

<matte-ace>
int largeNumber = 2147483647 + 1;
</matte-ace>


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
| xor           | disjunção exclusiva    | 1 > 0 xor 2 > 0  | false |

<quiz id="relational" question="Qual o tipo da expressão 5 > 0?">
  <option value="int">int</option>
  <option value="double">double</option>
<option value="boolean" correct>boolean</option>
 <option value="logical">logical</option>
</quiz>


### Negação
Os valores lógicos podem ser **negados**, com vista a obter o valor simétrico, através do operador unário **!**.

<matte-ace>
boolean negative = !(3 > 0);
</matte-ace>


# Sumário

Esta secção abordou expressões apenas utilizando valores literais, mas ilustrando todos os operadores principais para manipular e relacionar valores. Escrever expressões constantes não reflete a atividade de programar, pois tipicamente lidamos com expressões que dependem de valores externos.



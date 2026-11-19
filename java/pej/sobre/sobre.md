# Livro Interativo

Este é um livro interativo, com demonstrações e exercícios incorporados, que aborda conceitos de programação estruturada ilustrados com a sintaxe de Java. Contudo, não se trata de um manual de Java, mas sim numa abordagem ao raciocínio algorítmico que é necessário para programar independentemente da linguagem utilizada.

> Um dos aspetos distintivos deste livro é o ênfase no treino de leitura e interpretação de código. Todo o conteúdo apresentado é ilustrado com exemplos que podem ser experimentados e com perguntas sobre o código.

## Exemplos de aquecimento
Considera a seguinte sequência de instruções consiste em três variáveis: `a`, `b` e `c`. Os operadores são intuitivos: `*` para multiplicação e `+` para adição.

<matte-ace data-storage-id="sobre-sequential-values" call="" tracing firstValues title="Qual o valor de cada variável?">
int a = 20;
int b = a * 5;
int c = b + 1;
</matte-ace>

Deverás ter assumido que a interpretação das instruções é feita de cima para baixo, porque é assim que estamos habituados a ler. O valor de `b` só consegue ser determinado tendo um valor para `a`, e o valor de `c` só consegue ser determinado tendo calculado o valor de `b`. Esta forma **sequencial** de execução é uma das características elementares em programação estruturada.


Executa o seguinte código no botão ► e observa o resultado. 

<matte-ace data-storage-id="sobre-arithmetic-example">
int a = 10;
int b = 20;
int c = a + b / 2;
</matte-ace>

O valor de `c` resultou em 20 (em vez de 15), e logo há um problema no cálculo da média. A noção de **expressão** existe em praticamente todos os estilos de programação. Estará a divisão por `2` está a ser feita sobre a soma de `a` e `b`? A interpretação do programa pode ser observada passo a passo através da lupa. Observa em que momento é feita a divisão.

<quiz id="correct-average-expression" question="O que é necessário para que o cálculo seja correto?">
<option correct>(a + b) / 2</option>
<option>a / 2 + b / 2</option>
<option>2 / (a + b)</option>
</quiz>

Acrescenta uma instrução no código seguinte para definir uma variável `c`, à semelhança do exemplo anterior, que terá o valor da média dos valores de `a` e `b`. Para a divisão utiliza o operador `/`.

<matte-ace data-storage-id="sobre-average-exercise">
int a = 10;
int b = 20;
int c = a + b / 2;
int avg = (a + b) / 2;
</matte-ace>

> Os erros de programação são das maiores barreiras à aprendizagem. Neste livro é dada uma atenção particular à interpretação de erros. Os erros são sinalizados no código de forma convencional, mas as mensagens são diferentes das que são apresentadas nas ferramentas profissionais.

<matte-ace data-storage-id="sobre-type-error-example">
int pi = 3.14159;
</matte-ace>
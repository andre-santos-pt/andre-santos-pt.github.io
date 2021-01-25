---
title: Interfaces e Polimorfismo
---

# Motivação: Objetos com comportamento adaptável

O código seguinte apresenta dois procedimentos para ordenar  um vetor de inteiros, por ordem crescente (esquerda) e decrescente (direita). O algoritmo de ordenação é talvez o mais naïve que se conhece, o Bubble Sort (mas essa questão não é importante para o que se quer ilustrar). Repare como as implementações são quase iguais, apenas diferem na guarda do if (aliás, apenas diferem num único caracter!). Imaginemos agora que se pretendia outra ordenação de inteiros segundo outro critério, por exemplo, pares antecedem os ímpares. (Qual seria a solução? Copiar o código, dar outro nome ao procedimento, e alterar o if?)

```java
void sortAscending(int[] v) {
  for(int i = 0; i < v.length; i++)
    for(int j = 1; j < v.length-i; j++)
      if(v[j] < v[j-1]) {  
   	  	int t = v[j-1];  
   	  	v[j-1] = v[j];  
   	  	v[j] = t;  
      }  
}
```

![Description](header.jpg)

```java
void sortDescending(int[] v) {
  for(int i = 0; i < v.length; i++)
    for(int j = 1; j < v.length-i; j++)
      if(v[j] > v[j-1]) {  
   	  	int t = v[j-1];  
   	  	v[j-1] = v[j];  
   	  	v[j] = t;  
      }  
}
```

{% include_relative code.html %}

Para números, só há dois critérios de ordenação óbvios (crescente e decrescente). Porém, numa situação onde é necessário ordenar objetos mais complexos (p.e. Alunos com número, nome, idade, etc), podem haver múltiplos critérios de ordenação úteis numa aplicação. Ao recorrer a uma solução de copy-paste, estaremos a duplicar muito código, o que não é desejável por si só, bem como a aumentar a chance de introduzir erros neste processo (em engenharia de software há muitos bugs derivados de copy-paste). Por fim, ainda necessitamos de entender, pelo menos superficialmente, o código do algoritmo para fazer a substituição corretamente.

# Interfaces

As interfaces consistem num mecanismo de programação que ajuda a contornar estes problemas, “externalizando” parte do comportamento de um procedimento/função. No código acima, o que seria desejável era ter um única implementação do algoritmo, onde o cálculo lógico da condição do if fosse um parâmetro. Acontece que tal parâmetro não forneceria simplesmente um valor, mas sim uma função booleana que decide se v[j] deve estar antes de  v[j-1]. Como em Java, ao contrário de outras linguagens (p.e. funcionais), não podemos passar uma função como parâmetro, a solução consistirá em ter um objeto que disponibiliza a função pretendida. Porém, queremos permitir diferentes objetos (funções de decisão distintas), e para isso podemos definir um tipo de objeto abstrato, que chamaremos de interface.

As interfaces têm um nome (o tipo abstrato que representam), e definem de operações (implicitamente públicas) sem fornecer a sua implementação.  A ideia é a interface ser uma especificação que declara quais as operações que os objetos compatíveis terão que ter disponíveis. Para o exemplo dado, faria sentido a seguinte interface para representar um critério de ordenação. A operação booleana, dados os inteiros a e b, devolverá verdadeiro caso  a seja considerado anterior a b. Porém, apenas vemos uma declaração, sem implementação,  a qual ficará a cargo de classes que se declarem compatíveis com a interface.

```java
interface SortingPolicy {
  boolean isBefore(int a, int b);
}
```

Para definir uma classe compatível com determinada interface, terão que ser disponibilizadas implementações para todas as operações declaradas pela mesma, bem como declarar que a classe é compatível com a interface através da diretiva implements. Uma classe pode implementar várias interfaces (implements InterfaceA, InterfaceB, ...), embora isso não seja o mais comum. A classe seguinte é compatível com a interface apresentada,  implementando o critério de ordenação crescente (a vem antes de b se a < b).  

```
class Ascending implements SortingPolicy {
  public boolean isBefore(int a, int b) {
    return a < b;
  }
}
```

# Polimorfismo
O polimorfismo é um mecanismo que permite ter uma forma uniforme para manipular objetos de tipos diferentes. Em termos práticos, isto permite ter uma variável de um tipo à qual podem ser atribuídos objetos de tipos diferentes. Por exemplo:

```java
SortingPolicy policy = new Ascending();
```

A variável policy do tipo SortingPolicy é polimórfica, porque lhe podem ser atribuídos objetos de vários tipos (formas) diferentes, desde que compatíveis com a interface. O código que inclua uma invocação de uma operação numa variável polimórfica, por exemplo policy.isBefore(4, 5) não tem que depender do tipo do objeto concreto que fornece a implementação da operação.  Isto consiste numa grande vantagem para o nosso exemplo, pois podemos ter uma versão do método que implementa a ordenação que é independente do critério de ordenação (pois este é fornecido num parâmetro).


```java
static void sort(int[] v, SortingPolicy policy) {
  for(int i = 0; i < v.length; i++)  
    for(int j = 1; j < v.length-i; j++)  
      if(policy.isBefore(v[j], v[j-1])) {  
   	  int t = v[j-1];  
   	  v[j-1] = v[j];  
   	  v[j] = t;  
   	}  
}
```

Nesta implementação, o parâmetro policy aceita qualquer objeto compatível com a interface, ficando a decisão de que objeto utilizar a cargo de quem invoca. O seguinte código invoca o método utilizando o critério de ordenação crescente (definido na classe apresentada anteriormente).

```java
int[] array = {6, 3, 2, 7, 4};
sort(array, new Ascending());    	// [2, 3, 4, 6, 7]
```

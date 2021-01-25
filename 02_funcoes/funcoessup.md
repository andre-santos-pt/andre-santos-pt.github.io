---
title: Funções de ordem superior
---

Uma função de **ordem superior**, herda o nome pomposo da Matemática, onde também existe essa categorização. A ideia consiste numa função que ao invés de manipular valores (numéricos, etc), trata também funções como se de valores tratassem.

# Motivação

Dada a aparente complexidade deste assunto, apresentamos primeiro um exemplo motivador.

<code>
fun countPrimes(min: Int, max: Int) : Int =                  
    if(min > max) 0                                          
    else if(isPrime(min)) 1 + countPrimes(min+1, max)        
    else countPrimes(min + 1, max)                           
</code>
<code>
fun countEven(min: Int, max: Int) : Int =                    
    if(min > max) 0         
    else if(min % 2 == 0) 1 + countEven(min+1, max)          
    else countEven(min + 1, max)                          
</code>

Como se pode observar, as funções têm uma estrutura muito semelhante. O único aspeto que é diferente (para além do nome) é a guarda do segundo *if* para decidir se o número é considerado para a contagem. Outra função para efetuar uma contagem com outro critério poderia também ter a mesma estrutura. Porém, a **duplicação** é um fenómeno que devemos evitar.

Um dos objetivos das funções de ordem superior é conseguir que as mesmas sejam adaptáveis, generalizando as partes comuns e externalizando as partes específicas. No exemplo acima, a parte específica é o critério de contagem, e então a ideia é permitir que esse seja fornecido como uma função que o define.

# Lambdas

lambdas

<code>
val isEven = {n: Int -> n % 2 == 0}
</code>


# Função de ordem superior

Uma função de ordem superior é caracterizado por ter pelo menos uma das seguintes características:
  - Receber pelo menos uma função como argumento
  - Retornar uma função como resultado



## Função que consome função

<code>
fun countIf(max: Int, criteria: (n: Int) -> Boolean) : Int =             
    countIfAux(0, 1, max, criteria)
tailrec fun countIfAux(r: Int, min: Int, max: Int, criteria: (n: Int) ->
    if(min > max) r                                                      
    else countIfAux(if(criteria(min)) r + 1 else r, min + 1, max, criteria)     
</code>

## Função que devolve função
fun multipleOf(i: Int) : (n: Int) -> Boolean =
    { n -> n % i == 0 }

## Benefícios

# Conclusão

As funções de ordem superior permitem definições adaptáveis, permitindo generalizar várias funções semelhantes.

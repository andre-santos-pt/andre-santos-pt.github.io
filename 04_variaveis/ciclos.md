---
title: Ciclos
---

A noção de **ciclo** consiste numa forma de especificar que um bloco de instruções se repete um número variável de vezes. Nas [funções recursivas](../02_funcoes/funcoesrec) a repetição é feita à custa de chamadas sucessivas à função, ao passo que com ciclos será alcançada por via da execução repetida de um bloco de instruções.

A forma mais elementar de definir um ciclo é através da estrutura de controlo **while** (enquanto).

<kotlin>
while()
</kotlin>

# Iteração

Uma **iteração** consiste no processo de percorrer os valores de um intervalo, também designada por *varrimento*. Desta forma, ao referir uma iteração sobre os números naturais até 10, significa que os valores de 1 a 10 serão *visitados* com algum propósito.


## Vetores

O processamento de vetores sem recursão tem que se basear em iterações. Neste caso, as iterações são sobre os índices das posições que pretendemos aceder.

<kotlin>
fun sum(a: DoubleArray): Double {
  var i = 0
  while(i < a.size) {

    i = i + 1
  }
}

</kotlin>

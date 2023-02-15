---
title: Funções
---

>No [capítulo anterior](../01_expressoes) foi abordada a definição de valores através de **expressões**. Este capítulo aborda a definição de **funções**, um conceito fundamental em programação, pois sem definir funções apenas conseguiremos escrever pequenos programas sequenciais (os chamados *scripts*). Embora seja possível escrever programas com alguma dimensão (digamos com mais de 1000 linhas) sem definir funções, a qualidade dos mesmos (legibilidade e facilidade de adaptação) não será certamente a melhor.


Fazendo uma analogia com notações utilizadas em Matemática:

  - a multiplicação *4 x 5* é uma forma de expressar *5 + 5 + 5 + 5*
  - a potência *2*<sup>5</sup> é uma forma de expressar *2 x 2 x 2 x 2 x 2*

Quais as vantagens de utilizar as primeiras ao invés das segundas?
  - identificar com clareza a intenção (através da notação)
  - na multiplicação temos o símbolo *x*
  - na potência temos uma forma de dispor os números
  - ser sucinto, pois as versões expandidas com números ficam intratáveis
  - beneficiar das operações conhecidas para manipular números

A definição de funções está presente em todas as linguagens de programação (de âmbito não especializado), sempre com os mesmos propósitos: **abstrair** um cálculo (ou procedimento) que é recorrente, ou para **decompor** um processo complexo em sub-processos. Abstrair neste contexto significa dar-lhe uma notação (tal como em Matemática), para que a mesma possa ser **reutilizada** em diversos contextos do programa (ou até de outros programas).

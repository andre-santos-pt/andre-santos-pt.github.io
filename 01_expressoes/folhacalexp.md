---
title: "Prática: Folhas de Cálculo (expressões)"
image: excel.png
---

Por forma a ilustrar como a lógica de um programa pode funcionar apenas à custa de expressões, consideremos o caso das folhas de cálculo. Estas são largamente utilizadas como formas de apoiar os mais diversos modelos de negócio. É curioso que quem as desenvolve tipicamente não é programador de formação, mas sim um especialista no domínio em questão.

Nesta secção analisamos a essência das folhas de cálculo, apresentando para já uma forma bastante simplificada do conceito. Consideremos que cada célula aloja uma expressão (constante ou dependente), que se assemelham às apresentadas neste capítulo. Para efeitos de simplificação, vamos assumir que as células podem apenas conter números.

As células têm uma coordenada em termos de coluna (letra) e linha (número). Por exemplo, a coluna mais à esquerda é composta pelas células A1, A2, A3, etc. Apesar da organização espacial em duas dimensões, cada célula é apenas um espaço para guardar um valor. Desta forma, consideremos que cada valor tem a sua definição, e que o valor das células vazias é zero.

<code>
val A2 = 0
val A3 = 2
val A4 = 4
val A1 = A2 + A3 + A4
val B2 = 1
val B3 = 3
val B4 = 5
val B1 = B2 + B3 + B4
</code>

As células cujo conteúdo consiste num número literal correspondem a expressões constantes, ao passo que as definem uma fórmula correspondem a expressões dependentes.

{% include spreadsheet.html %}

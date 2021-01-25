---
title: "Prática: imagens a preto e branco (filtros)"
---

Na [secção anterior](funcoesind) abordou-se a definição de funções independentes simples, baseadas em expressões. Utilizando funções para manipular inteiros, nesta secção propõe exercícios para concretizar efeitos simples sobre imagens a preto e branco.


# Luminosidade

Numa imagem a preto e branco, cada pixel é um valor inteiro no intervalo [0, 255], que representam luminosidade. Zero é a ausência de luz (preto), 255 é o mais luminoso possível (branco), sendo que os valores intermédios são tons de cinzento. Um efeito sobre uma imagem codificada desta forma consiste em aplicar uma função que faz corresponder o valor de cada pixel a outro valor. Por exemplo, clarear uma imagem consiste em aumentar os valores de luminosidade, tendo em conta determinada intensidade.

<code>
fun invert(v: Int) = 255 - v
</code>

<code>
fun brighten(lum: Int, intensity: Double) = truncate(lum+lum*intensity)
</code>


# Sumário

Nesta prática demonstrámos que é possível definir o comportamento de efeitos  sobre imagens a preto e branco através de funções simples. Porém, como veremos mais à frente, efeitos mais elaborados irão requerer funções mais complexas.

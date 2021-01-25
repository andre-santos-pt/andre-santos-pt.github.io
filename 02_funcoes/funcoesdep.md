---
title: Funções dependentes
---

Na [secção anterior](funcoesind) foram apresentadas funções independentes que dependem apenas da sua própria lógica. Nesta secção abordamos **funções dependentes**, ou seja, que se baseiam noutras funções para calcular o seu resultado.




# Funções dependentes

Uma função **dependente**, tal como o nome indica, depende de algo, neste caso de uma ou mais funções. Uma função que não seja muito simples tipicamente depende de outras funções, portanto, uma funções ser dependente é o caso frequente.

Por exemplo, poderíamos definir uma função para saber se um número é ímpar, à custa da função para saber se é par. Como demonstrado no exemplo seguinte, isso pode ser feito negando o resultado da invocação de *isEven* ([secção anterior](funcoesind)), pois ser ímpar é o contrário de ser par.

{% include code file="isOdd.kt" %}

Considere outro exemplo de função, cujo objetivo é limitar um valor num intervalo *[min, max]*. Se for dado um valor dentro do intervalo é devolvido o próprio valor, caso contrário é devolvido *min* ou *max* dependendo se o valor ultrapassa o intervalo no seu limite inferior ou superior, respetivamente.

{% include code file="constrain.kt" %}

Considerando que os argumentos para *min* e *max* seriam *1* e *100*, se for dado o valor:
  - *60*, é executado *min(60, 100)*, resultando em *60*,
  depois *max(1, 60)*, resultando em *60* (próprio número).
  - *120*, é executado *min(120, 100)*, resultando em *100*, e por sua vez *max(1, 100)*, resultando em *100*.
  - *-10*, é executado *min(-10, 100)*, resultando em *-1*, e por sua vez *max(1, -10)*, resultando em *1*.

# Decomposição funcional
<code>
fun constrain(v: Int, min: Int, max: Int) =
  if(v < min) min
  else if(v > max) max
  else v
</code


# Conclusão
As funções podem ser definidas à custa de outras funções, e essa possibilidade é indispensável para economia de esforço e decomposição de complexidade. Na [secção seguinte](funcoesrec) será abordado um tipo de funções caracterizadas por serem dependentes de si próprias, as funções recursivas.

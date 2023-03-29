---
title: "Projeto: Biblioteca de Geração de JSON"
layout: single
---

Este projeto consiste em desenvolver uma biblioteca para produzir dados no formato [JSON](http://www.json.org). Na sua forma mais simples, podemos apenas instanciar objetos da biblioteca (modelo) e serializar para texto. Será possível também derivar JSON a partir de objetos, podendo o processo ser adaptado por via de anotações nas classes desses objetos. Não será exigido o *parsing* de JSON textual.

Será assumida a perspectiva de quem está a desenvolver algo para terceiros utilizarem (biblioteca). Assim sendo, aspetos de **API (forma e documentação)** deverão ser tidos em conta. Por outro lado, a biblioteca deverá oferecer alguma flexibilidade na sua utilização para permitir aplicá-la num leque alargado de situações.

Por forma a garantir uma boa arquitetura da solução, e simultaneamente ter um "guia" para o desenvolvimento, o projeto deverá ser faseado de acordo com o que se propõe neste enunciado. A funcionalidade terá que ser feita de raiz, **não é permitida a utilização de quaisquer bibliotecas externas** (exceto as coleções e JUnit).

# Exemplo (JSON)

<pre>
{
  "uc": "PA",
  "ects" : 6.0,
  "data-exame" : null,
  "inscritos": [
    {
      "numero" : 101101,
      "nome" : "Dave Farley",
      "internacional" : true
    },
    {
      "numero" : 101102,
      "nome" : "Martin Fowler",
      "internacional" : true
    },
    {
      "numero" : 26503,
      "nome" : "André Santos",
      "internacional" : false
    }
  ]
}
</pre>


# Fase 1 - Modelo

Nesta fase o objetivo é ter um conjunto de classes cujos objetos representam uma estrutura de dados JSON em memória. 

Não devemos gerar JSON textual diretamente, mas sim ter objetos em memória (que designaremos por *modelo*) que representam os dados. Estes por sua vez são projetados para texto (para serializar para consola ou ficheiros). Isto permitirá uma maior flexibilidade na manipulação dos dados (pe., fazer pesquisas e validações).

## Projeção textual
Deverá ser possível obter JSON textual a partir do modelo. O formato poderá ser semelhante ao do exemplo acima, mas o mais importante é que seja JSON válido (pode ter outra indentação).

## Visitantes
As classes do modelo deverão oferecer uma forma de varrimento baseada em *visitantes* (padrão de desenho *Visitor*). Utilizando esta forma deverá ser possível:

1. efetuar pesquisas, como por exemplo:
  - obter todos os valores guardados em propriedades com identificador "numero"
  - obter todos os objetos que têm determinadas propriedades (pe. todos os objetos que têm as propriedades *numero* e *nome*)
2. verificar que o modelo obedece a determinada estrutura (pe. a propriedade *inscritos* consiste num array onde todos os objetos têm a mesma estutura)


# Fase 2 - Inferência por reflexão

Nesta fase o objetivo é desenvolver funcionalidade que permita instanciar automaticamente o modelo (desenvolvido na Fase 1) por reflexão de quaisquer objetos de valor (*data class*). 

**Atenção:** Não se pretende obter JSON textual diretamente da estrutura das classes.

## Funcionalidade
Deverá haver uma forma padrão de instanciação que suporte:
- objetos de valor (*data class*)
- coleções (*Collection*)
- tabelas (*Map*)
- tipos primitivos
- strings
- enumerados

## Anotações
Como forma de adaptar a instanciação, deverão ser definidas anotações para permitir:
- excluir propriedades da instanciação
- utilização de identificadores personalizados (e não os das classes)
- forçar que alguns valores sejam considerados strings JSON (pe. um atributo inteiro ser representado como string)

# Avaliação intermédia

As funcionalidades principais têm que ter **casos de teste** correspondentes, como forma de controlar a qualidade e demonstrar a funcionalidade.

A avaliação intermédia será guiada pelos casos de teste, e o objetivo é identificar possíveis problemas estruturais a tempo de serem corrigidos atempadamente para a entrega final.


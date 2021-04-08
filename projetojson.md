---
title: "Projeto: Biblioteca de Geração de JSON"
layout: single
---

Este projeto consiste em desenvolver uma biblioteca para produzir dados no formato [JSON](http://www.json.org). Na sua forma mais simples, podemos apenas instanciar objetos da biblioteca (modelo) e serializar para texto. Será possível também derivar JSON a partir de objetos, podendo o processo ser adaptado por via de anotações nas classes desses objetos. Não será exigido o *parsing* de JSON textual.

Será assumida a perspectiva de quem está a desenvolver algo para terceiros utilizarem (biblioteca). Assim sendo, aspetos de **API (forma e documentação)** deverão ser tidos em conta. Por outro lado, a biblioteca deverá oferecer alguma flexibilidade na sua utilização para permitir aplicá-la num leque alargado de situações.

Por forma a garantir uma boa arquitetura da solução, e simultaneamente ter um "guia" para o desenvolvimento, o projeto deverá ser faseado de acordo com o que se propõe neste enunciado. A funcionalidade terá que ser feita de raiz, **não é permitida a utilização de quaisquer bibliotecas externas** (exceto as coleções e JUnit).

As funcionalidades principais deverão ter **casos de teste** correspondentes, como forma de controlar a qualidade e demonstrar a funcionalidade.




# Fase 1 - Modelo de dados

Nesta fase o objetivo é ter um conjunto de classes cujos objetos representam uma estrutura de dados JSON em memória. O JSON textual será obtido através do varrimento desta estrutura.

Não devemos gerar JSON textual diretamente, mas sim ter objetos em memória (que designaremos por *modelo*) que representam os dados. Estes por sua vez são serializados para texto (e potencialmente para ficheiros). Isto permitirá uma maior flexibilidade na manipulação dos dados (pe., fazer pesquisas e validações).

As classes do modelo deverão oferecer uma forma de varrimento baseada em *visitantes* (padrão de desenho *Visitor*). Utilizando esta forma deverá ser possível:
- serializar a estrutura para texto
- efetuar pesquisas, como por exemplo:
  - obter todas as strings
  - obter todos os objetos que têm determinadas propriedades


# Fase 2 - Inferência por reflexão

Nesta fase o objetivo é desenvolver funcionalidade que permita instanciar automaticamente o modelo (desenvolvido na Fase 1) por reflexão de quaisquer objetos de valor (*data class*).

Deverá haver uma forma padrão de instanciação que suporte:
- objetos de valor (*data class*)
- coleções (*Collection*)
- tabelas (*Map*)
- tipos primitivos
- strings
- enumerados

Como forma de adaptar a instanciação, deverão ser definidas anotações para permitir:
- excluir propriedades da instanciação
- utilizar outros identificadores


# Fase 3

(a publicar)

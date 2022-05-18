---
title: "Projeto: Biblioteca de Geração de XML"
layout: default
---

Este projeto consiste em desenvolver uma biblioteca para produzir dados no formato [XML](https://www.w3.org/XML/). Na sua forma mais simples, podemos apenas instanciar objetos da biblioteca (modelo) e serializar para texto XML. Será possível também derivar XML a partir de objetos por reflexão, podendo o processo ser adaptado por via de anotações nas classes desses objetos.


# Entrega

O projeto deverá ser alojado num repositório [GitHub](https://github.com) privado, mas com permissões de acesso ao utilizador *andre-santos-pt*. Dado que se trata de uma biblioteca disponível para terceiros utilizarem, para além do código fonte, o repositório terá também que conter no Wiki tutoriais sobre:
1. como utilizar o modelo de dados (Fase 1)
2. como utilizar a inferência automática (Fase 2)
3. como desenvolver plugins (Fase 4)

# Desenvolvimento

O objetivo é assumir o papel de quem está a desenvolver algo para terceiros utilizarem (biblioteca). Assim sendo, aspetos de **API (forma e documentação)** deverão ser tidos em conta. Por outro lado, a biblioteca deverá oferecer alguma flexibilidade na sua utilização para permitir aplicá-la num leque alargado de situações.


**Atenção:**

1. As funcionalidades principais deverão ter **casos de teste** correspondentes, como forma de controlar a qualidade e demonstrar a funcionalidade.

2. A funcionalidade terá que ser feita de raiz, **não** é permitida a utilização de quaisquer bibliotecas externas (exceto as coleções e JUnit).

3. Não será exigido o *parsing* de XML (textual para objetos em memória).



## Fase 1 - Modelo de dados

Não devemos gerar XML textual diretamente, mas sim ter objetos em memória (que designaremos por *modelo*) que representam o conteúdo dos mesmos.  Isto permitirá uma maior flexibilidade na manipulação dos dados (pe., fazer pesquisas e validações).

Nesta fase o objetivo é ter um conjunto de classes cujos objetos representam a estrutura de dados de um documento XML em memória. O XML textual (serialização) será obtido através do varrimento desta estrutura.

### Requisitos

Apenas é necessário suportar os elementos fundamentais de XML, nomeadamente:
- cabeçalho ```<?xml ... >```

- entidades ```<entity>```  ```<simpleEntity/>```

- atributos ```<entity att="...">```

- texto entre entidades ```<entity>qualquer coisa</entity>```

- entidades aninhadas ```<entity><child>...</child><child><subchild/></child></entity>```

- *escaping* de caracteres especiais (**<**, **>**, **&**, **"**, **'**)

Para efeitos de simplificação, consideramos que o conteúdo entre as entidades consiste exclusivamente em texto simples (sem *tags*) ou numa sequência (zero ou mais) de entidades.

As classes do modelo deverão oferecer uma API de varrimento baseada em *visitantes* (padrão de desenho *Visitor*). Esta API deverá ser demonstrada com as seguintes funcionalidades:
1. serialização da estrutura para texto
2. função de procura de entidade (critério passado como função decisora)
3. função para construir um documento XML com base na filtragem dos elementos de outro documento XML


## Fase 2 - Inferência por reflexão

Ao passo que utilizando o modelo, os documentos XML são instanciados manualmente (compondo objetos das classes da biblioteca), nesta fase o objetivo é desenvolver funcionalidade que permita instanciar automaticamente o modelo por reflexão. Isto é, dado um objeto oferecer uma forma genérica de obter uma representação XML do mesmo.

**Atenção:** a reflexão não deverá produzir XML textual diretamente, mas sim objetos das classes do modelo (Fase 1).

Pretende-se uma forma padrão de instanciação para objetos de valor (*data class*), que suporte:
- tipos primitivos
- strings
- enumerados
- coleções (*Collection*)

Como forma de adaptar a instanciação por reflexão, deverão ser definidas as seguintes anotações:

- **@XmlName("someId")** para definir nomes de entidades e atributos XML, diferentes dos identificadores do código Kotlin. ```<someId>```

- **@XmlTagContent** para definir que propriedade dos objetos será utilizada para o conteúdo entre as *tags*. ```<tag>...</tag>```

- **@XmlIgnore** para marcar propriedades dos objetos a ignorar na geração de XML.




## Fase 3 - Editor

Nesta fase pretende-se desenvolver um editor de XML, utilizando como base o modelo de dados da Fase 1.

### Requisitos

1. A arquitetura do editor deve ser baseada no padrão MVC, sendo as classes da Fase 1 adaptadas para efeito.
2. As funcionalidades do editor devem permitir:
  - Edição de atributos
  - Renomear entidades
  - Adicionar e remover atributos
  - Adicionar e remover entidades
  - Histórico de acções com *undo/redo*
  - Gravar o estado para um ficheiro


### Interface gráfica
A interface gráfica de uma aplicação é muito relevante para a sua usabilidade. Contudo, dado que nem as interfaces gráficas nem usabilidade aplicacional são matérias da UC, neste projeto as mesmas podem ficar "toscas" (ou "feias" mesmo ☺), dado que essa característica não terá peso na avaliação. Boas interfaces gráficas implicam um esforço de desenvolvimento considerável, e neste projeto pretende-se que o esforço incida nas partes relacionadas com a matéria.

O seguinte vídeo ilustra a ideia do editor. Este aspeto é só para referência, o trabalho a desenvolver pode ter outro aspeto.

<video width="500" controls>
  <source src="demofase3.mov" type="video/mp4">
Your browser does not support the video tag.
</video>

Recomenda-se a utilização de Java Swing, por razões práticas e por a GUI em si não ser o mais relevante no projeto. Podem ser utilizadas outras tecnologias de GUI, mas por "conta própria". No sentido de aliviar o esforço respeitante à parte gráfica, fornecem-se os esqueletos de componentes Swing utilizados na implementação do vídeo. ([ProjHelpers.kt](ProjHelpers.kt)) Estes poderão ser usados e adaptados livremente, mas contudo, a sua utilização não é obrigatória.



## Fase 4 - Editor com Plugins
Nesta última fase, o objetivo é tornar o editor de XML numa *framework* que permite acrescentar e customizar funcionalidade através de *plugins*. Pretendem-se dois tipos de *plugins*, que permitem nomeadamente, acrescentar de comandos e alterar a forma como é feita a edição de valores. Para a concretização deve ser utilizada injeção de dependências por via da biblioteca resultante do exercício (*Window plugin*).

O seguinte vídeo ilustra as duas formas de estender a *framework*. Podemos observar que há um comando adicional para adicionar entidades *event* (já com atributos) que só é aplicável na entidade *calendar*. Por outro lado, vemos que alguns atributos têm uma visualização diferente, nomeadamente *event.date* e *event.mandatory*.

<video width="500" controls>
  <source src="demofase4.mov" type="video/mp4">
Your browser does not support the video tag.
</video>


### Comandos
Um dos tipos de *plugin* visa permitir novos comandos no editor, em complemento aos que já existem na base. Os novos comandos deverão ser integrados no menu dos comandos base, bem como na infraestrutura de *undo/redo*. Os comandos poderão definir se estão disponíveis ou não, consoante o contexto.

Exemplos:
- Adicionar elementos XML predefinidos para um domínio (pe. HTML). Por exemplo, a criação de uma entidade *body* só estaria disponível no contexto da entidade *html* e caso esta última não contivesse já uma.
- Efetuar uma validação de uma entidade (verificar se existem determinados atributos e se os valores são válidos).

### Edição de atributos
No editor base, todos os atributos XML são editados como texto (sem restrições). Este tipo de *plugin* possibilitará alterar os *widgets* que são utilizados para editar e visualizar o texto dos atributos de forma que possam ser utilizados *widgets* especializados consoante o atributo em questão.

Exemplos:
- o atributo *date* das entidades *event*, do qual se espera uma data num formato bem definido, ser editado com um *widget* específico para para datas.
- o atributo *mandatory* das entidades *evaluationItem*, do qual se espera um booleano ("true" ou "false") ser visualizado e editado através de uma *checkbox*.


### Aplicações Exemplo
Em complemento à *framework*, como forma de ilustrar o desenvolvimento de extensões, deverão ser desenvolvidas duas aplicações baseadas em *plugins* com algum realismo (ie. *hello world* não basta). Isto é importante para ajudar a averiguar se a flexibilidade oferecida às extensões é satisfatória.

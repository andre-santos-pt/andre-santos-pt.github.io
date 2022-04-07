---
title: "Projeto: Biblioteca de Geração de XML"
layout: default
---

Este projeto consiste em desenvolver uma biblioteca para produzir dados no formato [XML](https://www.w3.org/XML/). Na sua forma mais simples, podemos apenas instanciar objetos da biblioteca (modelo) e serializar para texto XML. Será possível também derivar XML a partir de objetos por reflexão, podendo o processo ser adaptado por via de anotações nas classes desses objetos.


# Entrega

O projeto deverá ser alojado num repositório [GitHub](https://github.com) privado, mas com permissões de acesso ao utilizador *andre-santos-pt*. Dado que se trata de uma biblioteca disponível para terceiros utilizarem, para além do código fonte, o repositório terá também que conter no Wiki tutoriais sobre:
1. como utilizar o modelo de dados (Fase 1)
2. como utilizar a inferência automática (Fase 2)
3. ... a publicar ... (Fases 3 e 4)

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


## Fase 3

(a publicar)

## Fase 4

(a publicar)

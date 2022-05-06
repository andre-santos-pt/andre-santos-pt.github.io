---
title: Frameworks e plugins
---

# Bibliotecas
A noção de biblioteca é algo com que lidamos desde o início da aprendizagem da programação, por exemplo ao utilizar classes pre-existentes para manipular estruturas de dados. As bibliotecas são essenciais para a reutilização de código, e consequente redução de esforço, bem como para alcançar uma maior fiabilidade do software a desenvolver, dado que uma biblioteca terá sido previamente testada e experimentada.

Para utilizar uma biblioteca, o código cliente estabelece uma dependência das classes da mesma. Quando necessária, a funcionalidade oferecida pela biblioteca é utilizada, instanciando as suas classes e invocando operações. O controlo do fluxo de execução é feito pelo código cliente, ao passo que a biblioteca tem o papel de ferramenta para auxiliar a concretização.


# Frameworks
Uma *framework* consiste também em código reutilizável, mas difere de uma biblioteca no sentido em que uma *framework* é já por si uma aplicação, com controlo de fluxo próprio, ainda que possa estar incompleta.
É uma plataforma sobre a qual vamos desenvolver uma aplicação, permitindo de forma estruturada desenvolver uma **família de aplicações** relacionadas que partilham uma base comum, mas diferem nalgumas partes. Uma *framework* permite maiores graus de reutilização, mas impõe mais restrições uma vez que lógica de funcionamento é imposta pela a mesma, saindo em grande parte do controlo das aplicações.

Um sistema grande e complexo, ainda que nem sempre deliberadamente, pode evoluir para uma natureza de *framework*. A gestão da complexidade requer alguma forma de componentização, e o número pessoas envolvidas no desenvolvimento promove uma definição mais clara das fronteiras entre componentes, a bem da coerência arquitetural do sistema.

## Princípio aberto-fechado (*Open-closed*)
O princípio de desenvolvimento aberto-fechado é baseado na ideia que o software deve ser fechado para modificação (ie. alterações de código), mas aberto para extensão (ie. acrescentar/adaptar funcionalidades). Parece um pouco paradoxal, pois como pode o software evoluir sem o mesmo ser modificado? A chave para alcançar o princípio aberto-fechado são os mecanismos de polimorfismo.

Suponhamos um componente que executa uma das suas tarefas utilizando várias estratégias possíveis. Se a solução seguir uma estrutura semelhante à ao código em baixo, a inclusão de novas estratégias não antecipadas inicialmente implica a alteração do código. Posto isto, este pedaço de software não se caracteriza como sendo "fechado", pois a inclusão de novas estratégias implica a sua modificação.

{% include code code="
class Component {
  var strategyId = ...
  fun task() {
    if(strategyId == 0)
      // do this
    else if(strategyId == 1)
      // do that
    else
      ...
  }
}
"
%}

Uma solução baseada em polimorfismo com uma interface que abstrai a estratégia concreta, permite que o componente seja "fechado", não requerendo modificações no código aquando da utilização de novas estratégias. Por outro lado, é "aberto" porque é possível de facto estendê-lo com novo comportamento, desenvolvendo uma classe compatível e configurando o componente.

{% include code code="
interface Strategy {
  fun execute(...)
}

class Strategy1 : Strategy { ... }

class Strategy2 : Strategy { ... }

class Component {
  var strategy: Strategy = ...
  fun task() {
    strategy.execute(...)
      ...
  }
}
"
%}


Uma *framework* é desenhada de raiz tendo em conta o princípio aberto-fechado, permitindo que extensões sejam adicionadas sem alterar a base, nem tão pouco ter que compreender (muito) do seu funcionamento interno. Frequentemente as extensões são designadas por *plugins* (peças que encaixam/desencaixam na base).

## Inversão de controlo
Quando não desenvolvemos com base numa *framework*, uma função que incluímos no nosso código, será invocada também a partir do nosso código. A partir do momento que as funções que desenvolvemos são invocadas externamente ao nosso código, temos **inversão de controlo**. Esta noção é também por vezes referida como *hollywood principle* (*"Don't Call Us, We'll Call You."*).


### Funções de ordem superior
O caso mais simples de inversão de controlo é uma função de ordem superior que recebe como parâmetro outra função. A função passada pode ser também utilizada noutros contextos, mas frequentemente é apenas invocada pela função de ordem superior.

No exemplo em baixo, a função *filter* recebe uma função *predicate* (lambda) para decidir quais os elementos a incluir, sendo esta apenas invocada por *filter* (código externo), e não pelo código cliente. Neste simples caso, o controlo de execução foi invertido.

{% include code code="
val predicate = { n -> n > 0 }
list.filter(predicate)
"
%}

### Plugins

A inversão de controlo é central na forma de funcionar das *frameworks*, pois embora as extensões forneçam funcionalidade, o controlo da utilização da mesma é feito pela *framework*. Ou seja, esta terá que invocar operações fornecidas pelas extensões. Por exemplo, uma aplicação (*framework*) pode permitir que extensões forneçam comandos (definindo o seu comportamento), porém a sua integração em botões, menus, etc, fica a cargo da *framework*.

A possibilidade de *plugins* implica que algumas interfaces da *framework* sejam expostas, para que as extensões possam escrever classes compatíveis. No contexto do exemplo acima, um *plugin* poderia consistir numa estratégia a ser utilizada, sendo a interface *Strategy* exposta.

Porém, para que um *plugin* encaixe numa *framework*, precisamos ainda de uma forma desta carregar as classes externas "desconhecidas" (não tem dependências para as mesmas). O carregamento das classes externas pode ser feito com recurso a carregamento de classes dinâmico. Isto consiste procurar classes disponíveis no *classpath* por nome (*string*). Os nomes a utilizar ficam frequentemente guardados num descritor fornecido pelo *plugin* (ficheiro de texto, pe. XML, JSON, Properties) que é lido pela *framework* aquando do carregamento do plugin.  

{% include code code="
val clazz: KClass<*> = Class.forName(\"NomeQualificado\").kotlin
"
%}

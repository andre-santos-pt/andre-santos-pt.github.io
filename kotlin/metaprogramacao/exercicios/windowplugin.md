---
title: Window plugin
exer: true
---

O objetivo deste exercício é desenvolver uma biblioteca para injeção de dependências utilizando anotações. Vamos partir de uma aplicação que consiste numa janela com botões que realizam ações sobre a mesma.

### Preparação

Descarregar o código [Window.kt](Window.kt), que contém a aplicação que nos servirá de caso de teste para a injeção de dependências. Descarregar também [Actions.kt](Actions.kt), que contém implementações das interfaces definidas em *Window*.

Neste ponto de partida, a classe *Window* depende de *DefaultSetup* (que implementa *FrameSetup*) e de *Move* e *Size* (que implementam *Action*). No final do exercício, a ideia é que *Window* já não dependa das implementações concretas, sendo estas dadas num  ficheiro de configuração, com o seguinte conteúdo.

*di.properties*
<pre>
Window.setup=DefaultSetup
Window.actions=Move,Size
</pre>

Este ficheiro indica que a propriedade *setup* dos objetos *Window* será inicializada com um objeto *DefaultSetup*, e que a lista da propriedade *actions* será populada com um objeto *Center* e outro *Size*.

Desta forma, embora num caso muito simplificado, a nossa é agora uma micro-*framework* onde podemos acrescentar *plugins* através do ficheiro de configuração.


### 1. Injeção de propriedade

Numa primeira abordagem, pretende-se uma anotação **@Inject** que possa ser utilizada em propriedades, como no seguinte exemplo. Em Kotlin, os valores das propriedades têm que ser definidos no momento da construção do objeto. Porém, no caso das variáveis (**var**) podemos utilizar o modificador **lateinit** indicando que um valor será fornecido posteriormente à criação do objeto.

{% include code code="
@Inject
private lateinit var setup: FrameSetup
"
%}

A anotação fará com que um objeto seja injetado na criação, de acordo com o conteúdo do ficheiro de configuração (*DefaultSetup* no exemplo acima).  Isso será feito pela biblioteca que implementaremos neste exercício, através de reflexão. Pretende-se uma API do estilo do que se apresenta em seguida.

{% include code code="
fun main () {
    val w = Injector.create(Window::class) // em vez de Window()
    w.open()
}
"
%}

A operação *create* irá instanciar um objeto *Window*, e seguidamente preencher as propriedades anotadas com @Inject. Assumiremos que o ficheiro de configuração está na raiz de execução e tem um nome fixo (*di.properties*).

Por forma a carregar uma classe dinamicamente dado o seu nome, podemos fazer o seguinte.
{% include code code="
val clazz: KClass<*> = Class.forName(\"NomeQualificado\").kotlin
"
%}





### 2. Injeção de elemento em propriedade (coleção)
Como forma de complementar a anotação anterior, pretende-se agora outra anotação semelhante **@InjectAdd** cujo o propósito é anotar propriedades que consistam numa coleção de elementos, indicando que na mesma serão adicionados vários objetos consoante o conteúdo do ficheiro de configuração.

{% include code code="
@InjectAdd
private val actions = mutableListOf<Action>()
"
%}

Caso o ficheiro de configuração fosse o apresentado acima, a lista action seria populada com um objeto *Center* e outro *Size*.


### 3. Plugins para exercício PairDataSet MVC

Utilize a biblioteca desenvolvida para permitir a injeção de comandos (*plugins*) no exercício PairDataSet MVC.

**Dicas:**
1. Poderá ser necessário rever a interface para os comandos utilizada anteriormente.
2. Como regra (típica), exigir que as classes que implementam comandos nas extensões tenham um construtor sem parâmetros. O facto de ter várias assinaturas de construtores possíveis complicará a solução, porque não é óbvio como fornecer argumentos.

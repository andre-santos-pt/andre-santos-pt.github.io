---
title: Visitante (padrão de desenho)
---

Ao passo que será simples escrever código para um varrimento pelos elementos de uma estrutura de dados linear cujos elementos têm o mesmo tipo (pe. uma lista), quando estamos perante uma estrutura de objetos hierárquica, potencialmente com diferentes tipos de objetos, a tarefa poderá não ser trivial.

O padrão de desenho **Visitante** (*Visitor*) consiste num tipo de solução para facilitar o varrimento de estruturas de dados hierárquicas. A metáfora associada é a do objeto "visitante" que consegue fazer uma visita por todos os elementos (ou parte) da estrutura, sem que tenha que entender como navegar de uns elementos para os outros. Um caso típico são estruturas recursivas (ver diagrama), onde é formada uma árvore com nós folha (terminais) e nós compostos (que contêm outros nós).

![](visitante.png)

# Interface dos visitantes
O elemento central quando aplicamos este padrão é uma interface que incluí uma operação para cada elemento visitável da estrutura com que estamos a trabalhar. Tipicamente, é utilizado o identificador *visit* sobreposto para os vários tipos de elementos.

{% include code code="
interface Visitor {
    fun visit(c: Composite)
    fun visit(l: Leaf)
}
"
%}

O propósito da interface é permitir que objetos compatíveis (os *visitantes*) sejam utilizados no varrimento da estrutura. Ao ser visitado um nó de determinado tipo, será invocada a operação *visit* correspondente.

A interface acima consiste na forma mais elementar, porém, normalmente as operações têm uma implementação por omissão, para evitar terem que ser definidas quando a utilização não necessita de tratar alguns tipos de elementos. Por outro lado, as operações de visita a elementos compostos poderão ser booleanas, por forma a indicar se o varrimento deve prosseguir para dentro do mesmo. Desta forma é possível efetuar varrimentos parciais da estrutura. Por fim, também é frequente ter uma operação para sinalizar o fim do processamento de um elemento composto, para que possa ser tomada uma ação nesse momento caso necessário.

{% include code code="
interface Visitor {
    fun visit(c: Composite): Boolean = true
    fun endVisit(c: Composite) { }
    fun visit(l: Leaf) { }
}
"
%}

# Adaptação da estrutura

Uma vez tendo a interface dos visitantes, é necessário adaptar a estrutura para permitir o varrimento. Tipicamente, é utilizado o identificador *accept* ("aceitar o visitante") para a operação que desencadeia o varrimento.

{% include code code="
abstract class Element {
    abstract fun accept(v: Visitor)
}
"
%}

O caso mais simples é o dos elementos folha, e consiste apenas em invocar a operação correspondente.

{% include code code="
class Leaf : Element() {
    override fun accept(v: Visitor) {
        v.visit(this)
    }
}
"
%}

Já os elementos compostos exigem um procedimento mais elaborado. Assumindo a segunda versão da interface *Visitor*, a operação de visita é invocada, e apenas caso devolva verdadeiro se prossegue para os elementos filho, invocando *accept* em cada um. Por fim, depois do processamento dos filhos, é invocado a operação de fim de visita (*endVisit*).

{% include code code="
class Composite : Element() {
    val children : MutableList<Element> = mutableListOf()

    override fun accept(v: Visitor) {
        if(v.visit(this))
            children.forEach {
                it.accept(v)
            }
        v.endVisit(this)
    }
}
"
%}


# Utilização
Uma vez tendo a estrutura de dados adaptada, podemos em qualquer um dos seus elementos iniciar uma visita fornecendo o objeto visitante.


{% include code code="
val c: Composite = ...
val countLeafs = object : Visitor {
  var count = 0
  override fun visit(l: Leaf) {
    count++
  }
}
c.accept(countLeafs)
val n = countLeafs.count

"
%}

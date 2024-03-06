---
title: File/Directory Composite
exer: true
---

A organização de ficheiros em diretórios consiste numa árvore, onde os diretórios são nós compostos (que contêm filhos) e os ficheiros são nós folha. Este tipo de estrutura pode ser representada através de uma estrutura de dados recursiva (padrão de desenho [Objetos Compostos](../../padroesdesenho/objetoscompostos)), tal como ilustrado na figura. Os elementos podem ser de dois tipos: ficheiros ou diretórios (*Element* é interface). Os diretórios contêm outros elementos, os quais podem ser diretórios ou ficheiros. Todos os elementos terão o seu pai definido (diretório), porém na raiz da estrutura o pai não estará definido.

![](filecomposite.png)

### 1. Estrutura de dados (Objetos Compostos)

Implemente a estrutura de dados de acordo com o que é apresentado no diagrama. As propriedades deverão ser calculadas, por forma a que uma alteração na estrutura (pe. acrescentar um ficheiro) resultará em valores diferentes.

- *depth* refere-se à profundidade na árvore (elemento raiz (*root*) terá profundidade um).
- *path* é o caminho absoluto do ficheiro (pe. */Users/andre/Desktop/file.txt*)
- *deepElementCount* significa número total elementos, considerando a contagem recursivamente (pelos subdiretórios).
- *toText* corresponde a um formato textual da árvore (usar profundidade dos elementos para tabulação).

<pre>
artists
  beatles
    help
      i need you
    let it be
      get down
      two of us
</pre>

Para efeito de comparação de estruturas idênticas (e testes), é conveniente representar a estrutura através de objetos de valor. Desta forma, a utilização de **==** e *equals* terá em conta a comparação de conteúdo, e não a identidade dos objetos.

{% include code code="
sealed interface Element {
    val name: String
    val parent: DirectoryElement?

    val depth: Int
        get() = 0 // TODO
}

data class DirectoryElement(
    override val name: String,
    override val parent: DirectoryElement? = null
) : Element {
    
}
"
%}

Experimente a instanciação destas classes com pelo menos três níveis de profundidade, testando as propriedades.

Pode utilizar o seguinte caso de teste como ponto de partida.
{% include code code="
class TestFileComposite {
    val artists = DirectoryElement(\"artists\")
    val beatles = DirectoryElement(\"beatles\", artists)
    val help = DirectoryElement(\"help\", beatles)
    //val iNeedYou = FileElement(\"i need you\", help)
    val letItBe = DirectoryElement(\"let it be\", beatles)
    //val getDown = FileElement(\"get down\", letItBe)
    //val twoOfUs = FileElement(\"two of us\", letItBe)

    @Test
    fun testDepth() {
        assertEquals(1, artists.depth)
        assertEquals(2, beatles.depth)
        assertEquals(3, help.depth)
        assertEquals(3, letitbe.depth)
    }
}
"
%}

### 2. Carregamento de árvore

Nesta alínea pretende-se obter uma instância da estrutura anterior, obtida através do sistema de ficheiros. Escreva um teste, e uma função de extensão de File para concretizar o pretendido.

{% include code code="
fun File.toDirectoryTree(): Element {

}
"
%}


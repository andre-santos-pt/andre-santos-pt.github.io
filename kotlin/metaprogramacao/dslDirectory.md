---
title: Linguagens de Domínio Internas (Internal DSLs)
---
As linguagens específicas de domínio, vulgarmente conhecidas pela sigla DSL (*domain specific language*) são linguagens de software especializadas para um tipo de problema (por oposição às *general-purpose languages* tais como Kotlin, Java Python, etc). A especificidade de uma DSL faz com que o âmbito da utilização da mesma seja reduzido. Porém, uma das principais vantagens de uma DSL é fornecer uma forma de expressão prática e adaptada ao domínio em questão.

Uma DSL *externa* é aquela que tem a sua infraestrutura independente, tendo a liberdade sintática total, podendo até ser uma DSL com representação gráfica. HTML ou SQL são consideradas DSLs dado que são aplicáveis num aspeto de desenvolvimento do sistema. A utilização da plataforma [OutSystems](https://www.outsystems.com) é baseada em parte numa DSL gráfica.

Por outro lado, uma DSL *interna* é aquela cuja sintaxe está embutida noutra linguagem existente (chamemos-lhe a linguagem "mãe"). As DSLs internas são normalmente desenvolvidas para problemas menos generalistas, como por exemplo para apoiar a utilização de componentes proprietários.

Ao passo que as DSLs externas não têm restrições quanto à sua forma, são dispendiosas de implementar, pois a sintaxe e semântica terão que ser definidas de raiz, ao que acresce a necessidade de ter um bom editor para a mesma. As DSLs internas estão constrangidas pelas possibilidades sintáticas da linguagem mãe, mas têm um custo de desenvolvimento reduzido, pois toda a infraestrutura da linguagem já existe.

Esta secção aborda alguns mecanismos disponíveis em Kotlin que facilitam o desenvolvimento de DSLs internas, oferecendo alguma liberdade sobre a sintaxe. Frequentemente, uma DSL interna é baseada num conjunto de classes (ou biblioteca) já existentes, e o seu papel é fornecer uma interface para as mesmas que:
1. alivie a densidade da sintaxe, permitindo descrições mais sucintas
2. facilite a legibilidade, não só por (1) mas por formas de compor objetos mais explícitas
3. facilite a manipulação do código devido a (1, 2)



# Exemplo

Para efeitos de ilustração, consideremos uma estrutura hierarquica para diretórios e ficheiros implementada com as seguintes classes.

{% include code code="
sealed interface Element {
    val name: String
    val parent: DirectoryElement?
}

data class FileElement(
    override val name: String,
    override val parent: DirectoryElement? = null
) : Element {
    init {
        parent?.children?.add(this)
    }
}

data class DirectoryElement(
    override val name: String,
    override val parent: DirectoryElement? = null,
) : Element {
    internal val children: MutableList<Element> = mutableListOf()

    init {
        parent?.children?.add(this)
    }
}
"
%}

O seguinte código ilustra a instanciação de uma estrutura de diretórios, onde podemos constatar que há alguma dificuldade em entender a composição dos elementos (quem é filho de quem), porque a àrvore não fica muito explícita na sintaxe.

{% include code code="
val artists = DirectoryElement(\"artists\")
val beatles = DirectoryElement(\"beatles\", artists)
val help = DirectoryElement(\"help\", beatles)
val iNeedYou = FileElement(\"i need you\", help)
val letItBe = DirectoryElement(\"let it be\", beatles)
val getDown = FileElement(\"get down\", letItBe)
val twoOfUs = FileElement(\"two of us\", letItBe)

"
%}

{% include code code="
artists
	beatles
		help
			i need you
		let it be
			get down
			two of us
"
%}


# Lambdas com instância implícita (receiver)
A utilização de lambdas em Kotlin possibilita uma conveniência sintática que aquando da definição da expressão lambda. A seguinte sintaxe faz com que o argumento passado à função esteja implicitamente disponível no corpo da expressão lambda (através de *this*, que pode ser omitido).

{% include code code="
fun directory(name: String, build: DirectoryElement.() -> Unit) =    
    DirectoryElement(name).apply { 
        build(this)
    }

fun DirectoryElement.directory(name: String, build: DirectoryElement.() -> Unit) =
    DirectoryElement(name, this).apply {
        build(this)
    }

fun DirectoryElement.file(name: String) = FileElement(name, this)

"
%}

Estas funções permitem instanciar a hierarquia de diretórios da seguinte forma.

{% include code code="
val dir = directory(\"artists\") {
  this.directory(\"beatles\") {
    this.directory(\"help\") {
      this.file(\"i need you\")
    }
  }
}
"
%}

O código pode ser simplificado omitindo o parâmetro implícito *this*.
{% include code code="
val dir = directory(\"artists\") {
  directory(\"beatles\") {
    directory(\"help\") {
      file(\"i need you\")
    }
  }
}
"
%}


# Definição de operadores
O comportamento dos operadores da linguagem pode ser definido para tipos específicos, recorendo ao modificador *operator* e um nome de função predefinido (cada operador tem um nome de função associado).

A função seguinte define que a sintaxe de acesso *[...]* quando aplicada a um *DirectoryElement* resulta na devolução de um filho *FileElement* com dado nome.

{% include code code="
operator fun DirectoryElement.get(name: String) =
    children.find { it.name == name } as FileElement

val f : FileElement = help[\"i need you\"]
"
%}

A função seguinte define que o operador */* quando aplicado a um *DirectoryElement* resulta na devolução de um filho *DirectoryElement* com dado nome.

{% include code code="
operator fun DirectoryElement.div(name: String): DirectoryElement =
    children.find { it.name == name } as DirectoryElement

val help : DirectoryElement = dir / \"beatles\" / \"help\"
"
%}

As duas extensões podem ser usadas em combinação.

{% include code code="
val f: FileElement = (dir / \"beatles\" / \"help\")[\"i need you\"]
"
%}


# Notação infixa em invocações
As funções de instância ou de extensão que tenham apenas um parâmetro podem ter modificador *infix*, permitindo que a mesma possa ser invocada como se de um operador se tratasse.

{% include code code="
infix fun DirectoryElement.files(
    action: (FileElement) -> Unit
) {
    children.filterIsInstance<FileElement>()
        .forEach { action(it) }
}

dir / \"beatles\" / \"help\" files {
  println(it)
}
"
%}

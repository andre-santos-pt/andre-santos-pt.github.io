---
title: File scanner
exer: true
---

### Experimentação

Utilizando a class *File* do Java, podemos obter os ficheiros contidos num diretório da seguinte forma:
{% include code code="
val path = File(System.getProperty(\"user.dir\")) // diretorio de execucao
val children: Array<File> = path.listFiles() // filhos do diretorio (ficheiros e diretorios)
val files: List<File> = children.filter { it.isFile } // filtro para incluir so ficheiros
"
%}
Porém, o processo não ocorre recursivamente (entrando em subdiretórios). O objetivo desta sequência de exercícios é desenvolver uma função para obter os ficheiros de um diretório, incluindo também nos seus subdiretórios (e sucessivamente).

<hr>


### 1. Função recursiva

Desenvolva uma função para obter a listagem de ficheiros, mas incluindo subdiretórios. Será útil recorrer a um procedimento auxiliar recursivo, que poderá ser definido internamente. Para tornar o resultado determinístico no que diz respeito à ordem pela qual os ficheiros serão recolhidos, pode ordenar alfabeticamente o conteúdo de um diretório.

{% include code code="
val list = dir.listFiles()?.sortedBy { it.nameWithoutExtension }
"
%}

### 2. Função com critério de aceitação opcional

Evolua a função anterior por forma a permitir fornecer um critério de aceitação de ficheiros a incluir na filtragem. Por omissão, todos o ficheiros serão aceites.

O código permitiria por exemplo obter todos os ficheiros que têm determinada extensão.

{% include code code="
val kotlinFiles = deepListFiles(File(path)) {
    it.name.endsWith(\".kt\")
}
"
%}

### 3. Função de extensão

Evolua ainda a função desenvolvida para uma função de extensão, por forma a poder invocá-la como se se tratasse de uma função de *File*.

{% include code code="
val kotlinFiles = path.deepListFiles() {
    it.name.endsWith(\".kt\")
}
"
%}

### 4. Estratégia de ordenação variável

Defina uma parâmetro adidional opcional para permitir variar a ordem com que os ficheiros são listados, utilizando a seguinte interface ([Padrão Estratégia](../../padroesdesenho/estrategia)).

{% include code code="
interface ListingStrategy {
    fun sort(dirContents: Array<File>)
}
"
%}

Diferentes implementações desta interface permitirão que seja ordenado de forma diferente o conteúdo de um diretório. Por exemplo, a implementação por omissão poderá consistir no seguinte.

{% include code code="
object Ascending : ListingStrategy {
    override fun sort(dirContents: Array<File>) {
        dirContents.sortBy { it.nameWithoutExtension }
    }
}
"
%}
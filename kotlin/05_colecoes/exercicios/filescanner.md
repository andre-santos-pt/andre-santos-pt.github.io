---
title: File scanner
exer: true
---

### Experimentação

Utilizando a class *File* do Java, podemos obter os ficheiros contidos num diretório da seguinte forma:
{% include code code="
val path = File(System.getProperty(\"user.dir\")) // diretorio de execucao
val children = path.listFiles() // filhos do diretorio (ficheiros e diretorios)
val files = children.filter { it.isFile } // filtro para incluir so ficheiros
"
%}
Porém, o processo não ocorre recursivamente (entrando em subdiretórios). O objetivo desta sequência de exercícios é desenvolver uma função para obter os ficheiros de um diretório, incluindo também nos seus subdiretórios (e sucessivamente).

<hr>


### 1. Função recursiva

Desenvolva uma função para obter a listagem de ficheiros, mas incluindo subdiretórios. Será útil recorrer a um procedimento auxiliar recursivo, que poderá ser definido internamente.


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

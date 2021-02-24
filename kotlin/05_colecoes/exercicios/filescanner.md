---
title: File scanner
exer: true
---

A seguinte função obtém os ficheiros contidos num diretório. Porém, o processo não ocorre recursivamente (entrando em subdiretórios).

{% include code code="
fun dirFiles(dir: File): List<File> {
    val files = mutableListOf<File>()
    dir.listFiles().forEach {
        if(it.isFile)
           files.add(it)
    }
    return files
}
"
%}

O mesmo resultado dado pela função poderia ser obtido da seguinte forma.
{% include code code="
val files = path.listFiles().filter { it.isFile }
"
%}
O objetivo desta sequência de exercícios é desenvolver uma função para obter os ficheiros de um diretório, incluindo também nos seus subdiretórios (e sucessivamente).

<hr>


### 1. Função recursiva

Escreva uma função para obter a listagem de ficheiros incluindo subdiretórios.
Será útil recorrer a um procedimento auxiliar recursivo, que poderá ser definida internamente.

{% include code code="
fun dirFilesRec(dir: File): List<File> {

}
"
%}

### 2. Função com critério de aceitação opcional

Evolua a função anterior por forma a permitir fornecer um critério de aceitação de ficheiros a incluir na filtragem. Por omissão, todos o ficheiros são aceites.

Isto permitirá por exemplo obter todos os ficheiros que têm determinada extensão.

{% include code code="
dirFilesRec(File(path)) {
    it.name.endsWith(\".kt\")
}.forEach {
    println(it)
}
"
%}

### 3. Função de extensão

Evolua a função desenvolvida para uma função de extensão, por forma a poder invocá-la como se se tratasse de uma função de *File*.

{% include code code="
f.dirFilesRec().forEach {
  println(it)
}
"
%}

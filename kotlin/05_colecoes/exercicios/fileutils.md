---
title: Funções auxiliares para ficheiros
exer: true
---

Partindo dos seguintes casos de teste, desenvolva as funções pretendidas.

#### Propriedade para saber extensão de ficheiro

(*Extension property*, já existente nas bibliotecas do Kotlin)

{%include code code="
@Test
fun testFileExtension() {
    assertEquals(\"\", File(\"random\").extension)
    assertEquals(\"kt\", File(\"Test.kt\").extension)
    assertEquals(\"kts\", File(\"build.gradle.kts\").extension)
}
"
%}

#### Propriedade para saber profundidade de ficheiro/diretório na árvore

(*Extension property*)

{%include code code="
@Test
fun testFileDepth() {
    assertEquals(0, File(\"\").depth)
    assertEquals(1, File(\"dir\").depth)
    assertEquals(2, File(\"dir/subdir\").depth)
}
"
%}


#### Obter extensões de lista de ficheiro

{%include code code="
val fileList = listOf(
    File(\"random\"),
    File(\"Test.kt\"),
    File(\"Example.kt\"),
    File(\"Script.kts\")
)

@Test
fun testDistinctExtensions() {
    assertEquals(setOf(\"kt\", \"kts\"), distinctExtensions(fileList))
}
"%}

#### Contar extensão em lista de ficheiro

(*Extension function*)

{%include code code="
@Test
fun testCountExtensions() {
    assertEquals(2, fileList.countExtension(\"kt\"))
    assertEquals(0, fileList.countExtension(\"txt\"))
}
"%}

#### Filtrar ficheiros por extensão

(*Extension function*)

{%include code code="
@Test
fun testWithExtension() {
    val expected = listOf(File(\"Test.kt\"), File(\"Example.kt\"))
    assertEquals(expected, fileList.withExtension(\"kt\"))
    assertEquals(emptyList<File>(), fileList.withExtension(\"txt\"))
}
"
%}
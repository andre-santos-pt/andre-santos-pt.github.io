---
title: Funções auxiliares para ficheiros
exer: true
---

### Funções auxiliares para File

Partindo dos seguintes casos de teste, desenvolva as funções pretendidas.

#### Propriedade para saber extensão de ficheiro

(*Extension property*)

{%include code code="
@Test
fun fileExtension() {
    assertEquals(\"\", File(\"random\").extension)
    assertEquals(\"kt\", File(\"Test.kt\").extension)
    assertEquals(\"kts\", File(\"build.gradle.kts\").extension)
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
fun distinctExtensions() {
    assertEquals(setOf(\"kt\", \"kts\"), distinctExtensions(fileList))
}
"%}

#### Contar extensão em lista de ficheiro

(*Extension function*)

{%include code code="
@Test
fun countExtensions() {
    assertEquals(2, fileList.countExtension(\"kt\"))
    assertEquals(0, fileList.countExtension(\"txt\"))
}
"%}

#### Filtrar ficheiros por extensão

(*Extension function*)

{%include code code="
@Test
fun withExtension() {
    val expected = listOf(File(\"Test.kt\"), File(\"Example.kt\"))
    assertEquals(expected, fileList.withExtension(\"kt\"))
    assertEquals(emptyList<File>(), fileList.withExtension(\"txt\"))
}
"
%}
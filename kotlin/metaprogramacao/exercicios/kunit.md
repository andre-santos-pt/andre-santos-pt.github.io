---
title: KUnit
exer: true
---

Neste exercício o objetivo é fazer uma pequena biblioteca similar ao [JUnit](https://www.junit.org). Deverão existir três tipos de anotações:

- **@TestCase**, para definir um caso de teste, anotando uma função (que não deverá ter parâmetros);

- **@BeforeTestRun**, para anotar uma função (também sem parâmetros), que irá executar antes de cada caso de teste anotado com @TestCase.

- **@ExpectedException**, para anotar um caso de teste, indicando que a execução do teste tem que dar origem à exceção indicada.

Exemplo de API, para correr os casos de teste definidos na class *MyTests*.

{% include code code="
val kunit = KUnit(MyTests::class)
val results: List<TestCaseResult> = kunit.testResults()
results.forEach(::println)
"
%}

A execução deverá apresentar uma lista contendo todos os casos de teste, indicando se passaram ou falharam (e neste caso com a possível mensagem).

Para além das anotações, será útil também fornecer operações de *assertTrue* e *assertFalse* para serem utilizadas dentro dos casos de teste, lançando uma exceção quando as condições não se verificam.

**Dica:** Quando é lançada uma exceção no contexto de uma chamada de método efetuada por reflexão (por exemplo, *AssertionError* no falhar dos testes), é lançada uma exceção *InvocationTargetException*. A exceção que causou o erro pode ser obtida numa propriedade da última.

{% include code code="
try {
  kFunction.call(obj)
}
catch(e: InvocationTargetException) {
  val cause: Throwable = e.cause
  println(cause?.message)
}
"
%}


**Dica:** Para a anotação *ExpectedException* será necessário um parâmetro do tipo classe para a exceção esperada possa ser indicada.
{% include code code="
annotation class ExpectedException(val exceptionClass: KClass<out Exception>)

@TestCase
@ExpectedException(IllegalArgumentException::class)
fun testException() {
   throw IllegalArgumentException()
}
"
%}
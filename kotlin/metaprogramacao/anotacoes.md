---
title: Anotações
---

Muitas aplicações baseadas em metaprogramação tomam partido da possibilidade de anotar um programa com metadados, frequentemente designados por **anotações**. Anotar um programa tem o propósito de fornecer de forma precisa alguma informação adicional sobre os elementos do mesmo. Exemplos de anotações comuns: funções de teste (*@Test*), funções obsoletas (*@Deprecated*), atributos para serem definidos automaticamente por injeção (*@Inject*).

Embora a informação adicional por si só possa ser útil, na maior parte dos casos as anotações são desenhadas para serem processadas externamente, automatizando algum aspeto de desenvolvimento (pe. testes) ou fazendo algum tipo de verificação (pe. sinalizar quando o código cliente utiliza algum elemento obsoleto).

# Definição
Cada anotação diferente tem o seu próprio tipo, o qual terá que ser definido (ou importado) por forma a poder utilizar a anotação.

Na sua forma mais simples, podemos apenas declarar a existência do tipo de anotação através do modificador **annotation**.

{% include code code="
annotation class TestCase
"
%}

Desta forma, podemos anotar código com a anotação sem restrições relativas a que tipos de elementos são anotados. O seguinte exemplo utiliza a anotação acima para anotar a classe, uma propriedade, e uma função.

{% include code code="
@TestCase
class MyTest {

  @TestCase
  val obj = MyClass()

  @TestCase
  fun testMyClass() {
    //...
  }
  //...
}

"
%}


## Alvo

Salvo algumas situações, normalmente uma anotação é pensada para ser aplicada num tipo de elemento específico dos programas. Por exemplo, se a anotação acima *@TestCase* for pensada para marcar uma função como sendo de teste (à semelhança de [JUnit](www.junit.org)), então não fará sentido que a mesma possa ser utilizada numa propriedade, classe, ou outro tipo de elemento que não uma função.

Tipos de alvos (*targets*) principais para uma anotação são:
- classe/interface
- anotação (i.e. a anotação anota outra anotação)
- propriedade
- parâmetro
- construtor
- função


Ao definir uma anotação podemos (e na maior parte dos casos, devemos) definir quais os elementos alvo no programa. Isso é feito através de uma anotação (*@Target*) à definição da anotação. Para o exemplo acima iriamos restringir a anotação a funções. *AnnotationTarget* é um enumerado com todos os tipos de elementos possíveis. Podemos listar diferentes alvos separados por vírgula.

{% include code code="
@Target(AnnotationTarget.FUNCTION)
annotation class TestCase
"
%}

{% include error code="
@TestCase
class MyTest {
  // ...
}
"
msg="Não compila por utilização de anotação em elemento inválido."
%}



## Propriedades
As anotações podem ter propriedades para enriquecer um pouco a informação para além de uma mera "marca" que sinaliza algo. A forma de definir propriedades é igual à dos [valores compostos](../01_expressoes/valorescompostos), porém, apenas podemos utilizar alguns tipos de dados, nomeadamente:
- tipos numéricos
- strings
- classes (através de **::class**)
- enumerados
- anotações
- vetores dos tipos anteriores

Na continuação do exemplo anterior, suponhamos que cada caso de teste seria acompanhado de uma descrição.
{% include code code="
@Target(AnnotationTarget.FUNCTION)
annotation class TestCase (val desc: String)
"
%}

Desta forma, a utilização da anotação teria que fornecer um valor para a propriedade *desc*. Tal como nos valores compostos e parâmetros, podemos ter valores por omissão, e estes são definidos da mesma forma.
{% include code code="
class MyTest {
  @TestCase(\"grandessíssimo teste\")
  fun testMyClass() {
    //...
  }
  //...
}

"
%}


# Processamento por reflexão

Tal como explicado, o propósito das anotações é serem processadas externamente por uma biblioteca ou *framework*, beneficiando de funcionalidade adicional. Uma das formas de processar anotações é por via de [reflexão](reflexao), interrogando os elementos do programa pela presença de anotações. Todos os elementos reflexivos (*KClass*, *KFunction*, etc) têm as operações *hasAnnotation* e *findAnnotation*, para, respetivamente, saber se a anotação está presente, e obter a mesma.

{% include code code="
val clazz: KClass<MyClass> = MyClass::class
val isDeprecated: Boolean = clazz.hasAnnotation<Deprecated>()
"
%}

A anotação pode ser obtida por forma a ser tratada como um valor composto. No seguinte exemplo é obtida uma função aleatória da classe, e caso a mesma esteja anotada com *@TestCase* é impresso o valor da propriedade *desc*.

{% include code code="
val f: KFunction<*> = MyClass::class.declaredMemberFunctions.random()
val testAnnotation: TestCase? = f.findAnnotation<TestCase>()
testAnnotation?.let {
  println(testAnnotation.desc)
}
"
%}

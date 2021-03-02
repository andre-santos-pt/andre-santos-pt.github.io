---
title: Multimap
exer: true
---

Uma tabela (*map*) associa chaves a valores, sendo que a cada chave apenas pode estar associado um valor. Quando o objetivo é associar vários valores a uma chave, a solução terá que ser baseada em utilizar uma coleção como valor.

Por exemplo, dada uma lista de Strings, se o objetivo for indexar as palavras por letra inicial, poderíamos fazer o seguinte.

{% include code code="
fun indexMap(list: List<String>): Map<Char, List<String>> {
    val m = mutableMapOf<Char, MutableList<String>>()
    list.forEach {
        var firstChar = it.elementAt(0)
        if(!m.containsKey(firstChar))
            m[firstChar] = mutableListOf()
        m[firstChar]!!.add(it)
    }
    return m
}
"
%}

A solução não tem problema, a questão é que em situações semelhantes teremos que repetir este estilo de solução. Existe um tipo de abstração tipicamente designado por *multimap*, que funciona como uma tabela (*map*) que permite várias inserções com a mesma chave.

{% include code code="
val m = MultiMap<Char, String>()
m['A'] = \"Aladino\"
m['B'] = \"Badr al-Budur\"
m['A'] = \"Ali\"
m['B'] = \"Baba\"

println(m['A'])   // [Aladino, Ali]
println(m['B'])   // [Badr al-Budur, Baba]
println(m['C'])   // []
"
%}

O objetivo deste exercício é escrever a classe *MultiMap*.

<hr>
### 1. Estrutura de dados por composição

Defina a classe *MultiMap* tendo como base uma tabela, implementando as operações:

1. put(K, V)
2. get(K): V
3. contains(K): Boolean
4. size(): Int
5. keySet: Set\<K\>


{% include code code="
class MultiMap<K, V> {
  private val map: MutableMap<K, MutableList<V>> = mutableMapOf()

}
"
%}

### 2. Função para indexar Strings pela primeira letra
Escreva uma função com o propósito idêntico à do exemplo inicial, utilizando a classe desenvolvida.

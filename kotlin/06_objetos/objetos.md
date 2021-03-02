---
title: Objetos
---

Em capítulos anteriores lidámos com valores (simples ou compostos), os quais são manipulados com vista a calcular algo. Por exemplo, dado um valor inteiro, podemos invocar uma função para o exponenciar, obtendo um novo valor. Este último é outra coisa que não o primeiro, e não foi feita uma transformação ao valor original.

Porém, quando lidamos com entidades mais complexas que representam algo que tem um tempo de vida prolongado ao longo de um aplicação, tais como, uma base de dados, a janela de uma aplicação, uma imagem que está a ser editada interativamente. Neste caso será apropriado utilizar **objetos**, que podem ser vistos como entidades que têm identidade, bem como estado próprio que é alterado ao longo do tempo. Frequentemente, os objetos são compostos por valores, bem como por outros objetos.


# Instanciação e tipo
Os objetos têm que ser *instanciados* (ou *criados*) para que possam ser utilizados. Cada objeto tem (pelo menos) um **tipo** associado, que descreve as operações que disponíveis para o manipular.

Por exemplo, num sistema com utilizadores podemos ter uma lista com os nomes de *login*. No nosso sistema, este objeto representaria a lista de utilizadores registados, onde entram e saem registos ao longo do tempo. No exemplo em baixo temos uma instanciação de uma lista do tipo *ArrayList* que contém elementos do tipo *String*.

{% include code code="
val usernames = ArrayList<String>()
"
%}


Anteriormente, apresentámos o caso das listas, sendo que cada uma é um objeto, instanciado através de *mutableListOf(...)*. Nesse caso estamos perante uma operação que nos cria e devolve um objeto.


# Funções
Uma **função** sobre um objeto é uma operação que nos permite saber ou calcular algo com base no mesmo. Como exemplos para o caso de uma lista, são funções: saber se determinado elemento existe, obter uma versão da lista filtrada de acordo com algum critério, ou saber se está ordenada. O essencial é que a invocação da função não altere o *estado* do objeto, ou seja, este permanece igual após a operação.

{% include code code="
val hasUser = usernames.contains(\"Zé\")
"
%}

# Propriedades
Uma **propriedade** é um tipo específico de função que nos informa sobre uma característica do objeto, sendo que não fornecemos argumentos para a obter. No caso de uma lista, são propriedades: o comprimento, ou o facto de estar vazia ou não.

{% include code code="
val nUsers = usernames.size
"
%}

# Procedimentos
Um **procedimento** é uma operação que causará uma alteração no objeto. No caso de uma lista, são procedimentos: adicionar ou remover um elemento. Ao contrário das funções/propriedades, os procedimentos fazem com que o objeto vá mudando de forma ao longo do tempo.

{% include code code="
val newUser = \"Zé\"
usernames.add(newUser)
"
%}

# Objetos imutáveis
Um objeto **imutável** é aquele que não permite ser alterado após a instanciação. Isto é, não existem procedimentos disponíveis para tal. No contexto de um objeto imutável não faz sentido falar em procedimentos, pois estes implicariam que o objeto fosse *mutável*.


# Objetos únicos (*singletons*)
A maior parte dos objetos pertencerá a uma [classe](classes) (próxima secção), implicando que num sistema haverão vários objetos com a mesma estrutura. Porém, por vezes é útil definir objetos únicos, para facilitar o seu acesso de qualquer parte do sistema. Estes objetos são definidos através da palavra reservada **object**, seguido de um identificador, e o seu corpo com propriedades e funções.

{% include code code="
object SystemUsers {
    val usernames = mutableSetOf<String>()

    val logged = mutableSetOf<String>()

    fun register(user: String) {
        usernames.add(user)
    }

    fun login(user: String) {
        require(user in usernames)
        logged.add(user)
    }

    fun logout(user: String) {
        require(user in logged)
        logged.remove(user)
    }
}
"
%}

O acesso ao objeto único é feito mediante o seu identificador, e manipulado com a sintaxe normal para interagir com objetos.

{% include code code="
SystemUsers.register(\"Tony\")
SystemUsers.login(\"Tony\")
//...
SystemUsers.logout(\"Tony\")
"
%}

O recurso a objetos únicos para representar partes de um sistema deve ser evitado, pois o tipo de desenho baseado nestas soluções tende a ser pouco flexível.

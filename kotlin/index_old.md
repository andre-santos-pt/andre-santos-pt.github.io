---
layout: default
title: Programação Multi-Paradigma em Kotlin
image: bytes.jpeg
---

# Sobre o autor

# Abordagem

Este livro digital aborda a programação de software numa perspectiva multi-paradigma, isto é, apresentando conceitos de programação que provêm de diferentes paradigmas clássicos (funcional, programação estruturada, e com objetos), os quais hoje em dia se tendem a misturar nas linguagens modernas. É importante que um programador domine os conceitos essenciais, pois o mais comum hoje em dia é que um sistema seja desenvolvido utilizando várias linguagens, sendo que estas vão evoluindo e simultaneamente surgindo outras novas. Porém, muitos dos conceitos fundamentais são os mesmos, e estão presentes, ainda que de formas ligeiramente diferentes, nas diversas linguagens. É pouco eficaz ilustrar conceitos de programação sem o recurso a uma linguagem concreta. Desta forma, este livro ilustra conceitos de programação com recurso a Kotlin, uma linguagem moderna que incorpora conceitos de vários paradigmas. Contudo, este não é um livro exaustivo sobre Kotlin. O foco está nos conceitos fundamentais de programação que são transponíveis para outras linguagens.


É dado ênfase na explicação de conceitos de programação sua relação com boas práticas de Engenharia de Software. Construir software é muito mais do escrever um programa que o executa num computador. Existem produtos e sistemas de software que perduram décadas e têm que ser mantidos ao longo do tempo, fazendo evoluir as suas funcionalidades de acordo com novos requisitos. O mundo está em constante mudança, e logo, o software, que hoje em dia está presente em quase todos os aspetos das nossas vidas, sofre também pressões para evoluir. Desta forma, o esforço de manutenção de software tende a ultrapassar o esforço do seu desenvolvimento inicial. Com vista a maximizar a eficiência da manutenção de software torna-se necessário aderir a práticas de desenvolvimento que facilitem este processo. Este livro aborda essas práticas ao nível da programação. Práticas relativas a aspetos avançados de arquitetura de software, processos de desenvolvimento, elaboração de requisitos, ou gestão de projetos, saem fora do âmbito deste livro.

# Conteúdos
O livro segue uma progressão que começa com a escrita de expressões isoladas, passando para a definição de funções, posteriormente programação procedimental, e convergindo para a estruturação com objetos. À medida que os conceitos de programação são apresentados, é também explicada a forma como funciona a gestão de memória na execução de um programa, e são abordados alguns algoritmos e estruturas de dados fundamentais. A escrita do livro está desenhada para uma leitura sequencial, onde frequentemente é feita referência a exemplos e conceitos apresentados anteriormente.

<ol>
{% for item in site.data.capitulos.caps %}
{% if item.cap != null %}
   <li><a href="{{ item.url }}">{{ item.cap }}</a></li>
     {% endif %}
{% endfor %}
</ol>

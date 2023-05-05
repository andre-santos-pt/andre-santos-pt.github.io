---
title: "Projeto: Editor de JSON (segunda parte)"
layout: single
---

A segunda parte do projecto consiste em desenvolver um editor gráfico de JSON, que será cliente do modelo da Fase 1. Desta forma, teremos uma aplicação de exemplo de utilização da biblioteca. 

# Funcionalidade

O editor será uma pequena aplicação gráfica ao estilo do vídeo em baixo. Na parte esquerda temos o editor a desenvolver, na parte direita temos a projeção textual do modelo JSON após cada edição (para *debug*). 

A apliação a desenvolver não tem que ter o mesmo aspeto do vídeo, mas sim o mesmo tipo de funcionalidade. Por exemplo, a interação pode ser mais baseada em menus, e os *widgets* podem ser de outro tipo.

<video width="600" controls>
  <source src="josue.mov" type="video/mp4">
Your browser does not support the video tag.
</video>

Não se espera que o editor possibilite todas as formas de edição. O editor deverá conseguir mostrar todo o conteúdo de um JSON, mas em termos de edição apenas se exige que consiga alterar os valores visíveis, adicionar e remover propriedades de objetos JSON, bem como adicionar e remover e elementos nos *arrays* JSON.


# Arquitetura

Deverá ser aplicada uma arquitetura *Model-View-Controller*:
1. O modelo da Fase 1 deverá ser enriquecido por forma aos elementos serem observáveis.

2. O editor consistirá numa vista do modelo que reage a alterações no mesmo.

3. As ações que alteram o estado do JSON deverão estar estruturas em comandos, por forma a permitir a funcionalidade de *undo*.


**Observações:**

1. O editor pode ter um aspeto "tosco". Interfaces gráficas não são o foco da UC, e logo não deverá ser dispendido tempo a aprimorar este aspeto.

2. Não se espera que as atualizações de vista estejam muito otimizadas. Por exemplo, quando um *array* é alterado, é razoável redesenhar todo o seu conteúdo.

3. Pequenos *bugs* visuais de refrescamento de *layout* são aceitáveis.

4. São fornecidas classes base em Java Swing que podem ser adaptadas livremente para o projeto (não sendo obrigatório). A ideia deste código é ilustrar aspetos do Swing necessários e menos óbvios, poupando tempo no arranque do projeto.  ([Helpers.kt](Helpers.kt); o vídeo ilustra a funcionalidade)

<video width="600" controls>
  <source src="demo.mov" type="video/mp4">
Your browser does not support the video tag.
</video>

# Distribuição e entrega

- A parte 1 do projeto deverá estar num repositório Github, simulando que se trata de uma biblioteca disponível para terceiros utilizarem. Desta forma, para além do código fonte, o repositório deverá conter instruções/tutoriais de como a utilizá-la nas várias vertentes (simples, visitors, reflexão/anotações, observadores).

- A parte 2 também deverá estar no mesmo repositório, mas não precisa de ter documentação.



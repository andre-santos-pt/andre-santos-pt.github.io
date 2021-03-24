---
title: Visitante de ficheiros
exer: true
image: visitanteuml.png
---

O objetivo deste exercício é aplicar o padrão de desenho Visitante (*Visitor*) de raiz atuando sobre uma estrutura simples. A estrutura será a representação dos elementos de um sistema de ficheiros (diretórios que contêm ficheiros, formando uma árvore).


### 1. Estrutura de dados

Implementar a estrutura de dados de acordo com o que é apresentado no diagrama.
- *nElements* significa número de elementos e deverá considerar a contagem recursivamente (pelos subdiretórios).
- *depth* refere-se à profundidade na árvore (elemento raiz (*root*) terá profundidade zero)

As propriedades deverão ser calculadas, por forma a que uma alteração na estrutura (pe. acrescentar um ficheiro) resultará em valores diferentes.


### 2. Carregamento da estrutura

Implementar uma função que dado um diretório (*File*) instancia as classes desenvolvidas em (1).


### 3. Funções sobre a estrutura

Escrever duas funções sobre a estrutura para:
- procurar ficheiro com um dado nome
- contar quantos ficheiros existem com uma dada extensão


### 4. Permitir visitantes

A solução recursiva para as duas funções anteriores foi provavelmente semelhante, e seria também para outras funções que tivessem que varrer a estrutura. O objetivo agora é desenvolver a infraestrutura para que objetos "visitantes" possam varrer a estrutura sem que ter que saber *como* o fazer.

a) Definir uma interface para representar os tipos de elementos que podem ser visitados (ficheiros ou diretórios)

b) Definir o procedimento para aceitar visitantes (tipicamente, *accept*).

c) Implementar as funções da questão anterior com recurso a um objeto visitante.

d) Implementar uma impressão da estrutura em árvore, do estilo:


 - dirA
   - f1
   - f2
   - dirB
     - f3
     - f4
   - dirC
     - f5
     - dirD

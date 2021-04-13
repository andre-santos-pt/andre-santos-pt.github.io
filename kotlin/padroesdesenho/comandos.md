---
title: Comandos (padrão de desenho)
image: comandos.png
---

No contexto de sistemas interativos, e frequentemente dada a necessidade de implementar a funcionalidade de *undo*/*redo*, é aplicado um padrão de desenho que consiste em representar ações (comandos) através de objetos.

No coração destas soluções está uma interface para representar um comando abstratamente, sendo os diversos comandos implementados em classes. A interface tem uma operação para representar a execução da ação, e opcionalmente uma operação para desfazer o comando. A execução de comandos é centralizada no sistema, que poderá manter um histórico de execução (pilha de comandos), para seja possível reverter ação.

O sistema apenas conhece a interface, e não os comandos concretos. Esta propriedade facilita a integração de novos comandos (pe. *plugins*), sem alterar o sistema base.

---
title: Metaprogramação
---

Um dos significados de aplicar "meta-" (prefixo de origem Grega) significa *"refletir sobre si"*. Metaprogramação pode ser definida como a programação que manipula o próprio ou outros programas. Os mecanismos para o alcançar são frequentemente referidos como primitivas de **reflexão**, isto quando a manipulação apenas envolve a consulta dos elementos de um programa (e não sua modificação).

Koltin tem as mesmas capacidades de reflexão do que o Java. É permitida a análise dos elementos estruturais de um programa, isto é, dos seus módulos e classes. Desta forma, conseguimos interrogar uma classe sobre os seus membros: atributos, construtores, e operações. Podemos também manipular atributos indiretamente por reflexão, bem como invocar operações.

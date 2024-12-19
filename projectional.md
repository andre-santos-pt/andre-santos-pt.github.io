---
title: Projectional Editors
layout: single
---

> Writing software may not necessarily involve typing characters in a text editor. [Projectional editors](https://martinfowler.com/bliki/ProjectionalEditing.html) are an alternative kind of editor where an in-memory model representation is visualized and manipulated through a dedicated editor (also referred to as [Structure(d) editor](https://en.wikipedia.org/wiki/Structure_editor)).

{% include iconinfo %}

***

{% include topic name="Javardise" title="Javardise" papers="conf/programming/Santos20" github="andre-santos-pt/javardise" %}

A projectional editor for a subset of Java, extensible with basic plugins. 

Uses [JavaParser](https://javaparser.org) as the model representation of Java code. Here's an informal talk at [Strumenta Virtual Meetup](https://www.youtube.com/watch?v=YAtE3qC6C5w) about the editor.

***

{% include topic name="Javardeye" title="Javardeye" papers="conf/programming/Santos21" %}

An early sketchy prototype where eye gaze is integrated with Javardise to control the cursor position, avoiding mouse movements and/or keystrokes.

<iframe width="420" height="315" src="https://www.youtube.com/embed/o0qS2GJ85xk?si=KA2dfVUvA-TJAPVt" frameborder="0" allowfullscreen></iframe>

***

{% include topic name="PescaJ" title="PescaJ" papers="conf/paint/LopesS23" github="Jose-f-Lopes/PescaJ" %}

A projectional editor for Java that provides views that aggregate scattered code/documentation. Built using the widgets of Javardise.

This work was developed in the MSc thesis of [José Lopes](https://www.linkedin.com/in/jose-fau-lopes/){:target="_blank"}.

<iframe width="420" height="315" src="https://www.youtube.com/embed/YLuqTLYFgqw?si=h9xUqczgpYj9owWP" frameborder="0" allowfullscreen></iframe>

***


{% include topic name="Autocorrection" title="Autocorrection" papers="conf/programming/SantosM24" github="andre-santos-pt/javardise" %}

An extension to Javardise with autocorrection features.

This work was developed in the MSc thesis of [Ângelo Mendonça](https://www.linkedin.com/in/ângelo-miguel-de-lima-mendonça-1b657b1b9/){:target="_blank"}.

![Autocorrection](images/autocorrection.png){: style="width: 600px; margin-right: 2em;"}

***


{% include topic name="Jasay" title="Jasay" papers="conf/ide/SantosCB24" %}

An extension to Javardise to support coding with the voice.

This work was developed in the MSc thesis of [Alexandre Cancelinha](https://www.linkedin.com/in/alexandre-cancelinha-9849081a3/){:target="_blank"}.

![Jasay](images/jasay.png){: style="width: 600px; margin-right: 2em;"}
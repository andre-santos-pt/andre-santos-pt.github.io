---
title: Software Maintenance and Evolution
layout: single
---

> A large amount of software development effort relates to its maintenance as systems evolve.
>
> I've worked in approaches related to software maintenance under distinct perspectives: code merging, variability management, and traceability.

{% include iconinfo %}

***

{% include topic name="Jaid" title="Jaid" papers="conf/onward/TelesS23" github="adrts-iscte/Jaid" %}

An approach for code merging using transformations and member identity. The Jaid prototype is a realization of the approach for Java that uses [JavaParser](https://javaparser.org) for representing code as Abstract Syntax Trees. 

This work was developed in the MSc thesis of [André Teles](https://www.linkedin.com/in/andré-teles-4b3973270/).

![Jaid](images/jaid.png){: style="width: 400px; margin-right: 2em;"}

***

{% include topic name="EquinoxPlugins" title="Feature models for plugin-based systems" papers="journals/spe/Santos17" github="andre-santos-pt/eclipse-feature-editor" %}

An approach for managing the variability offered by the plugin-based system of Eclipse ([Equinox/OGSi](https://www.eclipse.org/equinox/){:target="_blank"}) by means of feature models -- these are manipulated as a technical DSL to define valid system configurations.

 ![Plugins as features](images/equinoxplugins.jpg){: style="width: 700px; margin-right: 2em;"}

***

{% include topic name="GUITA" title="GUI-driven code tracing" papers="conf/vl/Santos12,journals/smr/Santos14" github="andre-santos-pt/guita" %}

GUI-driven code tracing helps to locate source code by interacting with the application.

<iframe width="420" height="315" src="https://www.youtube.com/embed/1lmRg2_RoqU?si=hXhWkLKmIbrG5LHu" frameborder="0" allowfullscreen></iframe>

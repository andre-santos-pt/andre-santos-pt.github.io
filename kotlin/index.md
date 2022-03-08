---
title: Programação Multiparadigma em Kotlin
layout: single
---

<div class="index">
<br>
{% for c in site.data.capitulos.caps %}

<section>
<b><a href="{{c.url}}">{{ c.cap }}</a></b>
{% for s in c.secs %}
   <p>&nbsp;&nbsp;<a href="{{c.url}}/{{s.url}}">{{ s.sec }}</a></p>
{% endfor %}
</section>
{% endfor %}
</div>

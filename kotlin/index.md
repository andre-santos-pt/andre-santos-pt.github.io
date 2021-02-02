---
title: Programação Multiparadigma em Kotlin
layout: default
---

<br>
{% for c in site.data.capitulos.caps %}
{% comment %}
  {% for p in site.pages %}
        {% if (p.dir | remove: '/') == c.url %}
          {{p.dir | escape}}
        {% endif %}
      {% endfor %}
{% endcomment %}

<section>
<b><a href="{{c.url}}">{{ c.cap }}</a></b>
{% for s in c.secs %}
   <p>&nbsp;&nbsp;&nbsp;&nbsp;<a href="{{c.url}}/{{s.url}}">{{ s.sec }}</a></p>
{% endfor %}
</section>
{% endfor %}

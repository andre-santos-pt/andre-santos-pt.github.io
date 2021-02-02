---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: default
title: Índice
image: bytes.jpeg
---

{% for item in site.data.capitulos.caps %}
   <a href="{{ item.url }}">{{ item.title }}</a>
{% endfor %}

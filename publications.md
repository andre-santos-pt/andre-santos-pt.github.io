---
title: Publications
layout: single
---

<a href="https://dblp.org/pid/16/268.html" target="_blank">
<img align="right" src="https://upload.wikimedia.org/wikipedia/commons/d/d2/DBLP_Logo_320x120.png">
</a>

<ul>
  {% for pub in site.data.pubs %}
    <a href="{{ pub.info.ee }}" target="_blank">{{ pub.info.title }}</a><br>
    {% assign authors = pub.info.authors.author | map: "text"  | join: ", " | replace: " 0001", "" %}
    <i>{{ authors }}</i><br>
    {{ pub.info.venue }} {{ pub.info.year }}<br><br>

  {% endfor %}
</ul>

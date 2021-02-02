---
title: Notas de História
nav:
  - page: Header One
    permalink: #header-one

  - page: Header Two
    permalink: #header-two
---
<ol>
{% for n in page.nav %}
    <li><a href="{{ n.permalink }}">{{ n.page }}</a></li>
{% endfor %}
</ol>

# baseadas

<section>

testes

</section>

<section>

fdsfds
</section>

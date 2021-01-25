---
title: Valores compostos
---

Nas secções anteriores abordámos a definição de valores simples, isto é, que consistem num único número. Dependendo do problema que estamos a resolver, um valor (número) representa alguma coisa (uma tonalidade, temperatura, dimensão, etc). Porém é frequente que os elementos com que temos que lidar nos problemas sejam definidos como uma composição de valores. Por exemplo, um ponto num espaço bi-dimensional será composto por dois valores (*x* e *y*), ao passo que num espaço tri-dimensional serão três (acresce *z*). Outros exemplos são as cores RGB (*red*, *green*, *blue*) com três valores, dimensões, intervalos...


# Definição de tipo
Nome

<kotlin>
data class Point(val x: Int, val y: Int)
</kotlin>

<kotlin>
val p = Point(0, 0)
</kotlin>

# Coerência e validação de valores compostos



# Valores compostos por valores simples

<kotlin>
data class Percentage(val value: Double) {
    init {
        require(value >= 0.0 && value <= 1.0)
    }
}
</kotlin>


# Conclusão

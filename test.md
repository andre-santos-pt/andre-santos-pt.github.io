---
layout: default
title: Interfaces e Polimorfismo
---

This is a test file, yet another attempet

```java
public interface IStepper extends IVariableRole {
	Direction getDirection();

  default String getName() {
		return "Stepper";
	}

	enum Direction {
		INC, DEC;
	}
}
```
- [x] @mentions, #refs, [links](), **formatting**, and <del>tags</del> supported
- [x] list syntax required (any unordered or ordered list supported)
- [x] this is a complete item
- [ ] this is an incomplete item

* Item 1
* Item 2
  * Item 2a
    * Item 2b

  *saa
*

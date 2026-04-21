# Práctica 3 — Sistema RPG (Herencia, experiencia, nivel y curación)

## Contexto

Se continúa el desarrollo del sistema RPG comenzado en las prácticas anteriores.

En esta práctica se añadirá herencia al sistema de personajes, además de completar varias mecánicas del juego que hasta ahora no estaban terminadas.

Se parte del código ya implementado en las prácticas anteriores. No hay que rehacer el proyecto desde cero.

---

## Objetivo

El objetivo de esta práctica es trabajar con:

- herencia
- especialización de clases
- sobrescritura de métodos
- progresión del personaje
- curación
- eventos aleatorios

---

## Parte 1 — Herencia en el personaje jugador

A partir de la clase `Personaje`, deben crearse al menos tres tipos distintos de personaje jugador.

Ejemplos válidos:

- `Guerrero`
- `Mago`
- `Arquero`

Cada clase hija debe:

- heredar de `Personaje`
- tener un constructor propio
- inicializar al personaje con valores base diferentes
- sobrescribir al menos un método de comportamiento (por ejemplo, que tenga más ataque, más defensa...)

### Requisitos

Cada tipo de personaje debe diferenciarse de los demás de forma real.

Por ejemplo, puede diferenciarse por:

- más vida
- más ataque
- más defensa
- forma distinta de atacar (cambiar el método de atacar, incluír críticos por ejemplo)
- forma distinta de curarse

No basta con cambiar únicamente el nombre.

---

## Parte 2 — Selección del tipo de personaje

En la clase `Juego`, antes de comenzar la partida, el usuario debe poder elegir el tipo de personaje. Esto requerirá también cambiar cómo se almacenan en disco los personajes.

Debe mostrarse un menú inicial similar a este:

1. Guerrero
2. Mago
3. Arquero

Según la opción elegida, deberá crearse un objeto del tipo correspondiente.

---

## Parte 3 — Curación

Debe añadirse al sistema una mecánica de curación.

### Requisitos

La clase `Personaje` debe incluir un método para curarse. Este método tiene limitaciones: un personaje puede curarse después de llevar una racha de 3 combates ganados, y la racha se reinicia cada vez que se cura.

Este método debe:

- aumentar la vida
- no permitir que la vida supere la vida máxima

Cada tipo de personaje puede curarse de forma distinta.

Ejemplos:

- el guerrero puede recuperar poca vida
- el mago puede recuperar más vida
- el arquero puede recuperar una cantidad intermedia

---

## Parte 4 — Experiencia y nivel

Debe completarse el sistema de progresión del personaje.

### Requisitos

1. El personaje debe ganar experiencia al derrotar enemigos.
2. Cuando alcance cierta cantidad de experiencia, debe subir de nivel.
3. Al subir de nivel, deben mejorar sus estadísticas.

### Reglas mínimas

- la experiencia debe acumularse
- el nivel inicial debe ser 1
- subir de nivel debe modificar al menos dos estadísticas

Ejemplo de mejora posible:

- +1 nivel
- +5 ataque
- +10 vida máxima
- restaurar la vida al máximo al subir de nivel

Hay libertad para elegir la forma de subida, pero debe ser coherente y mantenerse constante.

---

## Parte 5 — Descanso con evento aleatorio

Debe modificarse la acción de descansar.

Hasta ahora, descansar solo recuperaba vida. A partir de ahora, descansar puede tener consecuencias.

### Nuevo comportamiento

Cuando el personaje descansa:

- normalmente recupera vida
- pero existe un 25% de probabilidad de que aparezca un enemigo pasado y ataque al jugador

Ese enemigo se genera a partir de uno de los enemigos encontrados anteriormente.

### Reglas de enemigos pasados

Un enemigo pasado aparece en forma de zombie.

- un zombie debe basarse en un enemigo previo aleatorio con el que se haya luchado
- sus estadísticas deben ser las del enemigo original multiplicadas por 1,2
- su nombre será el del enemigo original seguido de "(zombie)"

### Requisito mínimo obligatorio

No es necesario implementar zombies de zombies. 

Esto significa:

- enemigo original
- zombie con estadísticas multiplicadas por 1,2

---

## Parte 6 — Enemigos de generaciones superiores (ampliación)

Si el enemigo original ya era un zombie, el nuevo enemigo que generemos con él será un fantasma. Y si el enemigo era un fantasma, el nuevo enemigo será un demonio.

### Reglas

#### Zombie
- estadísticas multiplicadas por 1,2
- el nombre incluye "zombie"

#### Fantasma
- estadísticas multiplicadas por 1,2
- además, un 10% de probabilidad de esquivar ataques
- el nombre incluye "espectro"

#### Demonios
- estadísticas multiplicadas por 1,2
- además, un 10% de probabilidad de esquivar ataques
- además, un 20% de probabilidad de realizar un impacto crítico
- el nombre incluye "demonio"

### Importante

No es obligatorio implementar generaciones superiores a la primera.

---

## Parte 7 — Enemigos y herencia

Es obligatorio aplicar herencia en la jerarquía de enemigos. Por ejemplo, podemos tener la clase `Fantasma` que herede de `Enemigo` y sobreescriba los métodos necesarios (como getNombre()).

Si se hace, debe aprovecharse realmente la herencia y no limitarse a duplicar código.

---

## Parte 8 — Menú del juego

El juego debe seguir teniendo menú por consola y permitir, al menos:

1. Ver estado
2. Buscar enemigo
3. Descansar
4. Curar
5. Ver inventario
6. Ver habilidades
7. Ver enemigos encontrados
8. Guardar personaje
9. Cargar personaje
0. Salir

---

## Requisitos técnicos

- Debe usarse herencia de forma real
- Debe existir al menos una clase base y varias clases hijas
- Debe haber al menos un método sobrescrito
- No se permite rehacer el proyecto desde cero

---

## Restricciones

- Todavía no se usan interfaces
- No se permite eliminar la lógica de excepciones y ficheros de la práctica anterior, sino que hay que adaptarla
- Es necesario incluir System.out.println() necesarios: por ejemplo, cuando un fantasma esquive un golpe, indicarlo.

---

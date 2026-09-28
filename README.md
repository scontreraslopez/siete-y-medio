# Siete y medio en Kotlin

Solución propuesta para la práctica de la unidad: una versión del juego de cartas **Siete y medio** que se juega por terminal.

> [!NOTE]
> Esta es **una** solución posible, ni la única ni la mejor. 
> Si la vuestra está organizada de otra forma y funciona, perfecto. 
> Lo importante es haber practicado, aunque hayáis tenido que buscar ayuda por el camino. 
> Estas bases son las que os van a permitir entender Android mucho más rápido.

## Cómo ejecutarlo

1. Abre el proyecto en IntelliJ IDEA.
2. Abre `src/main/kotlin/Main.kt`.
3. Pulsa el ▶️ que aparece junto a `fun main()`.

Requisitos: Kotlin 2.x y JDK 25 (ver `build.gradle.kts`).

## Estructura del proyecto

```
src/main/kotlin/
├── Main.kt                   → Punto de entrada. Solo arranca el controlador.
├── model/                    → Las reglas y los datos del juego.
│   ├── Suit.kt               → enum class con los cuatro palos.
│   ├── Card.kt               → data class Carta: número, palo, valor y nombre.
│   └── Deck.kt               → La baraja: 40 cartas barajadas y robar carta.
├── ui/                       → Todo lo que se muestra o se lee por terminal.
│   └── Menu.kt               → Menú principal. Devuelve la opción elegida.
└── controller/
    └── GameController.kt     → Controla el flujo: menú → partida → resultado → menú.
```

## La idea: separar responsabilidades

El programa se organiza en tres capas, siguiendo un **MVC ligero**:

| Capa | Qué hace | Qué **no** hace |
|---|---|---|
| **model** | Sabe cuánto vale una carta, cómo se construye la baraja y si alguien se ha pasado. | No usa `println` ni `readln`. |
| **ui** | Muestra información y lee lo que escribe el usuario. | No decide nada del juego. |
| **controller** | Une las dos anteriores: pide una opción a la UI, llama al modelo y decide qué pantalla viene después. | No contiene reglas del juego. |

## Decisiones de diseño explicadas

### `Suit`: enum con dato asociado

```kotlin
enum class Suit(val symbol: String) {
    OROS("🪙"), COPAS("🍷"), ESPADAS("⚔️"), BASTOS("🪵");
}
```

Cada palo guarda un símbolo para mostrarlo de forma más visual por terminal.
Aunque en el tema usaba emojis, aquí me he decantado por las iniciales de cada palo.
Es un detalle menor.

### `Card`: data class con propiedades calculadas

- `require(...)` en el `init` impide crear cartas imposibles, como un 8 o un 9.
- `value` es una **propiedad calculada**: no se guarda, se calcula a partir de `number` cada vez que se consulta.
- `cardName` devuelve un nombre legible ("Sota de OROS").

Es `data class` porque una carta es un **valor**: dos "5 de Oros" son la misma carta y nunca cambian.

### `Deck`: clase normal (no `data class`) con `init`

```kotlin
class Deck {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        // genera las 40 cartas y las baraja
    }

    fun drawCard(): Card = cards.removeFirstOrNull()
        ?: throw IllegalStateException("No hay más cartas en la baraja")
}
```

- La baraja se construye **completa y barajada** en el `init`. Así, cada vez que escribimos `Deck()` tenemos una baraja lista para jugar.
- La lista es `private`: desde fuera solo se pueden sacar cartas con `drawCard()`. Como la carta se **elimina** de la lista al robarla, no puede volver a salir en la misma partida.
- No es `data class` porque la baraja **cambia** (va perdiendo cartas). Las `data class` están pensadas para datos que no cambian. En Android sí que tenemos que usar `data class` para los datos que se muestran en pantalla, pero no para la baraja. Aquí quedaba artificial y no he querido complicarlo más.
- No es `object` (singleton) porque cada partida necesita una baraja **nueva**.

### La baraja se crea dentro de cada partida

```kotlin
MenuOption.PLAY -> {
    val deck = Deck()
    ...
}
```

La baraja solo existe mientras dura una partida, así que es una variable **local**. Si la declarásemos como `var deck: Deck? = null` en el controlador, tendríamos que arrastrar el `?` en cada uso (`deck?.drawCard() ?: ...`). Los nullables no son gratis: cada `?` que declaras lo pagas en todas las líneas donde usas esa variable.

Lo mismo ocurre con las cartas y la puntuación de cada jugador: si fueran propiedades del controlador, la segunda partida empezaría con las cartas de la primera.

### `MenuOption`: el menú devuelve una opción, no ejecuta nada

```kotlin
enum class MenuOption { PLAY, EXIT }

fun showMainMenu(): MenuOption { ... }
```

El menú solo pregunta y devuelve lo que ha elegido el usuario. Es el controlador quien decide qué hacer con esa opción mediante un `when`. Si el usuario escribe algo que no es válido, el menú vuelve a preguntar sin salir de la función.

## Hoja de ruta

- [x] Palos, cartas y baraja
- [x] Menú principal y bucle del programa
- [ ] Turno del jugador con bucle *pedir carta / plantarse*
- [ ] Detectar automáticamente cuándo un jugador se pasa
- [ ] Mover las reglas (puntuación, ganador) del controlador al modelo
- [ ] Pantalla de resultado con el formato del enunciado

## Tip

Tienes mi historial de commits en este repo, así puedes seguir también mi proceso mental de desarrollo.
No es obligatorio, pero puede ayudarte a ver cómo se construye un programa paso a paso.

## Para ir más allá (opcional)

Si ya lo tienes funcionando y quieres seguir practicando:

- **Crea una clase `Hand` (mano)** con propiedades calculadas `points` e `isBust`. Así el controlador no tiene que sumar puntuaciones a mano.
- **Soporta N jugadores** usando una `List<Player>` en lugar de variables `player1…` y `player2…`.
- **Baraja inmutable:** `data class Deck(val cards: List<Card>)`, donde robar una carta devuelve una baraja nueva con `copy()`. Ojo: `copy()` vuelve a ejecutar el `init`, así que la baraja inicial se crearía con una función en un `companion object` (`Deck.shuffled()`). Es el estilo que os encontraréis más adelante con el estado en Compose.

package iesseveroochoa.edu.gva.es.model

class Deck {

    private val cards: MutableList<Card> = mutableListOf()      // Esto guardará las cartas de la baraja conforme se vayan generando / repartiendo

    // Esto es el constructor que básicamente nos preparará las 40 cartas de la baraja española (sin 8 ni 9) y las mezclará.
    init {
        // Generamos las cartas del 1 al 7
        for (i in 1..7) {
            for (suit in Suit.values()) {
                cards += Card(i, suit)
            }

        }
        // Generamos las figuras del 10 al 12
        for (i in 10..12) {
            for (suit in Suit.values()) {
                cards += Card(i, suit)
            }
        }

        /*
            Nota:
            Se puede escribir más compacto como
            for (suit in Suit.entries) {
              for (number in (1..7) + (10..12)) {
                  cards += Card(number, suit)
              }
          }
         */

        cards.shuffle() // Mezclamos la baraja al final de la inicialización
    }

    val remainingCards: Int
        get() = cards.size

    // De este modo garantizamos que no haya repetidos
    fun drawCard(): Card = cards.removeFirstOrNull() ?: throw IllegalStateException("No hay más cartas en la baraja")
}
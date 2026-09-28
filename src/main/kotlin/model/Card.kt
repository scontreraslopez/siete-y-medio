package iesseveroochoa.edu.gva.es.model

data class Card(
    val number: Int,
    val suit: Suit
) {
    // Forzamos que el número de la carta esté entre 1 y 7 o entre 10 y 12.
    // Consideramos la baraja classic sin 8 ni 9.
    init {
        require(number in 1..7 || number in 10..12) { "El número de la carta debe estar entre 1 y 7 o entre 10 y 12" }
    }

    // Propiedad calculada para imprimir el nombre chulo
    val cardName: String
        get() = when
    (number) {
                1 -> "As de ${suit.name}"
                2 -> "Dos de ${suit.name}"
                3 -> "Tres de ${suit.name}"
                4 -> "Cuatro de ${suit.name}"
                5 -> "Cinco de ${suit.name}"
                6 -> "Seis de ${suit.name}"
                7 -> "Siete de ${suit.name}"
                10 -> "Sota de ${suit.name}"
                11 -> "Caballo de ${suit.name}"
                12 -> "Rey de ${suit.name}"
                else -> throw IllegalArgumentException("Número de carta inválido")
    }

    // Propiedad calculada para obtener el valor de la carta en el juego
    val value: Double
        get() = when {
            number in 1..7 -> number.toDouble() // As, Dos, Tres, Cuatro, Cinco, Seis, Siete
            else -> 0.5
        }
}


// Nota: En Kotlin, a diferencia de Java, Suit podía haberlo metido también en este fichero y no habría problema,
// pero lo he separado para que quede más claro y limpio.





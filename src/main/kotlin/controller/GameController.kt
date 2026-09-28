package iesseveroochoa.edu.gva.es.controller

import iesseveroochoa.edu.gva.es.model.Card
import iesseveroochoa.edu.gva.es.model.Deck
import iesseveroochoa.edu.gva.es.ui.MenuOption
import iesseveroochoa.edu.gva.es.ui.showMainMenu

// Lo hago un objeto singleton porque en este punto no necesitamos tener múltiples instancias de GameController.
// Solo queremos usar su método run() para iniciar el juego.

object GameController {

    fun run() {
        while (true) {
            when (showMainMenu()) {
                MenuOption.PLAY -> {
                    // Aquí iría la lógica para iniciar el juego
                    println("Iniciando el juego...")

                    // Guarrería que habría que mover luego
                    val deck = Deck()// Preparamos la baraja, pero no la inicializamos todavía. Esto nos permitirá reiniciar el juego más tarde si queremos.
                    var player1Score: Double = 0.0  // Usamos esto para llevar la puntuación de cada jugador
                    var player1Cards = mutableListOf<Card>() // No tengo claro si esto va a ser necesario, pero por si acaso lo voy a poner de momento.
                    // Serviría para mostrar las cartas que se le han repartido por las dudas.
                    var player2Score: Double = 0.0
                    var player2Cards = mutableListOf<Card>()


                    // Pongamos en borrador la lógica aquí.
                    // Turno del jugador1, se le reparte una carta, se le pregunta si quiere otra, etc.
                    // Sí se ha pasado el sistema de juego le dirá que ha perdido y se acabará su turno
                    // Cuando se termine el turno del jugador1 empieza el turno del jugador2,
                    // Ídem anterior
                    // Se mira quien ha ganado, o si empata

                    // Vamos al lio...
                    println("Turno del Jugador 1")
                    player1Cards.add(deck.drawCard())
                    player1Score += player1Cards.last().value // la ultima carta

                    // Linea debug
                    println("Jugador 1 ha recibido: ${player1Cards.last().cardName} y su puntuación es: $player1Score")


                    println("Turno del Jugador 2")
                    player2Cards.add(deck.drawCard())
                    player2Score += player2Cards.last().value // la ultima carta
                    println("Jugador 2 ha recibido: ${player2Cards.last().cardName} y su puntuación es: $player2Score")

                    // Otra guarrería que habría que mover luego
                    when {
                        player1Score > 7.5 && player2Score > 7.5 -> println("Ambos jugadores se han pasado de 7.5. Empate.")
                        player1Score > 7.5 -> println("Jugador 1 se ha pasado de 7.5. Jugador 2 gana.")
                        player2Score > 7.5 -> println("Jugador 2 se ha pasado de 7.5. Jugador 1 gana.")
                        player1Score == player2Score -> println("Empate entre Jugador 1 y Jugador 2 con puntuación de $player1Score")
                        player1Score > player2Score -> println("Jugador 1 gana con puntuación de $player1Score frente a $player2Score")
                        else -> println("Jugador 2 gana con puntuación de $player2Score frente a $player1Score")
                    }



                }
                MenuOption.EXIT -> {
                    println("Saliendo del juego. ¡Hasta luego!")
                    return // Sale del bucle y termina la ejecución del programa
                }
            }
        }
    }


}
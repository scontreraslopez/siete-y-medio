package iesseveroochoa.edu.gva.es.controller

import iesseveroochoa.edu.gva.es.model.Deck
import iesseveroochoa.edu.gva.es.ui.MenuOption
import iesseveroochoa.edu.gva.es.ui.showMainMenu

// Lo hago un objeto singleton porque en este punto no necesitamos tener múltiples instancias de GameController.
// Solo queremos usar su método run() para iniciar el juego.

object GameController {

    var deck: Deck? = null // Preparamos la baraja, pero no la inicializamos todavía. Esto nos permitirá reiniciar el juego más tarde si queremos.

    fun run() {
        while (true) {
            when (showMainMenu()) {
                MenuOption.PLAY -> {
                    // Aquí iría la lógica para iniciar el juego
                    println("Iniciando el juego...")
                    deck = Deck() // Nueva partida, así que creamos una nueva baraja

                    // Pongamos en borrador la lógica aquí.
                    // Turno del jugador1, se le reparte una carta, se le pregunta si quiere otra, etc.
                    // Sí se ha pasado el sistema de juego le dirá que ha perdido y se acabará su turno
                    // Cuando se termine el turno del jugador1 empieza el turno del jugador2,
                    // Ídem anterior
                    // Se mira quien ha ganado, o si empata

                }
                MenuOption.EXIT -> {
                    println("Saliendo del juego. ¡Hasta luego!")
                    return // Sale del bucle y termina la ejecución del programa
                }
            }
        }
    }


}
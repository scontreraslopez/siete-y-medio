package iesseveroochoa.edu.gva.es.controller

import iesseveroochoa.edu.gva.es.model.Card
import iesseveroochoa.edu.gva.es.ui.MenuOption
import iesseveroochoa.edu.gva.es.ui.showMainMenu

// Lo hago un objeto singleton porque en este punto no necesitamos tener múltiples instancias de GameController.
// Solo queremos usar su método run() para iniciar el juego.

object GameController {

    val deck = mutableListOf<Card>() //Preparamos ya la baraja para el juego, aunque aún no la llenamos con cartas.


    fun run() {
        while (true) {
            when (showMainMenu()) {
                MenuOption.PLAY -> {
                    // Aquí iría la lógica para iniciar el juego
                    println("Iniciando el juego...")
                }
                MenuOption.EXIT -> {
                    println("Saliendo del juego. ¡Hasta luego!")
                    return // Sale del bucle y termina la ejecución del programa
                }
            }
        }
    }


}
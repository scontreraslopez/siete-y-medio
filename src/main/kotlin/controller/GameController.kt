package iesseveroochoa.edu.gva.es.controller

import iesseveroochoa.edu.gva.es.ui.MenuOption
import iesseveroochoa.edu.gva.es.ui.showMainMenu

class GameController {
    fun run() {
        while (true) {
            when (showMainMenu()) {
                MenuOption.PLAY -> {
                    // Aquí iría la lógica para iniciar el juego
                    println("Iniciando el juego...")
                }
                MenuOption.EXIT -> {
                    println("Saliendo del juego. ¡Hasta luego!")
                    return

                }
            }
        }
    }
}
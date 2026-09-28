package iesseveroochoa.edu.gva.es.ui

enum class MenuOption {
    PLAY,
    EXIT
}

fun showMainMenu(): MenuOption {
    println("===================================")
    println("         SIETE Y MEDIO          ")
    println("===================================")
    println("Bienvenido jugador")
    println("1. Jugar")
    println("2. Salir")
    while (true) {
        print("Elige una opción: ")
        when (readln().trim()) {
            "1" -> return MenuOption.PLAY
            "2" -> return MenuOption.EXIT
            else -> println("Opción inválida. Por favor, elige 1 (Jugar) o 2 (Salir).")
        }
    }
}



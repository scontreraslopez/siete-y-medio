package iesseveroochoa.edu.gva.es

import iesseveroochoa.edu.gva.es.model.Suit

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val myBasto = Suit.BASTOS
    println(myBasto)
    println(myBasto.name)
    println(myBasto.symbol)

    when (myBasto) {
        Suit.BASTOS -> println("Es un basto")
        Suit.COPAS -> println("Es una copa")
        Suit.OROS -> println("Es un oro")
        Suit.ESPADAS -> println("Es una espada")
    }


}
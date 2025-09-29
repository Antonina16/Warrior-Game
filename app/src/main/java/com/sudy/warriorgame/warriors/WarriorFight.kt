package com.sudy.warriorgame.warriors

import com.sudy.warriorgame.warriors.interfaces.Warrior
import com.sudy.warriorgame.warriors.interfaces.isAlive
import com.sudy.warriorgame.warriors.interfaces.isNotAlive


fun fight(first: Warrior, second: Warrior): Boolean {
    require(first.isAlive and second.isAlive) { "Warriors should be alive" }
    while (first.isAlive) {
        first hits second
        if (second.isNotAlive) break
        second hits first
    }
    return first.isAlive
}

fun fight(army1: Army, army2: Army): Boolean {
    val it1 = army1.firstAliveIterator()
    val it2 = army2.firstAliveIterator()
    require(it1.hasNext() and it2.hasNext())
    { "Both armies should be alive" }

    while (it1.hasNext() and it2.hasNext()) {
        fight(it1.next(), it2.next())
    }
    return it1.hasNext()
}

fun straightFight(army1: Army, army2: Army): Boolean {
//    var round = 1
    do {
//        println("Round ${round} started")
//        println("army1 = ${army1.toStringAlive()}")
//        println("army2 = ${army2.toStringAlive()}")
        (army1.warriors zip army2.warriors).forEach { (warrior1, warrior2) ->
            fight(warrior1, warrior2)
        }
//        println("Round ${round++} ended")
//        println("army1 = $army1")
//        println("army2 = $army2")
    } while (army1.isAlive && army2.isAlive)
    return army1.isAlive
}


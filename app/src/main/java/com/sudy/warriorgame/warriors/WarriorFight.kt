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



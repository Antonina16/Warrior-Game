package com.sudy.warriorgame.warriors.interfaces


interface HasAttack {
    val attack: Int
    infix fun hits(other: HasHealth): Int {
        val healthBefore = other.health
        other.takeDamage(attack)
        val healthAfter = other.health
        return healthBefore - healthAfter
    }
}
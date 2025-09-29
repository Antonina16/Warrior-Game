package com.sudy.warriorgame.warriors.interfaces

interface HasHealth {
    val health: Int
    fun takeDamage(damage: Int)
    fun selfHeal(points: Int)
}
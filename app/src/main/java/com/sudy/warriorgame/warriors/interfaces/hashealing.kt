package com.sudy.warriorgame.warriors.interfaces


interface HasHealing {
    val healing: Int
    fun heal(other: HasHealth)

}
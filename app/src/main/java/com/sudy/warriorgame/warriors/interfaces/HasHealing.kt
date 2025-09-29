package com.sudy.warriorgame.warriors.interfaces


interface HasHealing {
    val healPower: Int
    fun heal(other: HasHealth)

}
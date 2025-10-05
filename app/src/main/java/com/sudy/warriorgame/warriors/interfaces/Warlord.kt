package com.sudy.warriorgame.warriors.interfaces

import com.sudy.warriorgame.warriors.WarlordImpl



interface Warlord : Warrior {
    fun moveUnits(units: List<Warrior>): List<Warrior>
    companion object
}

operator fun Warlord.Companion.invoke() = WarlordImpl()


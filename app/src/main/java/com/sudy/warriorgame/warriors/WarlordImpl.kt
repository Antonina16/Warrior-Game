package com.sudy.warriorgame.warriors

import com.sudy.warriorgame.warriors.configs.Props
import com.sudy.warriorgame.warriors.interfaces.ExtraProps
import com.sudy.warriorgame.warriors.interfaces.HasDefense
import com.sudy.warriorgame.warriors.interfaces.Warlord
import com.sudy.warriorgame.warriors.interfaces.Warrior


class WarlordImpl :  WarriorBase(health = Props.Warlord.HEALTH), HasDefense, Warlord {
    override val attack: Int
    get() = Props.Warlord.ATTACK + super.attack

    override val defense: Int
    get() = Props.Warlord.DEFENSE +
            extraModifiers[ExtraProps.DEFENSE]!!

    override fun moveUnits(units: List<Warrior>): List<Warrior> {
        TODO("Not yet implemented")
    }

}
package com.sudy.warriorgame.warriors

import com.sudy.warriorgame.warriors.configs.Props
import com.sudy.warriorgame.warriors.interfaces.ExtraProps
import com.sudy.warriorgame.warriors.interfaces.HasHealing
import com.sudy.warriorgame.warriors.interfaces.HasHealth


class Healer : WarriorBase(Props.Healer.HEALTH), HasHealing {
    override val healPower: Int
        get() = Props.Healer.HEAL +
                extraModifiers[ExtraProps.HEAL_POWER]!!

    override fun heal(other: HasHealth) {
        other.selfHeal(healPower)
    }

    override val attack: Int
        get() = 0
}
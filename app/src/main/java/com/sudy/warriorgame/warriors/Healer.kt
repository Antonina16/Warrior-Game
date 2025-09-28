package com.sudy.warriorgame.warriors

import com.sudy.warriorgame.warriors.configs.Props
import com.sudy.warriorgame.warriors.interfaces.HasHealing
import com.sudy.warriorgame.warriors.interfaces.HasHealth


class Healer : WarriorBase(health = Props.Healer.HEALTH), HasHealing {
    override val attack: Int
        get() = Props.Healer.ATTACK

    override val healing: Int
        get() = Props.Healer.HEAL


    override fun heal(other: HasHealth) {
        other.selfHeal(healing)
    }
}
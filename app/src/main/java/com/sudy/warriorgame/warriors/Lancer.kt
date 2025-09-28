package com.sudy.warriorgame.warriors

import com.sudy.warriorgame.warriors.configs.Props
import com.sudy.warriorgame.warriors.interfaces.HasAttack
import com.sudy.warriorgame.warriors.interfaces.HasHealth
import com.sudy.warriorgame.warriors.interfaces.WarriorInArmy


class Lancer : WarriorBase(health = Props.Lancer.HEALTH) {
    override val attack: Int
        get() = Props.Lancer.ATTACK

    override fun hits(other: HasHealth): Int {
        var dealtDamage = super.hits(other)
        val piercing = 50
        val damageToNext = dealtDamage * piercing / 100
        (other as? WarriorInArmy)?.nextBehind?.let {
            val proxy = object : HasAttack {
                override val attack: Int
                    get() = damageToNext
            }
            dealtDamage += proxy hits it
        }
        return dealtDamage
    }
}
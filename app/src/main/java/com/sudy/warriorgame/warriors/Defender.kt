package com.sudy.warriorgame.warriors
import com.sudy.warriorgame.warriors.configs.Props
import com.sudy.warriorgame.warriors.interfaces.ExtraProps
import com.sudy.warriorgame.warriors.interfaces.HasDefense


class Defender() : WarriorBase(health = Props.Defender.HEALTH), HasDefense {
    override val attack: Int
        get() = Props.Defender.ATTACK + super.attack

    override val defense: Int
        get() = Props.Defender.DEFENSE +
                extraModifiers[ExtraProps.DEFENSE]!!

    override fun takeDamage(damage: Int) {
        val reduceDamage = (damage-defense).coerceAtLeast(0)
        super.takeDamage(reduceDamage)
    }

}
package com.sudy.warriorgame.warriors

import com.sudy.warriorgame.warriors.configs.Props
import com.sudy.warriorgame.warriors.interfaces.Warrior


abstract class WarriorBase(
    health: Int
) : Warrior {
    private var _health: Int = health
    private val initialHealth = health
    override val health: Int
        get() = _health

    override val attack: Int
        get() = Props.Warrior.ATTACK

    override fun selfHeal(points: Int) {
        require(points > 0) { "Self healing is possible only for positive values" }
        _health = (_health + points).coerceAtMost(initialHealth)
    }

    override fun takeDamage(damage: Int) {
        require(damage >= 0) { "Damage must be nonnegative" }
        _health -= damage
    }

    override fun toString(): String {
        val name = javaClass.simpleName.replace("Impl", "")
        return "$name(health=$health, attack=$attack)"
    }

}
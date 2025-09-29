package com.sudy.warriorgame.warriors

import com.sudy.warriorgame.warriors.interfaces.CompoundWeaponImpl
import com.sudy.warriorgame.warriors.interfaces.ExtraProps
import com.sudy.warriorgame.warriors.interfaces.Warrior
import com.sudy.warriorgame.warriors.interfaces.Weapon


abstract class WarriorBase(
    health: Int
) : Warrior {
    private var _health: Int = health
    private val initialHealth = health
        get() = field + _weapon.health
    private val _weapon: CompoundWeaponImpl = CompoundWeaponImpl()

    override fun equipWeapon(weapon: Weapon) {
        _weapon.add(weapon)
    }

    override val health: Int
        get() = _health + _weapon.health

    override val attack: Int
        get() = _weapon.attack

    protected val extraModifiers: Map<ExtraProps, Int>
        get() = object : Map<ExtraProps, Int> by _weapon.extraModifiers {
            override operator fun get(key: ExtraProps): Int = _weapon.extraModifiers[key] ?: 0
        }

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


package com.sudy.warriorgame.warriors.interfaces

import com.sudy.warriorgame.warriors.WarriorBase
import com.sudy.warriorgame.warriors.configs.Props



interface Warrior : HasAttack, HasHealth, WeaponHolder {
    override fun toString(): String
    companion object
}

operator fun Warrior.Companion.invoke(): Warrior = WarriorImpl()


open class WarriorImpl() : WarriorBase(
    Props.Warrior.HEALTH
) {
    override val attack: Int
        get() = Props.Warrior.ATTACK + super.attack

}

inline val Warrior.isAlive: Boolean
    get() = health > 0

inline val Warrior.isNotAlive: Boolean
    get() = !isAlive

val Warrior.type: String
    get() = this::class.simpleName.toString().replace("Impl", "")
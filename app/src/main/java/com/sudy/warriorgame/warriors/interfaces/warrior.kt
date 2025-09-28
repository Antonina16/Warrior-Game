package com.sudy.warriorgame.warriors.interfaces

import com.sudy.warriorgame.warriors.WarriorBase
import com.sudy.warriorgame.warriors.configs.Props


interface Warrior : HasHealth, HasAttack{
    companion object
}

operator fun Warrior.Companion.invoke(): Warrior = WarriorImpl()


open class WarriorImpl() : WarriorBase(
    Props.Warrior.HEALTH
) {
    override val attack: Int
        get() = Props.Warrior.ATTACK

}

inline val Warrior.isAlive: Boolean
    get() = health > 0

inline val Warrior.isNotAlive: Boolean
    get() = !isAlive
package com.sudy.warriorgame.warriors

import android.os.Build
import androidx.annotation.RequiresApi
import com.sudy.warriorgame.warriors.interfaces.Command
import com.sudy.warriorgame.warriors.interfaces.HasHealing
import com.sudy.warriorgame.warriors.interfaces.HasHealth
import com.sudy.warriorgame.warriors.interfaces.Warlord
import com.sudy.warriorgame.warriors.interfaces.Warrior
import com.sudy.warriorgame.warriors.interfaces.WarriorInArmy
import com.sudy.warriorgame.warriors.interfaces.Weapon
import com.sudy.warriorgame.warriors.interfaces.isAlive
import com.sudy.warriorgame.warriors.interfaces.isNotAlive


class Army {
    private val units = mutableListOf<WarriorInArmyImpl>()
    private var warlord: Warlord? = null

    val size: Int
        get() = units.count { it.isAlive }

    fun moveUnits() {
        val newOrder = warlord?.moveUnits(units.map { it.warrior })
            ?: return
        units.clear()
        warlord = null
        newOrder.forEach {
            addUnits(1) { it }
        }
    }

    operator fun get(ix: Int) = when {
        ix < 0 -> units[units.size + ix]
        else -> units[ix]
    }.warrior


    private class WarriorInArmyImpl(
        val warrior: Warrior
    ) : WarriorInArmy, Warrior by warrior {
        var _nextBehind: Warrior? = null
        override val nextBehind: Warrior?
            get() = _nextBehind

        override fun hits(other: HasHealth): Int =
            warrior.hits(other).also {
                process(Command.ChampionHits(origin = this))
            }

        override fun process(command: Command) {
            when (command) {
                is Command.ChampionHits -> {
                    if (command.origin != this && warrior is HasHealing) {
                        warrior.heal(command.origin)
                    }
                    super.process(Command.ChampionHits(origin = this))
                }
            }
        }
    }

    fun equipWarriorAtPosition(ix: Int, weapon: Weapon): Army =
        units[ix].warrior.equipWeapon(weapon).let { this }

    fun addUnits(count: Int, factory: () -> Warrior) {
        repeat(count) {
            val warrior = factory()
            if (warrior is Warlord) {
                if (warlord == null) {
                    warlord = warrior
                } else {
                    return
                }
            }
            val newWarrior = WarriorInArmyImpl(warrior)
            units.lastOrNull()?.let {
                it._nextBehind = newWarrior
            }
            units.add(newWarrior)
        }
    }

    val warriors
        get() = units
            .asSequence()
            .map { it.warrior }
            .filter { it.isAlive }

    val isAlive: Boolean
        get() = units.any { it.isAlive }

    fun firstAliveIterator(): Iterator<Warrior> =
        FirstAliveIterator()

    private inner class FirstAliveIterator : Iterator<Warrior> {
        @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
        override fun hasNext(): Boolean {
            while (units.isNotEmpty() && units.first().isNotAlive) {
                units.removeFirst()
            }
            return units.isNotEmpty()
        }

        @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
        override fun next(): Warrior {
            check(hasNext()) { "No alive warriors" }
            return units.first()
        }
    }

    override fun toString(): String {
        return units.joinToString(
            prefix = "[", postfix = "]"
        ) { it.toString() }
    }

    fun toStringAlive(): String {
        return units
            .filter { it.isAlive }
            .joinToString(
                prefix = "[", postfix = "]"
            ) { it.toString() }
    }
}
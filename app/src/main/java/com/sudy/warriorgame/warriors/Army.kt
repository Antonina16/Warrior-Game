package com.sudy.warriorgame.warriors

import android.os.Build
import androidx.annotation.RequiresApi
import com.sudy.warriorgame.warriors.interfaces.Command
import com.sudy.warriorgame.warriors.interfaces.HasHealing
import com.sudy.warriorgame.warriors.interfaces.HasHealth
import com.sudy.warriorgame.warriors.interfaces.Warrior
import com.sudy.warriorgame.warriors.interfaces.WarriorInArmy
import com.sudy.warriorgame.warriors.interfaces.isNotAlive


class Army {
    private val units = mutableListOf<WarriorInArmyImpl>()

    private class WarriorInArmyImpl(val warrior: Warrior) : WarriorInArmy, Warrior by warrior {
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
                    super.process(Command.ChampionHits(this))
                }
            }
        }
    }


    fun addUnits(count: Int, factory: () -> Warrior) {
        repeat(count) {
            val newWarrior = WarriorInArmyImpl(factory())
            units.lastOrNull()?.let {
                it._nextBehind = newWarrior
            }
            units.add(newWarrior)
        }
    }

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

    fun firstAliveIterator(): Iterator<Warrior> = FirstAliveIterator()

}

inline val Army.isAlive: Boolean
    get() = firstAliveIterator().hasNext()


fun fight(army1: Army, army2: Army): Boolean {
    val firstItr = army1.firstAliveIterator()
    val secondItr = army2.firstAliveIterator()
    require(firstItr.hasNext() and secondItr.hasNext())
    { "Both army should be alive" }
    while (firstItr.hasNext() and secondItr.hasNext()) {
        com.sudy.warriorgame.warriors.fight(firstItr.next(), secondItr.next())
    }
    return firstItr.hasNext()
}

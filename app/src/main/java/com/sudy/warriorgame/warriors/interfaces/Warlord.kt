package com.sudy.warriorgame.warriors.interfaces

import android.os.Build
import androidx.annotation.RequiresApi
import com.sudy.warriorgame.warriors.Healer
import com.sudy.warriorgame.warriors.Lancer
import com.sudy.warriorgame.warriors.WarriorBase
import com.sudy.warriorgame.warriors.configs.Props


interface Warlord : Warrior {
    fun moveUnits(units: List<Warrior>): List<Warrior>
    companion object
}

operator fun Warlord.Companion.invoke() = WarlordImpl()

class WarlordImpl : WarriorBase(
    health = Props.Warlord.HEALTH
), HasDefense, Warlord {
    override val attack: Int
        get() = Props.Defender.ATTACK + super.attack

    override val defense: Int
        get() = Props.Defender.DEFENSE +
                extraModifiers[ExtraProps.DEFENSE]!!

    override fun takeDamage(damage: Int) {
        val reducedDamage = (damage - defense).coerceAtLeast(0)
        super.takeDamage(reducedDamage)
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun moveUnits(units: List<Warrior>): List<Warrior> {
        val reordered = mutableListOf<Warrior>()

        fun isOtherCombatUnit(it: Warrior) =
            it !is Lancer && it !is Healer && it !is Warlord

        val lancers = units.filter { it is Lancer }.toMutableList()
        val healers = units.filter { it is Healer }
        val others = units.filter(::isOtherCombatUnit).toMutableList()

        if (lancers.isNotEmpty()) {
            reordered.add(lancers.removeFirst())
        } else if (others.isNotEmpty()) {
            reordered.add(others.removeFirst())
        }
        reordered.addAll(healers)
        reordered.addAll(lancers)
        reordered.addAll(others)
        reordered.add(this)
        return reordered
    }
}
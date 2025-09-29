package com.sudy.warriorgame.warriors.interfaces

import com.sudy.warriorgame.warriors.interfaces.ExtraProps.*

enum class ExtraProps {
    DEFENSE, VAMPIRISM, HEAL_POWER
}

interface Weapon {
    val health: Int
    val attack: Int
    val extraModifiers: Map<ExtraProps, Int>

    companion object
}

interface CompoundWeapon : Weapon {
    fun add(weapon: Weapon)
}

class CompoundWeaponImpl : CompoundWeapon {
    private val weapons: MutableList<Weapon> = mutableListOf()

    override fun add(weapon: Weapon) {
        weapons.add(weapon)
    }

    override val health: Int
        get() = weapons.sumOf { it.health }

    override val attack: Int
        get() = weapons.sumOf { it.attack }

    override val extraModifiers: Map<ExtraProps, Int>
        get() = weapons.flatMap { it.extraModifiers.entries }
            .groupingBy { it.key }
            .aggregate { _, accumulator: Int?, element, _ ->
                (accumulator ?: 0) + element.value
            }
}

operator fun Weapon.Companion.invoke(
    health: Int, attack: Int,
    defense: Int = 0,
    vampirism: Int = 0,
    healPower: Int = 0
) = WeaponImpl(health, attack, defense, vampirism, healPower)

class WeaponImpl(
    override val health: Int,
    override val attack: Int,
    defense: Int = 0,
    vampirism: Int = 0,
    healPower: Int = 0
) : Weapon {
    override val extraModifiers: Map<ExtraProps, Int> =
        mutableMapOf<ExtraProps, Int>().apply {
            if (defense != 0) put(DEFENSE, defense)
            if (vampirism != 0) put(VAMPIRISM, vampirism)
            if (healPower != 0) put(HEAL_POWER, healPower)
        }
}

object Sword : Weapon {
    override val health: Int
        get() = 5
    override val attack: Int
        get() = 2
    override val extraModifiers: Map<ExtraProps, Int> =
        mapOf()
}

object Shield : Weapon {
    override val health: Int
        get() = 20
    override val attack: Int
        get() = -1
    override val extraModifiers: Map<ExtraProps, Int> =
        mapOf(DEFENSE to 2)
}

object GreatAxe : Weapon {
    override val health: Int
        get() = -15
    override val attack: Int
        get() = 5
    override val extraModifiers: Map<ExtraProps, Int> =
        mapOf(DEFENSE to -2,
            VAMPIRISM to 10)
}

object Katana : Weapon {
    override val health: Int
        get() = -20
    override val attack: Int
        get() = 6
    override val extraModifiers: Map<ExtraProps, Int> =
        mapOf(DEFENSE to -5,
            VAMPIRISM to 50)
}

object MagicWand : Weapon {
    override val health: Int
        get() = 30
    override val attack: Int
        get() = 3
    override val extraModifiers: Map<ExtraProps, Int> =
        mapOf(HEAL_POWER to 3)
}
package com.sudy.warriorgame.warriors.configs

import com.sudy.warriorgame.components.UnitType

typealias Cartage = List<UnitType>

const val DIM = 2

val cartage = listOf(
    UnitType.Warrior, UnitType.Vampire,
    UnitType.Defender, UnitType.Healer, UnitType.Lancer, UnitType.Rookie,
    UnitType.Knight,
)
object Props {
    object Warrior {
        const val HEALTH = 50
        const val ATTACK = 5
    }

    object Knight {
        const val HEALTH = 50
        const val ATTACK = 7
    }

    object Rookie {
        const val HEALTH = 50
        const val ATTACK = 1
    }
    object Lancer {
        const val HEALTH = 50
        const val ATTACK = 6
    }

    object Vampire {
        const val HEALTH = 40
        const val ATTACK = 4
        const val VAMPIRISM = 50
    }

    object Defender {
        const val HEALTH = 60
        const val ATTACK = 3
        const val DEFENSE = 2
    }
    object Healer {
        const val HEALTH = 60
        const val ATTACK = 0
        const val HEAL = 2
    }
    object Warlord {
        const val HEALTH = 100
        const val ATTACK = 3
        const val DEFENSE = 2
    }
}
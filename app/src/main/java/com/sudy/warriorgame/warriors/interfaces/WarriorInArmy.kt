package com.sudy.warriorgame.warriors.interfaces


sealed interface Command {
    class ChampionHits(val origin: WarriorInArmy) : Command
}

interface WarriorInArmy : Warrior {
    val nextBehind: Warrior?
    fun process(command: Command) {
        (nextBehind as? WarriorInArmy)?.process(command)
    }
}

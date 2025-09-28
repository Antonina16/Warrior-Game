package com.sudy.warriorgame.warriors

import com.sudy.warriorgame.warriors.configs.Props


class Rookie : WarriorBase(health = Props.Rookie.HEALTH) {
    override val attack: Int
        get() = 1
}

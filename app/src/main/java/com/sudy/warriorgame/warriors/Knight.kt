package com.sudy.warriorgame.warriors

import com.sudy.warriorgame.warriors.configs.Props


class Knight() : WarriorBase(health = Props.Knight.HEALTH) {
    override val attack: Int
        get() = Props.Knight.ATTACK + super.attack
}
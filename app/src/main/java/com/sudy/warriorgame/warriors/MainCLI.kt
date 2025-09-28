package com.sudy.warriorgame.warriors

import Vampire
import com.sudy.warriorgame.warriors.interfaces.isAlive
import com.sudy.warriorgame.warriors.interfaces.*


fun main() {
    // smoke test
    var chuck = Warrior()
    var bruce = Warrior()
    var carl = Knight()
    var dave = Warrior()
    var mark = Warrior()
    var bob = Defender()
    var mike = Knight()
    var rog = Warrior()
    var lancelot = Defender()
    var eric = Vampire()
    var adam = Vampire()
    var richard = Defender()
    var ogre = Warrior()
    var freelancer = Lancer()
    var vampire = Vampire()
    var priest = Healer()

    check(fight(chuck, bruce) == true)
    check(fight(dave, carl) == false)
    check(chuck.isAlive == true)
    check(bruce.isAlive == false)
    check(carl.isAlive == true)
    check(dave.isAlive == false)
    check(fight(carl, mark) == false)
    check(carl.isAlive == false)
    check(fight(bob, mike) == false)
    check(fight(lancelot, rog) == true)
    check(fight(eric, richard) == false)
    check(fight(ogre, adam) == true)
    check(fight(freelancer, vampire) == true)
    check(freelancer.isAlive == true)
    check(freelancer.health == 14)
    priest.heal(freelancer)
    check(freelancer.health == 16)

    var my_army = Army().apply {
        addUnits(2) { Defender() }
        addUnits(1) { Healer() }
        addUnits(2) { Vampire() }
        addUnits(2) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(1) { Warrior() }
    }

    var enemy_army = Army().apply {
        addUnits(2) { Warrior() }
        addUnits(4) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(2) { Defender() }
        addUnits(3) { Vampire() }
        addUnits(1) { Healer() }
    }
    var army_3 = Army().apply {
        addUnits(1) { Warrior() }
        addUnits(1) { Lancer() }
        addUnits(1) { Healer() }
        addUnits(2) { Defender() }
    }

    var army_4 = Army().apply {
        addUnits(3) { Vampire() }
        addUnits(1) { Warrior() }
        addUnits(1) { Healer() }
        addUnits(2) { Lancer() }
    }

    check(fight(my_army, enemy_army) == false)
    check(fight(army_3, army_4) == true)
    println("OK")
}


fun mainLancer() {
    val chuck = Warrior();
    val bruce = Warrior();
    val carl = Knight();
    val dave = Warrior();
    val mark = Warrior();
    val bob = Defender();
    val mike = Knight();
    val rog = Warrior();
    val lancelot = Defender();
    val eric = Vampire();
    val adam = Vampire();
    val richard = Defender();
    val ogre = Warrior();
    val freelancer = Lancer();
    val vampire = Vampire();

    check(fight(chuck, bruce) == true);
    check(fight(dave, carl) == false);
    check(chuck.isAlive == true);
    check(bruce.isAlive == false);
    check(carl.isAlive == true);
    check(dave.isAlive == false);
    check(com.sudy.warriorgame.warriors.fight(carl, mark) == false);
    check(carl.isAlive == false);
    check(com.sudy.warriorgame.warriors.fight(bob, mike) == false);
    check(com.sudy.warriorgame.warriors.fight(lancelot, rog) == true);
    check(com.sudy.warriorgame.warriors.fight(eric, richard) == false);
    check(com.sudy.warriorgame.warriors.fight(ogre, adam) == true);
    check(com.sudy.warriorgame.warriors.fight(freelancer, vampire) == true);
    check(freelancer.isAlive == true);

    val myArmy = Army();
    myArmy.addUnits(2) { Defender() };
    myArmy.addUnits(2) { Vampire() };
    myArmy.addUnits(4) { Lancer() };
    myArmy.addUnits(1) { Warrior() };

    val enemyArmy = Army();
    enemyArmy.addUnits(2) { Warrior() };
    enemyArmy.addUnits(2) { Lancer() };
    enemyArmy.addUnits(2) { Defender() };
    enemyArmy.addUnits(3) { Vampire() };

    val army3 = Army();
    army3.addUnits(1) { Warrior() };
    army3.addUnits(1) { Lancer() };
    army3.addUnits(2) { Defender() };

    val army4 = Army();
    army4.addUnits(3) { Vampire() };
    army4.addUnits(1) { Warrior() };
    army4.addUnits(2) { Lancer() };

    check(fight(myArmy, enemyArmy) == true);
    check(fight(army3, army4) == false);
}

fun mainVampire(args: Array<String>) {
    // smoke test
    val chuck = Warrior()
    val bruce = Warrior()
    val carl = Knight()
    val dave = Warrior()
    val mark = Warrior()
    val bob = Defender()
    val mike = Knight()
    val rog = Warrior()
    val lancelot = Defender()
    val eric = Vampire()
    val adam = Vampire()
    val richard = Defender()
    val ogre = Warrior()

    check(com.sudy.warriorgame.warriors.fight(chuck, bruce) == true)
    check(com.sudy.warriorgame.warriors.fight(dave, carl) == false)
    check(chuck.isAlive == true)
    check(bruce.isAlive == false)
    check(carl.isAlive == true)
    check(dave.isAlive == false)
    check(com.sudy.warriorgame.warriors.fight(carl, mark) == false)
    check(carl.isAlive == false)
    check(com.sudy.warriorgame.warriors.fight(bob, mike) == false)
    check(com.sudy.warriorgame.warriors.fight(lancelot, rog) == true)
    check(com.sudy.warriorgame.warriors.fight(eric, richard) == false)
    check(com.sudy.warriorgame.warriors.fight(ogre, adam) == true)

    val myArmy = Army().apply {
        addUnits(2) { Defender() }
        addUnits(2) { Vampire() }
        addUnits(1) { Warrior() }
    }

    val enemyArmy = Army().apply {
        addUnits(2) { Warrior() }
        addUnits(2) { Defender() }
        addUnits(3) { Vampire() }
    }

    val army3 = Army().apply {
        addUnits(1) { Warrior() }
        addUnits(4) { Defender() }
    }

    val army4 = Army().apply {
        addUnits(3) { Vampire() }
        addUnits(2) { Warrior() }
    }

    check(fight(myArmy, enemyArmy) == false)
    check(fight(army3, army4) == true)
    println("OK")
}


fun mainWarriors() {
    val warrior1 = Warrior()
    val warrior2 = Warrior()
    com.sudy.warriorgame.warriors.fight(warrior1, warrior2)
}

fun mainArmy() {
    val myArmy = Army()
    myArmy.addUnits(3) { Knight() }

    val enemyArmy = Army()
    enemyArmy.addUnits(3) { Warrior() }

    val army3 = Army()
    army3.addUnits(20) { Warrior() }
    army3.addUnits(5) { Knight() }

    val army4 = Army()
    army4.addUnits(30) { Warrior() }

    check(fight(myArmy, enemyArmy) == true)
    check(fight(army3, army4) == false)
    println("OK")
}

fun mainMixArmy() {
    // smoke test
    val chuck = Warrior()
    val bruce = Warrior()
    val carl = Knight()
    val dave = Warrior()
    val mark = Warrior()
    val bob = Defender()
    val mike = Knight()
    val rog = Warrior()
    val lancelot = Defender()

    check(com.sudy.warriorgame.warriors.fight(chuck, bruce) == true)
    check(com.sudy.warriorgame.warriors.fight(dave, carl) == false)
    check(chuck.isAlive == true)
    check(bruce.isAlive == false)
    check(carl.isAlive == true)
    check(dave.isAlive == false)
    check(com.sudy.warriorgame.warriors.fight(carl, mark) == false)
    check(carl.isAlive == false)
    check(com.sudy.warriorgame.warriors.fight(bob, mike) == false)
    check(com.sudy.warriorgame.warriors.fight(lancelot, rog) == true)

    val myArmy = Army()
    myArmy.addUnits(1) { Defender() }

    val enemyArmy = Army()
    enemyArmy.addUnits(2) { Warrior() }

    val army3 = Army()
    army3.addUnits(1) { Warrior() }
    army3.addUnits(1) { Defender() }

    val army4 = Army()
    army4.addUnits(2) { Warrior() }

    check(fight(myArmy, enemyArmy) == false)
    check(fight(army3, army4) == true)
    println("OK")
}
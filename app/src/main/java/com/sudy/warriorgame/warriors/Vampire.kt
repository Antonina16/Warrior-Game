import com.sudy.warriorgame.warriors.WarriorBase
import com.sudy.warriorgame.warriors.configs.Props
import com.sudy.warriorgame.warriors.interfaces.HasHealth
import com.sudy.warriorgame.warriors.interfaces.HasVampirism


class Vampire : WarriorBase(Props.Vampire.HEALTH), HasVampirism {
    override val attack: Int
        get() = Props.Vampire.ATTACK

    override val vampirism: Int
        get() = Props.Vampire.VAMPIRISM

    override fun hits(other: HasHealth): Int =
        super.hits(other).also {
            selfHeal(it * vampirism / 100)
        }
}
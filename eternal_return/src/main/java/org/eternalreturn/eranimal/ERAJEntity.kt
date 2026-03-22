package org.eternalreturn.eranimal

import net.kyori.adventure.text.Component
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeInstance
import org.bukkit.entity.*
import org.eternalreturn.util.AJEntity.AJEntity
import java.util.*

@JvmRecord
data class AreaERAnimalInfo(val name: String, val animals: MutableList<ERAnimalInfo>) {
    override fun toString(): String {
        var output = ""
        for (animal in this.animals) {
            output += (animal.name + " : " + animal.x + ", " + animal.y + ", " + animal.z + ", " + animal.yaw + ", " + animal.pitch)
            output += "\n"
        }
        return output
    }
}

class ERAJEntity(name: String, location: Location) : AJEntity(name, location) {
    var actor: Husk? = null

    //getter
    /**
     * 해당 객체의 ACTOR을 얻어온다. <br></br>
     * @ACTOR : 해당 객체가 참조하는 AJEntity의 rootEntity를 passenger로 삼는 엔티티를 말한다.
     */
    get(): Husk? {
        return field
    }

    /**
     * 해당 객체의 Actor을 설정한다. (invisible, adult, silent, invulnerable)
     */
    set(actor) {
        field = actor
        field?.let{
            it.setAdult()
            it.isSilent = true
            it.isInvisible = true
            it.isInvulnerable = true
            it.addPassenger(this.rootEntity)
            try {
                it.getAttribute(Attribute.ATTACK_DAMAGE)!!.baseValue = 0.0
                it.getAttribute(Attribute.MAX_HEALTH)!!.baseValue = 1024.0
                it.getAttribute(Attribute.ARMOR)!!.baseValue = 1024.0
                it.health = 1024.0
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }


    var hpbar: TextDisplay? = null

    fun setDebugDisplay(str: String) {
        this.hpbar!!.text(Component.text(str))
    }

    fun setDebugDisplay(cmp: Component?) {
        this.hpbar!!.text(cmp)
    }

    /**
     * 소환 이후의 로직을 설정한다.
     * 소환 직후에는 아무 것도 보이지 않도록 설정하였음
     * <blockquote><pre>
     * AJEntity.setNotBeShown(); // <- 해당 함수가 호출됨.
    </pre></blockquote> *
     */
    protected override fun afterSummoning() {
        val world = location.getWorld()
        if (world == null) {
            throw NullPointerException("전달된 매개변수 Location에 World 정보가 없습니다.")
        }

        hpbar = world.spawnEntity(location, EntityType.TEXT_DISPLAY) as TextDisplay
        hpbar!!.billboard = Display.Billboard.CENTER
        hpbar!!.backgroundColor = Color.fromARGB(0, 0, 0, 0)

        setNotBeShown()
    }

    /**
     * AJEntity 제거 뿐만 아니라 Actor까지 함께 제거한다.
     */
    override fun remove() {
        if (rootEntity != null) {
            super.remove()
            rootEntity.remove()
            rootEntity = null
        }
        if (actor != null) {
            actor!!.remove()
            actor = null
        }
        if (hpbar != null) {
            hpbar!!.remove()
            hpbar = null
        }
        this.isShown = false
    }

    protected override fun afterSpawnEvent(spawnedRootEntity: ItemDisplay?) {
        rootEntity = spawnedRootEntity
        rootEntity.addPassenger(hpbar!!)
        setNotBeShown()
    }


    override fun setNotBeShown() {
        super.setNotBeShown()
        hpbar!!.text(Component.text(""))
    }
}

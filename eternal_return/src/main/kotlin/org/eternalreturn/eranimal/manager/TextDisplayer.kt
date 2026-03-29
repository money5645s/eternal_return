package org.eternalreturn.eranimal.manager

import net.kyori.adventure.text.Component
import org.bukkit.entity.TextDisplay
import org.eternalreturn.system.EREngine
import org.dpengine.monobehaviour.MonobehaviourActor

class TextDisplayer(
    val textDisplay : TextDisplay,
    erEngine: EREngine) : MonobehaviourActor(erEngine)
{
    override fun remove() {
        super.remove()
        textDisplay.remove();
    }

    override fun lateinit() {
    }

    init{
        dpEngine.monobehaviourModule.register(this);
    }

    fun text(component : Component){
        textDisplay.text(component);
    }

}
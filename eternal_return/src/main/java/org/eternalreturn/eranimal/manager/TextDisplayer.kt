package org.eternalreturn.eranimal.manager

import org.bukkit.entity.TextDisplay
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor

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

}
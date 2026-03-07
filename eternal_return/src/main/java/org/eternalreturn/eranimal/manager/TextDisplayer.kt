package org.eternalreturn.eranimal.manager

import org.bukkit.entity.TextDisplay
import org.eternalreturn.erentity.EREntity
import org.eternalreturn.system.EREngine
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor

class TextDisplayer(
    textDisplay : TextDisplay,
    erEngine: EREngine) : EREntity(erEngine, textDisplay)
{
    override fun remove() {
        super.remove()
        entity.remove();
    }

    init{
        dpEngine.monobehaviourModule.register(this);
    }

}
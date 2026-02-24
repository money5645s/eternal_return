package org.eternalreturn.eranimal.animals.actors;

import org.eternalreturn.eranimal.ERAJEntity;
import org.eternalreturn.eranimal.ERAnimal;
import org.eternalreturn.system.EREngine;
import org.eternalreturn.system.PluginInstance;
import org.bukkit.Location;
import org.eternalreturn.util.dpengine.DPEngine;
import org.jetbrains.annotations.NotNull;

public class Boar extends ERAnimal {
    public Boar(@NotNull EREngine engine, @NotNull ERAJEntity erAjEntity, @NotNull Location location) {
        super(engine, erAjEntity, engine.createOrientedBox(location,1.5,1.5,2.0));
        this.ajEntity.registerAnimation("ready",2.0d);
        this.ajEntity.registerAnimation("attack",2.3d);
        this.ajEntity.registerAnimation("move",3.5d);
        this.ajEntity.registerAnimation("skill",3.25d);
        this.ajEntity.registerAnimation("death",2.0d);
    }
}
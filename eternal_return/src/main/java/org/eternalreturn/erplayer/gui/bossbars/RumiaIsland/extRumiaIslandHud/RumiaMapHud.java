package org.eternalreturn.erplayer.gui.bossbars.RumiaIsland.extRumiaIslandHud;

import org.eternalreturn.erplayer.ERPlayer;
import org.eternalreturn.erplayer.gui.bossbars.RumiaIsland.RumiaIslandHud;
import org.jetbrains.annotations.NotNull;

public class RumiaMapHud extends RumiaIslandHud {

    public void free(){
        super.free();
    }

    public RumiaMapHud(ERPlayer erPlayer, @NotNull String name) {
        super(erPlayer, name);
        generate();
    }

}

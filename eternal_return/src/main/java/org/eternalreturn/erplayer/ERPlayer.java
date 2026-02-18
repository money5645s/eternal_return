package org.eternalreturn.erplayer;

import org.eternalreturn.ercharacter.ERCharacter;
import org.eternalreturn.erplayer.gui.inventory.upgradesystem.UpgradeGuiController;
import org.eternalreturn.erplayer.gui.inventory.upgradesystem.view.UpgradeGui;
import org.eternalreturn.system.PluginInstance;
import org.eternalreturn.util.Gui.InventoryGui.View.IController;
import org.bukkit.entity.Player;
import org.eternalreturn.util.dpengine.DPEngine;
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourActor;
import org.eternalreturn.util.dpengine.behaviour.MonobehaviourModule;
import org.eternalreturn.util.dpengine.geometry.GeometryModule;
import org.jetbrains.annotations.NotNull;

public class ERPlayer extends ERCharacter{

    private String name = "NullPlayer";
    private UpgradeGui upgradeGui;
    private IController upgradeGuiController;

    public void free(){
        upgradeGuiController.free();
        upgradeGui.free();
    }

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    public ERPlayer(Player p, DPEngine engine){
        super(p,
                engine.createOrientedBox(p.getLocation(), 1.0 / 2, 2.0 / 2, 1.0 / 2),
                engine);
        upgradeGui = new UpgradeGui(p);
        upgradeGuiController = new UpgradeGuiController(this, upgradeGui);
    }

    public IController getUpgradeGuiController(){
        return upgradeGuiController;
    }

    //setter
    public UpgradeGui getUpgradeGui() {
        return upgradeGui;
    }

    public void sendMessage(String str){
        getEntity().sendMessage(str);
    }

}

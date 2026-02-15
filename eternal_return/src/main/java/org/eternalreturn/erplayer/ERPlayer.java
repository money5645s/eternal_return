package org.eternalreturn.erplayer;

import org.eternalreturn.ercharacter.ERCharacter;
import org.eternalreturn.erplayer.gui.inventory.upgradesystem.UpgradeGuiController;
import org.eternalreturn.erplayer.gui.inventory.upgradesystem.view.UpgradeGui;
import org.eternalreturn.system.PluginInstance;
import org.eternalreturn.util.Gui.InventoryGui.View.IController;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ERPlayer extends ERCharacter{

    private String name = "";
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

    public ERPlayer(Player p){
        super(p, PluginInstance.getEREngine().createOrientedBox(p.getLocation(), 1.0 / 2, 3.0 / 2, 1.0 / 2));
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

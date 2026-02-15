package org.EternalReturn.ERPlayer;

import org.EternalReturn.ERCharacter.character.NullCharacter;
import org.EternalReturn.ERCharacter.ERCharacter;
import org.EternalReturn.ERPlayer.Gui.Inventory.UpgradeSystem.UpgradeGuiController;
import org.EternalReturn.ERPlayer.Gui.Inventory.UpgradeSystem.View.UpgradeGui;
import org.EternalReturn.util.Gui.InventoryGui.View.IController;
import org.EternalReturn.util.Geometry.MathVector.Vec3d;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ERPlayer {

    private Player player;

    private UpgradeGui upgradeGui;
    private IController upgradeGuiController;
    private ERCharacter character;

    public void free(){
        upgradeGuiController.free();
        upgradeGui.free();
    }

    public ERPlayer(Player p){
        player = p;
        upgradeGui = new UpgradeGui(p);
        upgradeGuiController = new UpgradeGuiController(this, upgradeGui);
        character = new NullCharacter(this);
    }

    //getter
    public Player getPlayer() {
        return player;
    }

    public IController getUpgradeGuiController(){
        return upgradeGuiController;
    }


    @NotNull public ERCharacter getCharacter(){
        return this.character;
    }

    //setter
    public UpgradeGui getUpgradeGui() {
        return upgradeGui;
    }

    public void sendMessage(String str) {
        player.sendMessage(str);
    }

    public Vec3d getPos(){
        return new Vec3d(player.getLocation());
    }

    public void setCharacter(@NotNull ERCharacter character){
        this.character = character;
    }

}

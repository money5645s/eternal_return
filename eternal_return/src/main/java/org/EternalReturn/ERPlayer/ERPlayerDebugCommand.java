package org.EternalReturn.ERPlayer;

import org.EternalReturn.ERAnimal.*;
import org.EternalReturn.ERCharacter.character.adriana.Character_Adriana;
import org.EternalReturn.ERCharacter.character.fiora.Character_Fiora;
import org.EternalReturn.ERCharacter.character.hart.Character_Hart;
import org.EternalReturn.ERCharacter.character.hyunwoo.Character_Hyunwoo;
import org.EternalReturn.ERCharacter.character.isaac.Character_Isaac;
import org.EternalReturn.ERCharacter.character.jackie.Character_Jackie;
import org.EternalReturn.ERCharacter.character.lidailin.Character_LiDailin;
import org.EternalReturn.ERCharacter.character.nathapon.Character_Nathapon;
import org.EternalReturn.ERCharacter.character.yuki.Character_Yuki;
import org.EternalReturn.ERCharacter.ERCharacter;
import org.EternalReturn.EREntity.ERDummy;
import org.EternalReturn.System.PluginInstance;
import org.EternalReturn.System.SystemManager;
import org.EternalReturn.util.AJEntity.AJEntityManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ERPlayerDebugCommand implements CommandExecutor {
    private ERAJEntity testAnimal;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command");
        }

        Player p = (Player)sender;
        ERPlayer erPlayer = SystemManager.getERPlayer(p);
        Set<String> tagSet = p.getScoreboardTags();
        if(args.length == 1 && args[0].equalsIgnoreCase("showtags")){
            p.sendMessage(tagSet.toString());
            return true;
        }
        else if (args.length == 1 && args[0].equalsIgnoreCase("flushAJ")) {

            AJEntityManager.flushAllEntities();

        }

        else if(args.length == 1 && args[0].equalsIgnoreCase("enchant")){

            EntityEquipment equipment = null;
            ItemStack mainHandItem = null;
            ItemMeta mainHandItemMeta = null;
            if((equipment = p.getEquipment()) == null
                    || (mainHandItem = equipment.getItemInMainHand()).getType().equals(Material.AIR)
                    || (mainHandItemMeta = mainHandItem.getItemMeta()) == null){
                return false;
            }

            if(mainHandItem.getType().equals(Material.ENCHANTED_BOOK)){

                EnchantmentStorageMeta meta = (EnchantmentStorageMeta)mainHandItemMeta;

                p.sendMessage(meta.getStoredEnchants().toString());
            }
            else{
                p.sendMessage(mainHandItemMeta.getEnchants().toString());
            }


        }
        else if(args.length == 2 && args[0].equalsIgnoreCase("removetag")){
            tagSet.remove(args[1]);
            p.sendMessage(tagSet.toString());
            return true;
        }
        else if(args.length == 1 && args[0].equalsIgnoreCase("scoreboard")){
            ScoreboardManager scbManager = Bukkit.getScoreboardManager();
            Score data = scbManager.getMainScoreboard().getObjective("area").getScore("data");
            data.setScore(32768);
            p.sendMessage(data.getScore() + "");
        }

        else if (args[0].equalsIgnoreCase("ch")) {
            if (args.length < 2) {
                p.sendMessage("§c사용법: /er ch [캐릭터이름]");
                return true;
            }

            String charName = args[1].toLowerCase();
            ERCharacter character = null;

            // 입력된 이름에 따라 캐릭터 인스턴스 생성
            switch (charName) {
                case "lidailin":
                    character = new Character_LiDailin(erPlayer);
                    break;
                case "fiora":
                    character = new Character_Fiora(erPlayer);
                    break;
                case "hart":
                    character = new Character_Hart(erPlayer);
                    break;
                case "isaac":
                    character = new Character_Isaac(erPlayer);
                    break;
                case "jackie":
                    character = new Character_Jackie(erPlayer);
                    break;
                case "nathapon":
                    character = new Character_Nathapon(erPlayer);
                    break;
                case "adriana":
                    character = new Character_Adriana(erPlayer);
                    break;
                case "hyunwoo":
                    character = new Character_Hyunwoo(erPlayer);
                    break;
                case "yuki":
                    character = new Character_Yuki(erPlayer);
                    break;
                default:
                    p.sendMessage("§c알 수 없는 캐릭터입니다: " + charName);
                    return true;
            }

            if (character != null) {
                erPlayer.setCharacter(character);
                PluginInstance.getEREngine().registerBukkitActor(p, character);
                p.sendMessage("§a캐릭터가 변경되었습니다: §f" + character.getName());
            }
            return true;
        }

        else if(args.length == 1 && args[0].equalsIgnoreCase("dummy")){
            Entity dummy = p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
            PluginInstance.getEREngine().registerBukkitActor(dummy, new ERDummy(dummy));
            erPlayer.sendMessage("Dummy set");
        }

        return false;
    }
}

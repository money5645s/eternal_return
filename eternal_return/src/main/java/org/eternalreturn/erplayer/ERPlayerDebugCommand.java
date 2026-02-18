package org.eternalreturn.erplayer;

import org.eternalreturn.ercharacter.character.adriana.Character_Adriana;
import org.eternalreturn.ercharacter.character.fiora.Character_Fiora;
import org.eternalreturn.ercharacter.character.hart.Character_Hart;
import org.eternalreturn.ercharacter.character.hyunwoo.Character_Hyunwoo;
import org.eternalreturn.ercharacter.character.isaac.Character_Isaac;
import org.eternalreturn.ercharacter.character.jackie.Character_Jackie;
import org.eternalreturn.ercharacter.character.lidailin.Character_LiDailin;
import org.eternalreturn.ercharacter.character.nathapon.Character_Nathapon;
import org.eternalreturn.ercharacter.character.yuki.Character_Yuki;
import org.eternalreturn.ercharacter.ERCharacter;
import org.eternalreturn.erentity.ERDummy;
import org.eternalreturn.erplayer.ERPlayer;
import org.eternalreturn.system.PluginInstance;
import org.eternalreturn.system.SystemManager;
import org.eternalreturn.util.AJEntity.AJEntityManager;
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
    private org.eternalreturn.eranimal.ERAJEntity testAnimal;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command");
        }

        Player p = (Player)sender;
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

            var engine = PluginInstance.getEREngine();

            String charName = args[1].toLowerCase();
            ERCharacter character = null;
            // 입력된 이름에 따라 캐릭터 인스턴스 생성
            switch (charName) {
                case "lidailin":
                    character = new Character_LiDailin(engine, p);
                    break;
                case "fiora":
                    character = new Character_Fiora(engine, p);
                    break;
                case "hart":
                    character = new Character_Hart(engine, p);
                    break;
                case "isaac":
                    character = new Character_Isaac(engine, p);
                    break;
                case "jackie":
                    character = new Character_Jackie(engine, p);
                    break;
                case "nathapon":
                    character = new Character_Nathapon(engine, p);
                    break;
                case "adriana":
                    character = new Character_Adriana(engine, p);
                    break;
                case "hyunwoo":
                    character = new Character_Hyunwoo(engine, p);
                    break;
                case "yuki":
                    character = new Character_Yuki(engine, p);
                    break;
                default:
                    p.sendMessage("§c알 수 없는 캐릭터입니다: " + charName);
                    return true;
            }
            
            PluginInstance.getEREngine().registerBukkitActor(p, character);
            p.sendMessage("§a캐릭터가 변경되었습니다: §f" + character.getName());

            return true;
        }

        else if(args.length == 1 && args[0].equalsIgnoreCase("dummy")){
            
            var engine = PluginInstance.getEREngine();
            var erPlayer = (ERPlayer)engine.getEREntity(p);
            Entity dummy = p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
            engine.registerBukkitActor(
                    dummy,
                    new ERDummy(engine, dummy, engine.createOrientedBox(p.getLocation(),1.0/2,3.0/2,1.0/2)));
            p.sendMessage("Dummy set");
        }

        return false;
    }
}

package org.eternalreturn.system;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.eternalreturn.ercharacter.ERCharacter;
import org.eternalreturn.ercharacter.character.adriana.Character_Adriana;
import org.eternalreturn.ercharacter.character.fiora.Character_Fiora;
import org.eternalreturn.ercharacter.character.hart.Character_Hart;
import org.eternalreturn.ercharacter.character.hyunwoo.Character_Hyunwoo;
import org.eternalreturn.ercharacter.character.isaac.Character_Isaac;
import org.eternalreturn.ercharacter.character.jackie.Character_Jackie;
import org.eternalreturn.ercharacter.character.jan.Character_Jan;
import org.eternalreturn.ercharacter.character.lidailin.Character_LiDailin;
import org.eternalreturn.ercharacter.character.nathapon.Character_Nathapon;
import org.eternalreturn.ercharacter.character.sissela.Sissela;
import org.eternalreturn.ercharacter.character.yuki.Character_Yuki;
import org.eternalreturn.erentity.ERDummy;
import org.eternalreturn.erplayer.ERPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class SetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 2 && args[0].equalsIgnoreCase("area")){
            int num = -1;
            try{
                num = Integer.parseInt(args[1]);
            }catch (NumberFormatException e){
                sender.sendMessage("두 번째 매개변수는 양의 정수여야 합니다.");
                return false;
            }
            if(num == -1) return false;
            var areaSystem = PluginInstance.getEREngine().getAreaSystem();
            areaSystem.update(num);
            areaSystem.sendAreaStateToScoreboard();
        }
        else if(args.length == 1 && args[0].equalsIgnoreCase("reset")){
            var areaSystem = PluginInstance.getEREngine().getAreaSystem();
            areaSystem.reset();
        }
        return false;
    }
}

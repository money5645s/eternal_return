package org.eternalreturn.system;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

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

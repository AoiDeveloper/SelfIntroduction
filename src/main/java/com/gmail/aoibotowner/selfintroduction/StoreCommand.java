package com.gmail.aoibotowner.selfintroduction;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class StoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        switch (command.getName().toLowerCase()) {

            case "set":
                if (sender instanceof Player) {
                    StringBuilder message = new StringBuilder();
                    for(String arg: args) {
                        message.append(arg).append(" ");
                    }
                    Player p = (Player) sender;
                    p.getPersistentDataContainer().set(new NamespacedKey(SelfIntroduction.getPlugin(), "introduction"), PersistentDataType.STRING, message.toString());
                    p.sendMessage(ChatColor.AQUA + "Server: Your introduction is " + ChatColor.GOLD + message.toString() + ChatColor.AQUA + "now.");
                    return true;
                } else {
                    return false;
                }

            case "get":
                if(args.length > 1) {
                    int count = 0;
                    while (count < args.length) {
                        if(SelfIntroduction.getPlugin().getServer().getPlayer(args[count]) != null && SelfIntroduction.getPlugin().getServer().getOnlinePlayers().contains(SelfIntroduction.getPlugin().getServer().getPlayer(args[count]))) {
                            Player p = SelfIntroduction.getPlugin().getServer().getPlayer(args[count]);
                            sender.sendMessage(ChatColor.AQUA + p.getName() + ": " + ChatColor.GOLD + p.getPersistentDataContainer().get(new NamespacedKey(SelfIntroduction.getPlugin(), "introduction"), PersistentDataType.STRING));
                        }
                        count++;
                    }
                } else {
                    if(sender instanceof Player) {
                        Player p = (Player) sender;
                        sender.sendMessage(ChatColor.AQUA + p.getName() + ": " + ChatColor.GOLD + p.getPersistentDataContainer().get(new NamespacedKey(SelfIntroduction.getPlugin(), "introduction"), PersistentDataType.STRING));
                    } else {
                        return false;
                    }
                }
                return true;
            default:
                return false;
        }
    }
}

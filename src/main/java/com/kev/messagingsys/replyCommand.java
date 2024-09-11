package com.kev.messagingsys;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;


public class replyCommand implements CommandExecutor {
    // Reply  : /r <message>
    
    private Main main;
    
    public replyCommand(Main main){
        this.main = main;
    }
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        
        if (sender instanceof Player){
            Player player = (Player) sender;
            
            if(args.length >= 1){
                if(main.getRecentMessages().containsKey(player.getUniqueId())){
                    UUID uuid = main.getRecentMessages().get(player.getUniqueId());
                    
                    if(Bukkit.getPlayer(uuid) != null){
                        Player target = Bukkit.getPlayer(uuid);
    
                        if(target.getUniqueId() == player.getUniqueId()){
                            player.sendMessage(ChatColor.RED + "You cannot write a message to yourself!");
                        }
                        else {
                            StringBuilder builder = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                builder.append(args[i]).append(" ");
                            }
        
                            player.sendMessage(ChatColor.YELLOW + "You " + ChatColor.BLACK + "-> " + ChatColor.GREEN + target.getName() + ": " + builder);
                            target.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.BLACK + " ->" + ChatColor.YELLOW + " You: " + builder);
        
                            main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                        }
                        
                    }
                    else{
                        player.sendMessage(ChatColor.RED + "Person you messaged has gone offline!");
                    }
                }
                else{
                    player.sendMessage(ChatColor.RED + "You haven't messaged anyone yet!");
                }
            }
            else{
                player.sendMessage(ChatColor.RED + "Invalid Usage! Use /msg <player> <message>");
            }
            
            
            
        }
        return false;
    }
}


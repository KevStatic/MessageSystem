package com.kev.messagingsys;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class messageCommand implements CommandExecutor {
    // Message: /msg <player> <message>
    
    private Main main;
    
    public messageCommand(Main main){
        this.main = main;
    }
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        
        if (sender instanceof Player){
            Player player = (Player) sender;
            
            if (args.length >= 2){
                if (Bukkit.getPlayerExact(args[0]) != null){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    
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
                    player.sendMessage(ChatColor.RED + "This player was not found!");
                }
                
            }
            else{
                player.sendMessage(ChatColor.RED + "Invalid Usage! Use /msg <player> <message>");
            }
            
            
            
        }
        
        
        
        return false;
    }
}



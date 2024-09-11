package com.kev.messagingsys;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {
    
    
    private HashMap <UUID, UUID> recentMessages;
    
    
    
    @Override
    public void onEnable() {
        
        getCommand("msg").setExecutor(new messageCommand(this));
        getCommand("r").setExecutor(new replyCommand(this));
        
        recentMessages = new HashMap<>();
    }
    
    public HashMap<UUID, UUID> getRecentMessages() { return recentMessages; }
    
    public void onQuit(PlayerQuitEvent e){
        recentMessages.remove(e.getPlayer().getUniqueId());
    }
    
    
}

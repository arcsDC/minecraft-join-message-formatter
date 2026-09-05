package com.example.joinformatter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinFormatterPlugin extends JavaPlugin implements Listener {

    private String joinTemplate;
    private String quitTemplate;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        joinTemplate = getConfig().getString("messages.join", "&a{player} joined the game.");
        quitTemplate = getConfig().getString("messages.quit", "&c{player} left the game.");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String message = joinTemplate.replace("{player}", event.getPlayer().getName());
        event.getPlayer().sendMessage(colorize(message));
        Bukkit.broadcastMessage(colorize(message));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String message = quitTemplate.replace("{player}", event.getPlayer().getName());
        Bukkit.broadcastMessage(colorize(message));
    }

    private String colorize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}

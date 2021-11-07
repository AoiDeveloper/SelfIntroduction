package com.gmail.aoibotowner.selfintroduction;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class SelfIntroduction extends JavaPlugin implements Listener {

    private static SelfIntroduction plugin;

    public static SelfIntroduction getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        Objects.requireNonNull(getPlugin().getCommand("set")).setExecutor(new StoreCommand());
        Objects.requireNonNull(getPlugin().getCommand("get")).setExecutor(new StoreCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(e.getPlayer().getPersistentDataContainer().has(new NamespacedKey(getPlugin(), "introduction"), PersistentDataType.STRING)) return;
        e.getPlayer().getPersistentDataContainer().set(new NamespacedKey(getPlugin(), "introduction"), PersistentDataType.STRING, "");
    }
}

package xyz.villainsrule.noop;

import org.bukkit.plugin.java.JavaPlugin;

import xyz.villainsrule.noop.listeners.CommandListener;
import xyz.villainsrule.noop.listeners.PermissionListener;

public class NoOP extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CommandListener(), this);
        getServer().getPluginManager().registerEvents(new PermissionListener(), this);
    }
}
package me.ppgome.smpchat;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the SMPChat plugin. Handles command registry and startup/shutdown output.
 * @author PPGOME
 * @version 1.0
 */
public final class SMPChat extends JavaPlugin {

    /**
     * Creates an instance of the plugin
     * @return Instance of the plugin
     */
    public static SMPChat getPlugin() {
        return plugin;
    }

    private static SMPChat plugin;

    @Override
    /*
     * Runs when the server starts up
     */
    public void onEnable() {
        System.out.println("SMPChat has started!");
    }

    @Override
    /*
      Runs when the server stops
     */
    public void onDisable() {
        System.out.println("SMPChat has stopped!");
    }
}

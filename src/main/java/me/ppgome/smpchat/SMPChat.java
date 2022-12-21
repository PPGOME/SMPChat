package me.ppgome.smpchat;

import me.ppgome.smpchat.chatcolour.ColourApplier;
import me.ppgome.smpchat.message.MessageHandler;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the SMPChat plugin. Handles command registry and startup/shutdown output.
 * @author PPGOME
 * @version 1.0
 */
public final class SMPChat extends JavaPlugin implements Listener, CommandExecutor {

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

        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        this.getCommand("chatcolour").setExecutor(new ColourApplier());
        this.getCommand("message").setExecutor(new MessageHandler());
        this.getCommand("mmsg").setExecutor(new MessageHandler());
        this.getCommand("broadcast").setExecutor(new MessageHandler());

        getServer().getPluginManager().registerEvents(new MessageHandler(), this);
    }

    @Override
    /*
      Runs when the server stops
     */
    public void onDisable() {
        System.out.println("SMPChat has stopped!");
    }
}

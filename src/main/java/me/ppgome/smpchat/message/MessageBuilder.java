package me.ppgome.smpchat.message;

import me.ppgome.smpchat.PlayerInteractor;
import me.ppgome.smpchat.SMPChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * A class which contains overloaded methods for building certain messages (global, private, broadcast, etc)
 * @author PPGOME
 * @version 1.0
 */
public class MessageBuilder {
    private Player sender;
    private Player recipient;
    private Component message;

    private static final FileConfiguration CONFIG = SMPChat.getPlugin().getConfig();
    private static final MiniMessage MM = MiniMessage.miniMessage();
    private static final Component PREFIX = MM.deserialize(CONFIG.getString("prefix"));

    /**
     * Builds all global messages and applies custom colours as necessary.
     * @param sender
     * @param message
     * @return Finalized chat message
     */
    public static TextComponent buildMessage(Player sender, Component message) {
        return Component.text("<" + sender.getName() + "> ")
                .append(message.color(TextColor.fromHexString(PlayerInteractor.getChatcolour(sender))));
    }

    /**
     * Builds a private message
     * @param sender
     * @param recipient
     * @param message
     */
    public static void buildMessage(Player sender, Player recipient, String message) {
    }
    public static void buildMessage(Player sender, Player recipient, Component message) {
    }

    /**
     * Builds an error message to be used around the plugin
     * @param message
     * @param p
     */
    public static TextComponent buildErrorMessage(String message, Player p) {
        return Component.text("").append(PREFIX).color(TextColor.fromCSSHexString(CONFIG.getString("error.colour")))
                .append(Component.text(" " + message));
    }
}

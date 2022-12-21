package me.ppgome.smpchat.message;

import me.ppgome.smpchat.PlayerInteractor;
import me.ppgome.smpchat.SMPChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
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
    private static final Component BCPREFIX = MM.deserialize(CONFIG.getString("message.broadcast-prefix"));

    /**
     * Builds all global messages and applies custom colours as necessary. May not be the most practical but I wanted to
     * try what I had learned at school in an actual way.
     * @param sender Player who sent the message
     * @param message The message the player sent
     * @return Finalized chat message
     */
    public static TextComponent buildMessage(Player sender, Component message) {
        return Component.text("<" + sender.getName() + "> ")
                .append(message.color(TextColor.fromHexString(PlayerInteractor.getChatcolour(sender))));
    }

    /**
     * Builds a private message
     * @param sender Player who sent the message
     * @param recipient Player who the message was directed to
     * @param message The message the player sent
     * @param sent Determines which version to build. Players who sent get a confirmation message while players who did not get the normal message.
     */
    public static TextComponent buildMessage(Player sender, Player recipient, Component message, boolean sent, boolean frommod) {

        TextComponent recieve = Component.text("").append(PREFIX).append(Component.text(" From " + sender.getName() + ": ")
                .color(TextColor.fromHexString(CONFIG.getString("message.from-colour"))).decorate(TextDecoration.ITALIC));

        TextComponent send = Component.text("").append(PREFIX).append(Component.text(" To " + recipient.getName() + ": ")
                .color(TextColor.fromHexString(CONFIG.getString("message.from-colour"))).decorate(TextDecoration.ITALIC));

        if(frommod) {
            if(!sent) {
                return recieve.append(message.color(TextColor.fromHexString(CONFIG.getString("message.mod-colour"))));
            } else {
                return send.append(message.color(TextColor.fromHexString(CONFIG.getString("message.mod-colour"))));
            }
        } else {
            if(!sent) {
                return recieve.append(message.color(TextColor.fromHexString(CONFIG.getString("message.msgcolour"))));
            } else {
                return send.append(message.color(TextColor.fromHexString(CONFIG.getString("message.msgcolour"))));
            }
        }
    }

    /**
     * Builds a broadcast message
     * @param message Message to send out
     * @return Complete message as a component
     */
    public static TextComponent broascastBuilder(Component message) {
        return Component.text("").append(BCPREFIX).append(Component.text(" " + message)
                .color(TextColor.fromHexString(CONFIG.getString("message.broadcast-colour"))));
    }

    /**
     * Builds an error message to be used around the plugin
     * @param message The message defined in the console that outputs for select errors
     * @return A component of an error message to
     */
    public static TextComponent buildErrorMessage(String message) {
        return Component.text("").append(PREFIX).append(Component.text(" " + message)
                .color(TextColor.fromHexString(CONFIG.getString("error.colour"))));
    }

    /**
     * Combines all elements of a string array into a single string
     * @param args Array to condense into a string
     * @return a single string
     */
    public static TextComponent makeComponent(String[] args) {
        StringBuilder string = new StringBuilder();
        for (String s : args) {
            string.append(s).append(" ");
        }
        return Component.text(string.toString().trim());
    }
}

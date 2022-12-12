package me.ppgome.smpchat.message;

import me.ppgome.smpchat.SMPChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Handles private messages between players such as /msg and /r
 * @author PPGOME
 * @version 1.0
 */
public class MessageCommand implements CommandExecutor {

    private final FileConfiguration CONFIG = SMPChat.getPlugin().getConfig();
    MiniMessage mm = MiniMessage.miniMessage();
    Component prefix = mm.deserialize(CONFIG.getString("prefix"));

    @Override
    /**
     * Checks for message commands such as /msg, /message, /whisper, /r and /reply
     * @param sender The player who sent the command
     * @param command The command the player entered
     * @param label Not entirely sure yet
     * @param args Array of arguments provided
     */
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            String cmdname = command.getName();
            if(cmdname.equalsIgnoreCase("msg") || cmdname.equalsIgnoreCase("message")
                    || cmdname.equalsIgnoreCase("whisper")) {
                switch(args.length) {
                    case 0:
                        // Replace with component
                        p.sendMessage("You forgot a player name!");
                        break;
                    case 1:
                        // Replace with component
                        p.sendMessage("Planning on sending " + p.getName() + " a message?");
                        break;
                    case 2:
                        String recepient = args[0];
                        if(Bukkit.getServer().getPlayer(recepient) != null) {
                            // Add rest here
                        } else if(recepient.equalsIgnoreCase("console")) {
                            // Add rest here
                        } else {
                            p.sendMessage("That player doesn't exist?");
                        }
                        break;
                }
            }
        }
        return false;
    }

    // Have this send messages WITH COMPONENTS
    public void message(Player sender, Player recepient, String message, boolean italics, boolean bold, boolean mod) {
        String msgcolour = "";
        if (mod) {msgcolour = CONFIG.getString("mod-colour");}
        // Add system to grab chat colour from persistentdatacontainer of player

        if (CONFIG.getString("prefix") != null && CONFIG.getString("message.from-colour") != null) {
            TextComponent pmessagebuilder = Component.text("").append(prefix)
                    .color(TextColor.fromHexString(CONFIG.getString("message.from-colour")))
                    .decoration(TextDecoration.BOLD, true)
                    .append(Component.text(" From: " + sender.getName() + " > "))
                    .decoration(TextDecoration.ITALIC, italics).decoration(TextDecoration.BOLD, bold)
                    .color(TextColor.fromHexString(msgcolour))
                    .append(Component.text(message));
        }
    }
}

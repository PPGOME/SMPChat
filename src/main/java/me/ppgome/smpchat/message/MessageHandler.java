package me.ppgome.smpchat.message;

import com.sun.tools.javac.util.StringUtils;
import io.papermc.paper.event.player.AsyncChatEvent;
import me.ppgome.smpchat.SMPChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Handles private messages between players such as /msg and /r
 * @author PPGOME
 * @version 1.0
 */
public class MessageHandler implements CommandExecutor, Listener {

    private final FileConfiguration CONFIG = SMPChat.getPlugin().getConfig();
    MiniMessage mm = MiniMessage.miniMessage();
    Component prefix = mm.deserialize(CONFIG.getString("prefix"));

    @Override
    /*
    Checks for message commands such as /msg, /message, /whisper, /r and /reply. I am aware that this is a disaster.
     */
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            String cmdname = command.getName();
            if(cmdname.equalsIgnoreCase("msg") || cmdname.equalsIgnoreCase("message")
                    || cmdname.equalsIgnoreCase("whisper") || cmdname.equalsIgnoreCase("tell") ||
            cmdname.equalsIgnoreCase("mmsg")) {
                switch(args.length) {
                    // If no args
                    case 0:
                        if(CONFIG.getString("error.errormessages.msgnoargs") != null) {
                            p.sendMessage(MessageBuilder.buildErrorMessage(CONFIG.getString("error.errormessages.msgnoargs")));
                        } else {
                            p.sendMessage(MessageBuilder.buildErrorMessage("Please tell an admin that error.errormessages.msgnomsg is empty!"));
                        }
                        break;
                    // If player specified but no message given
                    case 1:
                        String recepient = args[0];
                        String error = CONFIG.getString("error.errormessages.msgnomsg");
                        if(error != null) {
                            if(Bukkit.getServer().getPlayer(recepient) != null) {
                                p.sendMessage(MessageBuilder.buildErrorMessage(error.replace("{PLAYER}", recepient)));
                            } else {
                                MessageBuilder.buildErrorMessage("That player doesn't seem to be online.");
                            }
                        } else {
                            p.sendMessage(MessageBuilder.buildErrorMessage("Please tell an admin that error.errormessages.msgnomsg is empty!"));
                        }
                        break;
                    // If both player and message are specified
                    case 2:
                        recepient = args[0];
                        // Does the player exist?
                        if(Bukkit.getServer().getPlayer(recepient) != null) {
                            // Send message

                            // Build message
                            String[] messagearray = Arrays.copyOfRange(args, 1, args.length);
                            TextComponent pmessage = MessageBuilder.makeComponent(messagearray);
                            Player playerrec = Bukkit.getPlayer(recepient);

                            if(cmdname.equalsIgnoreCase("msg") || cmdname.equalsIgnoreCase("tell") ||
                            cmdname.equalsIgnoreCase("whisper") || cmdname.equalsIgnoreCase("message")) {
                                // Send message to recipient
                                playerrec.sendMessage(MessageBuilder.buildMessage(p, playerrec, pmessage, false, false));
                                // Play ping sound
                                playerrec.playSound(playerrec.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                                // Send confirmation to sender
                                p.sendMessage(MessageBuilder.buildMessage(p, playerrec, pmessage, true, false));
                            } else if(cmdname.equalsIgnoreCase("mmsg")) {
                                // Send message to recipient
                                playerrec.sendMessage(MessageBuilder.buildMessage(p, playerrec, pmessage, false, true));
                                // Play ping sound
                                playerrec.playSound(playerrec.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                                // Send confirmation to sender
                                p.sendMessage(MessageBuilder.buildMessage(p, playerrec, pmessage, true, true));
                            }
                            return true;
                        // Or is it being sent to the console?
                        } else if(recepient.equalsIgnoreCase("console")) {
                            // Add rest here
                        } else {
                            p.sendMessage(MessageBuilder.buildErrorMessage("That player doesn't seem to be online."));
                        }
                        break;
                }
            } else if (cmdname.equalsIgnoreCase("broadcast")) {
                if(args.length > 0) {
                    String[] messagearray = Arrays.copyOfRange(args, 1, args.length);
                    TextComponent pmessage = MessageBuilder.makeComponent(messagearray);
                    p.sendMessage(MessageBuilder.broascastBuilder(pmessage));
                } else {
                    p.sendMessage(MessageBuilder.buildErrorMessage("Missing a message to broadcast!"));
                }
            }
        }
        return false;
    }

    @EventHandler
    /*
    Replaces call global chat messages with server-side, possibly coloured text.
     */
    public void onChat(AsyncChatEvent event) {
        Player p = event.getPlayer();
        Component message = event.message();
        event.setCancelled(true);
        for(Player online : Bukkit.getOnlinePlayers()) {
            online.sendMessage(MessageBuilder.buildMessage(p, message));
        }
    }
}

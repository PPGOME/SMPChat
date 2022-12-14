package me.ppgome.smpchat.chatcolour;

import me.ppgome.smpchat.PlayerInteractor;
import me.ppgome.smpchat.SMPChat;
import me.ppgome.smpchat.message.MessageBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Class that implements CommandExecutor and is used to validate the /chatcolour command.
 * @author PPGOME
 * @version 1.0
 * @see PlayerInteractor
 */
public class ColourApplier implements CommandExecutor {

    private final FileConfiguration CONFIG = SMPChat.getPlugin().getConfig();
    private final MiniMessage MM = MiniMessage.miniMessage();
    private final Component PREFIX = MM.deserialize(CONFIG.getString("prefix"));

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (command.getName().equalsIgnoreCase("chatcolour") || command.getName().equalsIgnoreCase("chatcolor")) {
                if (args.length == 1) {
                    String message = args[0];
                    p.sendMessage(message);
                    if (message.charAt(0) == '#') {
                        if (message.length() == 7) {
                            PlayerInteractor.setChatcolour(message, p);
                            p.sendMessage("Your chat colour is now " + PlayerInteractor.getChatcolour(p));
                            return true;
                        } else {
                            p.sendMessage(MessageBuilder.buildErrorMessage("Your hex code is not valid! Please use a correct one."));
                            return false;
                        }
                    } else {
                        p.sendMessage(MessageBuilder.buildErrorMessage("Your hex code did not start with a #. Please add it!"));
                        return false;
                    }
                }
            }
            return false;
        }
        return false;
    }

}
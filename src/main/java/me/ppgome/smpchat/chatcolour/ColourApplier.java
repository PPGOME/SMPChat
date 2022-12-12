package me.ppgome.smpchat.chatcolour;

import me.ppgome.smpchat.SMPChat;
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
                    if (message.substring(0).equals("#")) {
                        if (message.length() == 7) {
                            //Add persistentdatacontainer here
                        } else {
                            errorMessage("Your hex code is not valid! Please use a correct one.", p);
                        }
                    } else {
                        errorMessage("Your hex code did not start with a #. Please add it!", p);
                    }
                }
            }
            return false;
        }
        return false;
    }

    public void errorMessage(String message, Player p) {
        TextComponent errormsg = Component.text("").append(PREFIX).color(TextColor.fromCSSHexString(CONFIG.getString("error.colour")))
                .append(Component.text(message));
        p.sendMessage(errormsg);
    }

}
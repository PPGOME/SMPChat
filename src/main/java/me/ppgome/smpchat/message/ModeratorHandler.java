package me.ppgome.smpchat.message;

import me.ppgome.smpchat.SMPChat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ModeratorHandler implements CommandExecutor {

    private final FileConfiguration CONFIG = SMPChat.getPlugin().getConfig();
    MiniMessage mm = MiniMessage.miniMessage();
    Component prefix = mm.deserialize(CONFIG.getString("prefix"));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            // Player who sent command
            Player p = (Player) sender;
            String cmdname = command.getName();
            if (cmdname.equalsIgnoreCase("mmsg")) {
                if (args.length > 1) {
                    if (Bukkit.getServer().getPlayer(args[0]) != null) {
                        String[] messagearray = Arrays.copyOfRange(args, 1, args.length);
                        TextComponent pmessage = MessageBuilder.makeComponent(messagearray);
                        Player playerrec = Bukkit.getPlayer(args[1]);
                        playerrec.sendMessage(MessageBuilder.buildMessage(p, playerrec, pmessage, false, true));
                    }
                }
            } else if (cmdname.equalsIgnoreCase("broadcast") || (cmdname.equalsIgnoreCase("bc"))) {
                String[] messagearray = Arrays.copyOfRange(args, 1, args.length);
                TextComponent pmessage = MessageBuilder.makeComponent(messagearray);
                for(Player online : Bukkit.getOnlinePlayers()) {
                    online.sendMessage(MessageBuilder.broascastBuilder(pmessage));
                }
            } else if (cmdname.equalsIgnoreCase("mc")) {

            }
        }
        return false;
    }
}

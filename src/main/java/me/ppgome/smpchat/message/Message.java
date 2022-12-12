package me.ppgome.smpchat.message;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Handles private messages between players such as /msg and /r
 * @author PPGOME
 * @version 1.0
 */
public class Message implements CommandExecutor {

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
                        }
                }
            }
        }
        return false;
    }

    // Have this send messages WITH COMPONENTS
    public void message(){

    }
}

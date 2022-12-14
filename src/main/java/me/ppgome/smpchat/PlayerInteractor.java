package me.ppgome.smpchat;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class PlayerInteractor {

    public static void setChatcolour(String chatcolour, Player p) {
        NamespacedKey hex = new NamespacedKey(SMPChat.getPlugin(), "chatcolour");
        p.getPersistentDataContainer().set(hex, PersistentDataType.STRING, chatcolour);
    }

    public static String getChatcolour(Player p) {
        String chatcolourhex;
        NamespacedKey hex = new NamespacedKey(SMPChat.getPlugin(), "chatcolour");
        if(p.getPersistentDataContainer().has(hex, PersistentDataType.STRING)) {
            return p.getPersistentDataContainer().get(hex, PersistentDataType.STRING);
        }
        return "#FFFFFF";
    }

    public void addIgnore(Player p, Player ignored) {

    }
}

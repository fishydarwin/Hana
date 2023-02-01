package me.darwj.hana.string.messages;

import me.darwj.hana.string.HexColorCodes;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Messages {

    @Contract("_ -> new")
    public static @NotNull String parse(String message) {
        return ChatColor.translateAlternateColorCodes('&', HexColorCodes.colorize(message));
    }

}

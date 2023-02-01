package me.darwj.hana.string;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexColorCodes {

    private static final Pattern hexColorPattern =
            Pattern.compile("((\\B#)(((\\d|[a-f]){6})|((\\d|[A-F]){6})))");
    /**
     * Colorize hex color codes (#abcdef or #ABCDEF)
     * @param input The message to colorize
     * @return The formatted string
     */
    public static String colorize(String input) {
        String value = input;
        Matcher matcher = hexColorPattern.matcher(input);
        while (matcher.find()) {
            String hex = matcher.group().toLowerCase();
            String simpleHex = new String(new char[] {
                    '&', 'x',
                    '&', hex.charAt(1),
                    '&', hex.charAt(2),
                    '&', hex.charAt(3),
                    '&', hex.charAt(4),
                    '&', hex.charAt(5),
                    '&', hex.charAt(6),
                    });
            value = value.replace(hex, ChatColor.translateAlternateColorCodes('&', simpleHex));
        }
        return value;
    }

    /**
     * Strips hex color codes (#abcdef or #ABCDEF)
     * @param input The message to strip colors from
     * @return The formatted string
     */
    public static String stripColors(String input) {
        String value = input;
        Matcher matcher = hexColorPattern.matcher(input);
        while (matcher.find()) {
            String hex = matcher.group();
            value = value.replace(hex, "");
        }
        return value;
    }

}

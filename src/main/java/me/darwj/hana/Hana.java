package me.darwj.hana;

import me.darwj.hana.time.DurationParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

/**
 * A library filled with many quality-of-life utilities to aid development.
 */
public final class Hana extends JavaPlugin {

    private static Hana instance;
    public static Hana getInstance() { return instance; }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("" +
                Arrays.toString(DurationParser.parseUnits(Duration.between(Instant.now(), Instant.EPOCH))));
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this); // clean up after us.
    }

}

package me.darwj.hana.dynamic.memory;

import me.darwj.hana.storage.ConfigFile;

import java.io.File;
import java.io.IOException;

/**
 * Represents a Config file which has some in-memory Cache for faster access.<br><br>
 * It is required to implement separate save and reload functions that make use of the
 * associated ConfigFile to store data.<br><br>
 * While not necessary, one may use self-refreshing states to implement automatic saving,
 * or create their own variant of such a system. <b>By default, auto saving is not offered by
 * this class.</b>
 */
public abstract class MemoryCachedConfigFile {
    private ConfigFile config;

    protected MemoryCachedConfigFile(File file) throws IOException {
        this.config = new ConfigFile(file, false);
    }

    public ConfigFile getConfigFile() { return config; }

    public abstract void save() throws IOException;
    public abstract void reload() throws IOException;
}

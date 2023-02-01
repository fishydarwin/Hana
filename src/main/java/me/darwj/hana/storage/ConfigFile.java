package me.darwj.hana.storage;

import me.darwj.hana.Hana;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * Represents a Config file - this means a File associated to a YamlConfiguration.<br><br>
 * The necessity of this file comes from repeated bloat with storing files. This object
 * should allow programmers to quickly store instances of Config files without making extra
 * helper methods.<br><br>
 * It is technically possible to use this as a repository by extending it, though this behaviour
 * is not accounted for in this generic implementation and no ORM or interfacing is provided.
 */
public class ConfigFile {

    private final File file;
    private YamlConfiguration config;

    private boolean copyFromJar = false;

    public ConfigFile(File file, boolean copyFromJar) throws IOException {
        this.file = file;
        this.copyFromJar = copyFromJar;
        reload();
    }

    public YamlConfiguration getData() { return config; }

    public void save() throws IOException { config.save(file); }
    public void reload() throws IOException {
        if (copyFromJar) { // save as resource
            Hana.getInstance().saveResource(file.getPath(), false);
        } else { // clean slate if not available
            if (!file.exists()) {
                boolean dirsExist = file.getParentFile().exists();
                if (!dirsExist) { file.getParentFile().mkdirs(); }
                boolean fileExists = file.createNewFile();
                if (!fileExists) {
                    throw new IOException("File could not be created.");
                }
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

}

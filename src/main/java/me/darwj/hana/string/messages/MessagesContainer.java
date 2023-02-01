package me.darwj.hana.string.messages;

import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Represents a class that contains static Strings which represent messages for chat, console, etc.<br><br>
 * Automatically (via reflection) looks at static Strings declared in the class, and loads them
 * from the specified File.<br><br>
 * If the file does not exist, it will generate it automatically with the default provided values.<br>
 * Any missing keys from the config will be added to the config with the default value<br><br>
 * This class should not be used by itself. Another class should always extend this one instead.
 */
public class MessagesContainer {

    private final File messagesFile;
    private final YamlConfiguration messagesConfig;

    public MessagesContainer(@NotNull File file) throws IOException, IllegalAccessException {
        if (!file.exists()) {
            boolean dirsExist = file.getParentFile().exists(); // might be good already
            if (!dirsExist) { file.getParentFile().mkdirs(); } // second try
            boolean fileExists = file.createNewFile();
            if (fileExists) {
                messagesFile = file;
                messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
            } else {
                throw new IOException("File could not be created.");
            }
        } else {
            messagesFile = file;
            messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        }
        loadMessages();
    }

    private void loadMessages() throws IllegalAccessException, IOException {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) { // static fields only
                if (field.getType().isAssignableFrom(String.class)) { // string fields only
                    if (messagesConfig.isSet(field.getName())) { // if exists
                        field.set(this, messagesConfig.getString(field.getName())); // load from config
                    } else {
                        messagesConfig.set(field.getName(), field.get(this)); // set to default value
                        messagesConfig.save(messagesFile);
                    }
                }
            }
        }
    }

}

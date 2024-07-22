package me.maatija.pfm.kornercraft.managers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import me.maatija.pfm.kornercraft.configs.Lang;

public class ConfigManager {
    private final Plugin plugin;
    private File file;
    private FileConfiguration configuration;

    public ConfigManager(Plugin plugin, String name) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), name);
        saveDefaultConfig(name);
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileConfiguration getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(FileConfiguration configuration) {
        this.configuration = configuration;
    }

    public void reloadConfig(String name) {
        if (getFile() == null)
            setFile(new File(getPlugin().getDataFolder(), name));
        setConfiguration(YamlConfiguration.loadConfiguration(getFile()));
        if ("messages.yml".equals(name)) {
            setDefaultLangMessages();
        }
    }

    public FileConfiguration getConfig(String name) {
        if (getConfiguration() == null)
            reloadConfig(name);
        return getConfiguration();
    }

    public void saveDefaultConfig(String name) {
        if (getFile() == null)
            setFile(new File(getPlugin().getDataFolder(), name));
        if (!getFile().exists()) {
            InputStream defConfigStream = getPlugin().getResource(name);
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                try {
                    defConfig.save(getFile());
                } catch (IOException e) {
                    getPlugin().getLogger().log(Level.SEVERE, "Could not save default config: " + getFile(), e);
                }
            }
        }
    }

    public void saveConfig(String name) {
        try {
            getConfig(name).save(getFile());
        } catch (IOException exception) {
            getPlugin().getLogger().log(Level.SEVERE, "Could not save file to " + getFile(), exception);
        }
    }

    private void setDefaultLangMessages() {
        if (getFile() == null)
            setFile(new File(getPlugin().getDataFolder(), "messages.yml"));

        FileConfiguration config = getConfig("messages.yml");

        for (Lang lang : Lang.values()) {
            if (!config.contains(lang.getKey())) {
                config.set(lang.getKey(), lang.getMessage());
            }
        }

        try {
            config.save(getFile());
        } catch (IOException e) {
            getPlugin().getLogger().warning("Could not save default messages config: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static String getNotFound(String path, String file) {
        return "&cString \"&e%path%&c\" in \"&4%file%&c\" not found!"
                .replace("%path%", path)
                .replace("%file%", file)
                .replace('&', '§');
    }
}

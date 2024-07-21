package me.maatija.pfm.kornercraft;

import co.aikar.commands.CommandManager;
import co.aikar.commands.PaperCommandManager;
import me.maatija.pfm.kornercraft.events.ClearCubeEvent;
import me.maatija.pfm.kornercraft.commands.PMCommand;
import me.maatija.pfm.kornercraft.configs.Lang;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public final class FootcubeM extends JavaPlugin {

    private CommandManager commandManager;
    private File messagesFile;
    private FileConfiguration messagesConfig;

    @Override
    public void onEnable() {
        getLogger().info("Loading Commands.");

        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new PMCommand(this));

        getLogger().info("Commands successfully loaded!");

        getLogger().info("Loading Events");

        ClearCubeEvent clearCubeEvent = new ClearCubeEvent(this, 15);
        clearCubeEvent.start();

        getLogger().info("Events successfully loaded!");

        createMessagesFile();

        getLogger().info("FootcubeManager started successfully!");
        Bukkit.getConsoleSender().sendMessage("FootcubeManager has been enabled!");
    }

    @Override
    public void onDisable() {
        saveMessagesConfig();
    }

    private void createMessagesFile() {
        messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            getDataFolder().mkdirs();


            saveDefaultMessagesFile();
        }

        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    private void saveDefaultMessagesFile() {
        messagesConfig = new YamlConfiguration();
        for (Lang lang : Lang.values()) {
            messagesConfig.set(lang.getKey(), lang.getMessage());
        }

        try {
            messagesConfig.save(messagesFile);
        } catch (IOException e) {
            getLogger().warning("Could not save default messages config: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }

    public void saveMessagesConfig() {
        if (messagesFile != null && messagesConfig != null) {
            try {
                messagesConfig.save(messagesFile);
            } catch (IOException e) {
                getLogger().warning("Could not save messages config: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            getLogger().warning("Messages configuration or file is not initialized.");
        }
    }

    public void reloadMessagesConfig() {
        if (messagesFile == null) {
            messagesFile = new File(getDataFolder(), "messages.yml");
        }

        if (messagesFile.exists()) {
            messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);

            for (Lang lang : Lang.values()) {
                if (!messagesConfig.contains(lang.getKey())) {
                    messagesConfig.set(lang.getKey(), lang.getMessage());
                }
            }

            InputStream defConfigStream = getResource("messages.yml");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                messagesConfig.setDefaults(defConfig);
            }
        } else {
            getLogger().warning("Messages file does not exist.");
        }
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}

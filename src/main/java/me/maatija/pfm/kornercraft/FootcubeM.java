package me.maatija.pfm.kornercraft;

import co.aikar.commands.CommandManager;
import co.aikar.commands.PaperCommandManager;
import me.maatija.pfm.kornercraft.events.ClearCubeEvent;
import me.maatija.pfm.kornercraft.commands.FMCommand;
import me.maatija.pfm.kornercraft.managers.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class FootcubeM extends JavaPlugin {

    private CommandManager commandManager;
    private ConfigManager configManager;
    private UtilManager utilManager;

    @Override
    public void onEnable() {

        utilManager = new UtilManager(this);
        utilManager.getLogger().sendBanner();

        setupCommands();
        setupEvents();
        setupEvents();

        utilManager.getLogger().info("FootcubeManager has loaded succsesfully!");

        Bukkit.getConsoleSender().sendMessage("FootcubeManager has been enabled!");
    }

    @Override
    public void onDisable() {

        configManager.saveConfig("messages.yml");
    }

    public FileConfiguration getMessagesConfig() {
        return configManager.getConfig("messages.yml");
    }

    public void reloadMessagesConfig() {
        configManager.reloadConfig("messages.yml");
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public UtilManager getUtilManager() {
        return utilManager;
    }

    public void setUtilManager(UtilManager utilManager) {
        this.utilManager = utilManager;
    }

    public void setupCommands() {
        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new FMCommand(this));
    }
    public void setupEvents() {
        ClearCubeEvent clearCubeEvent = new ClearCubeEvent(this, 15);
        clearCubeEvent.start();
    }
    public void setupMessages() {
        configManager = new ConfigManager(this, "messages.yml");
        configManager.reloadConfig("messages.yml");
    }

}

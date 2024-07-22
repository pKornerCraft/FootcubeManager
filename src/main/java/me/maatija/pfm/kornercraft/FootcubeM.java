package me.maatija.pfm.kornercraft;

import co.aikar.commands.CommandManager;
import co.aikar.commands.PaperCommandManager;
import me.maatija.pfm.kornercraft.events.ClearCubeEvent;
import me.maatija.pfm.kornercraft.commands.PMCommand;
import me.maatija.pfm.kornercraft.managers.ConfigManager;
import me.maatija.pfm.kornercraft.managers.UtilManager;
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
        utilManager.getLogger().info("Loading Commands...");

        commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new PMCommand(this));
        utilManager.getLogger().info("Commands successfully loaded!");

        utilManager.getLogger().info("Loading Events...");
        ClearCubeEvent clearCubeEvent = new ClearCubeEvent(this, 15);
        clearCubeEvent.start();
        utilManager.getLogger().info("Events successfully loaded!");

        configManager = new ConfigManager(this, "messages.yml");
        configManager.reloadConfig("messages.yml");

        utilManager.getLogger().info("FootcubeManager started successfully!");
        Bukkit.getConsoleSender().sendMessage("FootcubeManager has been enabled!");
    }

    // Koja je marka calvin klain?
    // womp womp
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
}

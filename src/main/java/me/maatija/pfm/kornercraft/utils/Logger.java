package me.maatija.pfm.kornercraft.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Logger {
    private final PluginDescriptionFile description;
    private final ConsoleCommandSender consoleSender;
    private final Plugin plugin;
    private final List<String> banner = new ArrayList<>();

    public Logger(Plugin plugin) {
        this.plugin = plugin;
        this.description = plugin.getDescription();
        this.consoleSender = plugin.getServer().getConsoleSender();
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public PluginDescriptionFile getDescription() {
        return this.description;
    }

    public ConsoleCommandSender getConsoleSender() {
        return this.consoleSender;
    }

    public List<String> getBanner() {
        return this.banner;
    }

    public void sendBanner() {
        List<String> authors = getDescription().getAuthors();
        String formattedAuthors = authors.stream().map(String::valueOf).collect(Collectors.joining(", "));
        String pluginName = getDescription().getFullName();
        String serverName = getPlugin().getServer().getName();
        String version = getPlugin().getServer().getBukkitVersion();
        String serverNameVersion = serverName + " - " + version;

        getBanner().clear();
        getBanner().add("&r");
        getBanner().add("&b 888888 &38b   d88  &b" + pluginName);
        getBanner().add("&b 88     &388b  d88  &3Author: &b" + formattedAuthors);
        getBanner().add("&b 888888 &388YbdP88");
        getBanner().add("&b 88     &388 YY 88  &8Running on " + serverNameVersion);
        getBanner().add("&r");

        for (String message : getBanner()) {
            getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    public void info(String message) {
        getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &bFootcube&3Manager &8| &f" + message));
    }

    public void warning(String message) {
        getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8| &bFootcube&3Manager &8| &f" + message));
    }

    public void reload() {
    }

    public void send(Player player, String message) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}

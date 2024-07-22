package me.maatija.pfm.kornercraft.managers;

import me.maatija.pfm.kornercraft.utils.Logger;
import org.bukkit.plugin.Plugin;

public class UtilManager {
    private final Logger logger;

    public UtilManager(Plugin plugin) {
        this.logger = new Logger(plugin);
    }

    public Logger getLogger() {
        return this.logger;
    }

    public void reloadLogger() {
        getLogger().reload();
    }
}


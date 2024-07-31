package me.maatija.pfm.kornercraft.events;

import me.maatija.pfm.kornercraft.configs.Lang;
import me.maatija.pfm.kornercraft.managers.UtilManager;
import me.maatija.pfm.kornercraft.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class ClearCubeEvent {
    private final UtilManager utilManager;
    private final Logger logger;
    private final int intervalTicks;

    public ClearCubeEvent(UtilManager utilManager, int intervalMinutes) {
        this.utilManager = utilManager;
        this.logger = utilManager.getLogger();
        this.intervalTicks = intervalMinutes * 60 * 20;
    }

    public void start() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        Server server = utilManager.getPlugin().getServer();

        if (server.getOnlinePlayers().isEmpty()) {
            return;
        }

        scheduler.runTaskTimer(utilManager.getPlugin(), () -> {
            for (Player player : server.getOnlinePlayers()) {
                logger.send(player, Lang.CLEARCUBE_ANNOUNCE.getMessage());
            }

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cw -w world -o 482 46 -86 butcher 100");
        }, 0, intervalTicks);
    }
}

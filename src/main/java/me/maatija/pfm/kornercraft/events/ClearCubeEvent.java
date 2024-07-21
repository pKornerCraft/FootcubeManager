package me.maatija.pfm.kornercraft.events;

import me.maatija.pfm.kornercraft.configs.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ClearCubeEvent {

    private final JavaPlugin plugin;
    private final int intervalTicks;

    public ClearCubeEvent(JavaPlugin plugin, int intervalMinutes) {
        this.plugin = plugin;
        this.intervalTicks = intervalMinutes * 60 * 20;
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {

                String announceMessage = Lang.CLEARCUBE_ANNOUNCE.getMessage();


                boolean hasOnlinePlayers = !Bukkit.getOnlinePlayers().isEmpty();


                if (hasOnlinePlayers) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage(announceMessage);
                    }
                }


                ConsoleCommandSender console = Bukkit.getConsoleSender();
                console.sendMessage(announceMessage);


                Bukkit.dispatchCommand(console, "cw -w world -o 482 46 -86 butcher 100");
            }
        }.runTaskTimer(plugin, 0, intervalTicks);
    }
}
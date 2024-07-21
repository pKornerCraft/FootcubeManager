package me.maatija.pfm.kornercraft.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import me.maatija.pfm.kornercraft.FootcubeM;
import me.maatija.pfm.kornercraft.configs.Lang;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("pm")
public class PMCommand extends BaseCommand {

    private final FootcubeM plugin;

    public PMCommand(FootcubeM plugin) {
        this.plugin = plugin;
    }

    @Subcommand("info")
    public void onInfo(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.CANNOT_EXECUTE.getMessage());
            return;
        }

        Player player = (Player) sender;

        player.sendMessage("");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bFootcube&3Manager &7version &b0.0.1-beta"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Created by: &cMaatijaa"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Contact Discord: &cParadoxer#8391"));
        player.sendMessage("");
    }

    @Subcommand("help")
    @CommandPermission("footcubemanager.help")
    public void onHelp(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.CANNOT_EXECUTE.getMessage());
            return;
        }

        Player player = (Player) sender;
        player.sendMessage(Lang.HELP.getMessage());
    }

    @Subcommand("reload")
    @CommandPermission("footcubemanager.reload")
    public void onReload(CommandSender sender) {
        plugin.reloadMessagesConfig();
        String reloadMessage = Lang.RELOAD_SUCCESS.getMessage();

        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(reloadMessage);
        } else {
            sender.sendMessage(reloadMessage);
        }
    }
}

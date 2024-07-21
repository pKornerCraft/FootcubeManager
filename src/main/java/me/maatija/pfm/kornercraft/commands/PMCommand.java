package me.maatija.pfm.kornercraft.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PMCommand extends BaseCommand {

    @CommandAlias("info|pm info")
    public class MainCommandExecutor extends BaseCommand {

        @Subcommand("info")
        public void onInfo(CommandSender sender) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Ova komanda je dostupna samo igračima.");
                return;
            }

            Player player = (Player) sender;


            player.sendMessage("");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Foot&ccube&4Manager &7version &b0.0.1-beta"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Created by: &cMaatijaa"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Contact Discord: &cParadoxer#8391"));
            player.sendMessage("");
        }
    }
}
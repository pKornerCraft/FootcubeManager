package me.maatija.pfm.kornercraft.configs;

import lombok.Getter;
import org.bukkit.ChatColor;

@Getter
public enum Lang {
    INSUFICCIENT_PERMS("insuficcient_permissions", "&4▎ &cNedovoljno Permisija."),
    CLEARCUBE_ANNOUNCE("clearcube_announce", "&a▎ &fLopte uspesno obrisane sa treninga."),
    CANNOT_EXECUTE("cannot_execute", "&4▎ &cNije moguće izvršiti ovu komandu u konzoli."),
    RELOAD_SUCCESS("reload_success", "&a▎ &fPlugin je osvežen!"),
    HELP("help", String.join(System.lineSeparator(),
            "&7▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬&r",
            "&f &bFootcube&3Manager &7- &fLista dostupnih komandi.",
            "&r &r",
            "&c /&4fm help &7- &fOtvara ovu stranicu.",
            "&c /&4fm info &7- &fOtvara informacije plugina.",
            "&c /&4fm reload &7- &fOsvežava Plugin.",
            "&r &r",
            "&7▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬&r"
    ));

    private final String key;
    private final String message;

    Lang(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return this.key;
    }

    public String getMessage() {
        return ChatColor.translateAlternateColorCodes('&', this.message);
    }
}

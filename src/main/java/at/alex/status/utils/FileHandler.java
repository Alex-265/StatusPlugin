package at.alex.status.utils;

import at.alex.status.status;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class FileHandler {
    status Status = status.getPlugin(status.class);
    FileConfiguration config = Status.getCustomConfig();
    File configFile = Status.getCustomConfigFile();
    public void AddStatus(Player player, String text) throws IOException {
        config.set("status." + player.getUniqueId() + ".status", text);
        config.save(configFile);
    }
    public String GetStatus(Player player) {
        return config.getString("status." + player.getUniqueId() + ".status");
    }
    public void AddStatusColor(Player player, String colorstr) throws IOException {
        config.set("status." + player.getUniqueId() + ".color", colorstr);
        config.save(configFile);
    }
    public String GetStatusColor(Player player) {
        return config.getString("status." + player.getUniqueId() + ".color");
    }

}

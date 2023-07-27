package at.alex.status;

import at.alex.status.commands.statusc;
import at.alex.status.commands.statuscolor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class status extends JavaPlugin {

    private File customConfigFile;
    private FileConfiguration customConfig;

    @Override
    public void onEnable() {
        this.getLogger().log(Level.INFO, "Enabling status Plugin");
        // Plugin startup logic
        createCustomConfig();
        getCommand("status").setExecutor(new statusc());
        getCommand("statuscolor").setExecutor(new statuscolor());

    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "Shutting down status Plugin");
        // Plugin shutdown logic
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    public File getCustomConfigFile() {
        return this.customConfigFile;
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "status.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("custom.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        /* User Edit:
            Instead of the above Try/Catch, you can also use
            YamlConfiguration.loadConfiguration(customConfigFile)
        */
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        statusc Statusc = new statusc();
        Player player = event.getPlayer();
        try {
            Statusc.loadonjoin(event.getPlayer());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

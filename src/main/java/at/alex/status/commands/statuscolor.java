package at.alex.status.commands;

import at.alex.status.utils.FileHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class statuscolor implements CommandExecutor {
    FileHandler fileHandler = new FileHandler();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            fileHandler.AddStatusColor((Player) sender, args[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Player player = (Player) sender;
        statusc statusc = new statusc();
        try {
            statusc.loadonjoin(player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

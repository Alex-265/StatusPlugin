package at.alex.status.commands;

import at.alex.status.utils.FileHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class statusc implements CommandExecutor {

    FileHandler fileHandler = new FileHandler();
    ChatColor chatColor;

    private void setPrefix(Player player, String text) throws IOException {
        Scoreboard scoreboard = player.getScoreboard();
        Team players = scoreboard.getTeam(player.getUniqueId().toString());
        if(players == null) {
            players = scoreboard.registerNewTeam(player.getUniqueId().toString());
            fileHandler.AddStatusColor(player, "WHITE");
            return;
        }
        String colorstring = fileHandler.GetStatusColor(player);
        ChatColor chatColor = ChatColor.valueOf(colorstring.toUpperCase());
        players.setPrefix(chatColor + "[" + text + "] ");
        players.addPlayer(player);
        players.addEntry(player.getName());
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, "/team join " + player.getUniqueId() + " " + player.getName());

        try {
            chatColor = ChatColor.valueOf(colorstring.toUpperCase());
        } catch (Exception e) {
            chatColor = ChatColor.WHITE;
        }
        player.sendMessage(ChatColor.BOLD + "[AlexTools]" + " Dein status wurde auf " + chatColor + text + ChatColor.WHITE + " gesetzt!");

    }


    public void loadonjoin(Player player) throws IOException {
        if (fileHandler.GetStatus(player) == null) {
            return;
        }
        setPrefix(player, fileHandler.GetStatus(player));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        String text = args[0];
        Player player = (Player) sender;

        try {
            setPrefix(player, text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileHandler.AddStatus(player, text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}

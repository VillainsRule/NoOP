package xyz.villainsrule.noop.listeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandListener implements Listener {
    List<String> blockedCommands = Arrays.asList("defaultgamemode", "deop", "function", "gamemode", "give", "op", "seed", "setblock", "teleport", "tp");

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        String[] commandParts = message.split(" ")[0].split(":");

        if (Arrays.stream(commandParts).anyMatch(blockedCommands::contains)) {
            event.setCancelled(true);

            if (player != null) player.setOp(false);
        }
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent event) {
        String command = event.getCommand();

        String[] commandParts = command.split(" ")[0].split(":");

        if (Arrays.stream(commandParts).anyMatch(blockedCommands::contains))
            event.setCancelled(true);
    }
}
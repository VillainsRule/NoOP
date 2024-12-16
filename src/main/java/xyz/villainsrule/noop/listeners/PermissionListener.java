package xyz.villainsrule.noop.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PermissionListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        checkPermissions(player);
    }

    @EventHandler
    public void onCommandEvent(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (checkPermissions(player))
            event.setCancelled(true);
    }

    @EventHandler
    public void onInteractionEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (checkPermissions(player))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        checkPermissions(player);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();

        if (checkPermissions(player))
            event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryCreative(InventoryCreativeEvent event) {
        Player player = (Player) event.getWhoClicked();

        event.setCancelled(true);
        player.setGameMode(GameMode.SURVIVAL);

        checkPermissions(player);
    }

    @EventHandler
    public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();

        if (checkPermissions(player)) {
            event.setCancelled(true);
        }
    }

    public boolean checkPermissions(Player player) {
        if (player.isOp() || player.getGameMode() == GameMode.CREATIVE || player.hasPermission("*")) {
            player.setOp(false);
            player.setGameMode(GameMode.SURVIVAL);
            return true;
        }

        return false;
    }
}
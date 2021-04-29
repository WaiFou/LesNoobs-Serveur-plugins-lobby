package fr.lesnoobs.lobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class playerDrop implements Listener{
	@EventHandler
	public static void onPlayerDrop(PlayerDropItemEvent e) {
		Player p = (Player) e.getPlayer();
		if(!p.isOp()){
			e.setCancelled(true);
		}
	}
}

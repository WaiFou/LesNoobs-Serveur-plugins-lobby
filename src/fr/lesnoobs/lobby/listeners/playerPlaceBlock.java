package fr.lesnoobs.lobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class playerPlaceBlock implements Listener{
	@EventHandler
	public static void onPlayerPlaceBlock(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(!p.isOp()){
			e.setCancelled(true);
		}
	}
}

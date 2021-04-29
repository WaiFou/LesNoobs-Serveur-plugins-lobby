package fr.lesnoobs.lobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class playerTakeDamage implements Listener{
	@EventHandler
	public static void onPlayerTakeDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(!p.isOp()) {
				e.setCancelled(true);
			}
		}
	}
}

package fr.lesnoobs.lobby.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.lesnoobs.lobby.Functions;

public class playerJoin implements Listener{
	@EventHandler
	public static void onPlayerJoin(PlayerJoinEvent e) {	
		e.setJoinMessage("");
		Functions.playerJoin(e.getPlayer());
	}
}

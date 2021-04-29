package fr.lesnoobs.lobby.jump;

import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lesnoobs.lobby.Functions;

public class UpdateTimeHotBar extends BukkitRunnable{
	@Override
	public void run() {
		for (Entry<Player, Integer> entry : Jump.playerOnJump.entrySet()) {
			Player p = entry.getKey();
			String temps_min_sec= TimeConverter.convert(Jump.tasks.get(p).temps);
			
			Functions.hotbarMessage(p, "Votre temps actuel est de "+temps_min_sec);
		}
	}
}
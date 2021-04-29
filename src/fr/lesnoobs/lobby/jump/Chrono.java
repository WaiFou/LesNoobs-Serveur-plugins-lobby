package fr.lesnoobs.lobby.jump;

import org.bukkit.scheduler.BukkitRunnable;

public class Chrono extends BukkitRunnable{
	int temps = 0;
	
	@Override
	public void run() {
		temps++;
	}
}

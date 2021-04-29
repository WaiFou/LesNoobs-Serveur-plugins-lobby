package fr.lesnoobs.lobby.jump;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.lesnoobs.lobby.Functions;
import fr.lesnoobs.lobby.Main;
import net.md_5.bungee.api.ChatColor;

public class Scoreboard {
	public static HashMap<Player, Integer> classements = new HashMap<Player, Integer>();
	public static World world = Main.getInstance().getServer().getWorld("world");
	
	public static void setScoreBoard() {
		killOldScoreBoard();
		getClassementFile();
		
		
		placeArmorStand();
	}
	
	public static void placeArmorStand() {
		ArmorStand armor_top = (ArmorStand) world.spawnEntity(new Location(world, 7.5, 1.5, -12.5), EntityType.ARMOR_STAND);
		armor_top.setCustomName(ChatColor.DARK_RED+""+ChatColor.UNDERLINE+""+ChatColor.BOLD+"Classement général du jump");
		armor_top.setCustomNameVisible(true);
		armor_top.setVisible(false);
		armor_top.setGravity(false);
		
		List<Player> playerList = new ArrayList<Player>();
		List<String> tempsList = new ArrayList<String>();
		
		for(Entry<Player, Integer> entry : classements.entrySet()) {
			playerList.add(entry.getKey());
			tempsList.add(TimeConverter.convert(entry.getValue()));
		}
		
		for(int i=0; i<10; i++) {
			if (i < tempsList.size()) {
				ArmorStand cl = (ArmorStand) world.spawnEntity(new Location(world, 7.5, (1.5-(0.25*(i+1))), -12.5), EntityType.ARMOR_STAND);
				String name = ChatColor.DARK_AQUA+""+(i+1)+". "+ChatColor.RESET; //prefix
				name+=ChatColor.YELLOW+""+playerList.get(i).getDisplayName()+" "+ChatColor.RESET; //username
				name+=ChatColor.GREEN+""+tempsList.get(i); //temps	
				cl.setCustomName(name);
				cl.setCustomNameVisible(true);
				cl.setVisible(false);
				cl.setGravity(false);
			}
		}
	}
	
	public static void killOldScoreBoard() {
		
		for(Entity entity : world.getNearbyEntities(new Location(world, 7.5, 2, -12.5), 2, 2, 2)) {
			if(entity instanceof ArmorStand) {
				entity.remove();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void getClassementFile() {
		File f = new File(Main.getInstance().getDataFolder(), "Jump/classement.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
		
		List<String> listUUID = new ArrayList<String>();
		for (String key : config.getKeys(false)) {
			listUUID.add(key);
		}
		
		for (String uuid : listUUID) {
			Player p = Bukkit.getPlayer(UUID.fromString(uuid));
			int time = config.getInt(uuid);
			
			classements.put(p, time);
		}
		
		classements = Functions.sortHashMap(classements);
	}
	
	public static void saveClassementFile(Player p, int temps) {
		File f = new File(Main.getInstance().getDataFolder(), "Jump/classement.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
		
		List<String> listUUID = new ArrayList<String>();
		for (String key : config.getKeys(false)) {
			listUUID.add(key);
		}
		
		if(listUUID.contains(p.getUniqueId().toString())) {
			int old_time = config.getInt(p.getUniqueId().toString());
			if (temps<old_time) {
				config.set(p.getUniqueId().toString(), temps);
			}
		}
		else {
			config.set(p.getUniqueId().toString(), temps);
		}

		try {
			config.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setScoreBoard(); //update scoreboard
	}
}

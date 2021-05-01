package fr.lesnoobs.lobby.jump;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.lesnoobs.lobby.Functions;
import fr.lesnoobs.lobby.Main;

public class Jump {
	public static HashMap<Player, Integer> playerOnJump = new HashMap<Player, Integer>(); //1:start 2:checkpoint
	public static HashMap<Player, Chrono> tasks = new HashMap<Player, Chrono>();
	
	public static void jump(Player p, Material plate) {
		if(plate==Material.STONE_PLATE) {
			if(!playerOnJump.containsKey(p)) {
				playerOnJump.put(p, 1);
				giveItemJump(p);
				startChrono(p);
			}
		}
		else if(plate==Material.IRON_PLATE) {
			if(playerOnJump.containsKey(p)) {
				if(playerOnJump.get(p)==1) {
					Functions.hotbarMessage(p, "Point de sauvegarde activé");
					playerOnJump.put(p, 2);
				}
			}
			else {
				Functions.hotbarMessage(p, "Vous n'avez pas encore commencé le jump");
			}
		}
		else if(plate==Material.GOLD_PLATE) {
			if(playerOnJump.containsKey(p)) {
				jumpFinish(p);
			}
			else {
				Functions.hotbarMessage(p, "Vous n'avez pas encore commencé le jump");
			}
		}
		
	}
	
	public static void giveItemJump(Player p) {
		Inventory inv = p.getInventory();
		
		ArrayList<String> lore_item1 = new ArrayList<String>();
		ArrayList<String> lore_item2 = new ArrayList<String>();
		ArrayList<String> lore_item3 = new ArrayList<String>();
		
		inv.setItem(3, Functions.customItem(new ItemStack(Material.STONE_PLATE), "Recommencer", lore_item1));
		inv.setItem(4, Functions.customItem(new ItemStack(Material.IRON_PLATE), "Checkpoint", lore_item2));
		inv.setItem(5, Functions.customItem(new ItemStack(Material.GOLD_PLATE), "Annuler", lore_item3));
	}
	
	public static void clear_all_jump(Player p) {
		playerOnJump.remove(p);
		tasks.remove(p);
		p.getInventory().setItem(3, new ItemStack(Material.AIR));
		p.getInventory().setItem(4, new ItemStack(Material.AIR));
		p.getInventory().setItem(5, new ItemStack(Material.AIR));
	}
	
	public static void startChrono(Player p) {
		Chrono task = new Chrono();
		task.runTaskTimer(Main.getInstance(), 0, 20);
		tasks.put(p, task);
	}
	public static void plaque_annuler(Player p) {
		clear_all_jump(p);
		Functions.hotbarMessage(p, "Parkour annulé");
	}
	
	public static void plaque_checkpoint(Player p) {
		Functions.hotbarMessage(p, "Téléportation au point de sauvegarde");
		if(tasks.containsKey(p)) {
			if(playerOnJump.get(p)==2) {
				p.teleport(new Location(p.getWorld(), 3.5, 11, 15.5, -90, 0)); 
			}
			else {
				p.teleport(new Location(p.getWorld(), 12.5, 5, -19.5, -35, 0));
			}
		}
		else {
			p.teleport(new Location(p.getWorld(), 12.5, 5, -19.5, -35, 0));
		}
	}
	
	public static void plaque_restart(Player p) {
		playerOnJump.remove(p);
		if(tasks.containsKey(p)) {
			tasks.get(p).cancel();
			tasks.remove(p);
		}
		p.teleport(new Location(p.getWorld(), 12.5, 5, -19.5, -35, 0)); 
		Functions.hotbarMessage(p, "Parkour recommencé");
	}
	
	public static void jumpFinish(Player p){
		int temps = Jump.tasks.get(p).temps;
		String temps_min_sec= TimeConverter.convert(temps);
		
		Functions.hotbarMessage(p, "Parkour fini en "+temps_min_sec);
		
		clear_all_jump(p);
		p.teleport(new Location(p.getWorld(), 8.5, 1, -12.5, 90, 0));
		
		Scoreboard.saveClassementFile(p, temps);
	}
}

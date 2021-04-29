package fr.lesnoobs.lobby.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.lesnoobs.lobby.jump.Jump;
import fr.lesnoobs.lobby.menu.Menu;


public class playerInteract implements Listener{
	@EventHandler
	public static void onPlayerInteractEvent(PlayerInteractEvent e) {
		List<Action> rightClick = new ArrayList<Action>();
		List<Action> leftClick = new ArrayList<Action>();
		List<Action> allClicks = new ArrayList<Action>();
		rightClick.add(Action.RIGHT_CLICK_AIR); 
		rightClick.add(Action.RIGHT_CLICK_BLOCK); 
		leftClick.add(Action.LEFT_CLICK_AIR); 
		leftClick.add(Action.LEFT_CLICK_BLOCK);
		allClicks.addAll(rightClick);
		allClicks.addAll(leftClick);
		
		if (e.getAction()==Action.PHYSICAL) {
			List<Material> plaqueJump = new ArrayList<Material>();
			plaqueJump.add(Material.STONE_PLATE);
			plaqueJump.add(Material.IRON_PLATE);
			plaqueJump.add(Material.GOLD_PLATE);
			
			if(plaqueJump.contains(e.getClickedBlock().getType())) {
				Jump.jump(e.getPlayer(), e.getClickedBlock().getType());
				e.setCancelled(true);
			}
		}
		else if (allClicks.contains(e.getAction())) {
			if(rightClick.contains(e.getAction())){
				Player p = e.getPlayer();
				ItemStack is = e.getItem();
				Material ma = e.getMaterial();
				if(!ma.equals(Material.AIR)) {
					if(is.getItemMeta().hasDisplayName()) {
						if(is.getItemMeta().getDisplayName().equalsIgnoreCase("Recommencer") && ma.equals(Material.STONE_PLATE)) {
							Jump.plaque_restart(p);
						}
						else if(is.getItemMeta().getDisplayName().equalsIgnoreCase("Checkpoint") && ma.equals(Material.IRON_PLATE)) {
							Jump.plaque_checkpoint(p);
						}
						else if(is.getItemMeta().getDisplayName().equalsIgnoreCase("Annuler") && ma.equals(Material.GOLD_PLATE)) {
							Jump.plaque_annuler(p);
						}
						else if(is.getItemMeta().getDisplayName().equalsIgnoreCase("Menu des serveurs") && ma.equals(Material.COMPASS)) {
							Menu.ClickCompass(p);
						}
					}
				}
			}
		}
	}
}

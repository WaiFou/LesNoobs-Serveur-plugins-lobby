package fr.lesnoobs.lobby.menu;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import fr.lesnoobs.lobby.Main;

public class Menu implements Listener{
	public static void ClickCompass(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "Menu des serveurs");
		HashMap<ItemStack, String> menu_content = ListeServeur.readListeServeur();
		
		for (Entry<ItemStack, String> e : menu_content.entrySet()) {
			ItemStack is = e.getKey();
			int slot = Integer.parseInt(e.getValue());
			
			inv.setItem(slot, is);
		}
		
		p.openInventory(inv);
	}
	
	@EventHandler
	public static void onPlayerClick(InventoryClickEvent e) {
		if(e.getInventory().getTitle().equalsIgnoreCase("Menu des serveurs")) {
			e.setCancelled(true);
			if(e.isLeftClick()) {
				if(e.getSlot() < e.getInventory().getSize() && e.getSlot() >= 0) {
					if(e.getInventory().getItem(e.getSlot())!=null){
						if(e.getWhoClicked() instanceof Player) {
							Player p = (Player) e.getWhoClicked();
							String action = ListeServeur.getActionBySlot(e.getSlot());
							
							final ByteArrayDataOutput out = ByteStreams.newDataOutput();
							out.writeUTF("Connect");
							out.writeUTF(action);
							p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
						}
					}
				}	
			}
		}
		else {
			if(e.getWhoClicked() instanceof Player) {
				Player p = (Player) e.getWhoClicked();
				if(!p.isOp()){
					e.setCancelled(true);
				}
			}
		}
	}
}

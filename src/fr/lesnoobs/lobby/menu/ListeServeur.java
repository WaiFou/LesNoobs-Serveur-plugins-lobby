package fr.lesnoobs.lobby.menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.lesnoobs.lobby.Functions;
import fr.lesnoobs.lobby.Main;


public class ListeServeur {
	public static HashMap<Player, Integer> hmAddMenu = new HashMap<Player, Integer>();
	public static HashMap<Player, ItemStack> itemStackAddMenu = new HashMap<Player, ItemStack>();
	public static HashMap<Player, String> nameAddMenu = new HashMap<Player, String>();
	public static HashMap<Player, Integer> slotAddMenu = new HashMap<Player, Integer>();
	public static HashMap<Player, String> loreAddMenu = new HashMap<Player, String>();
	
	public static HashMap<ItemStack, String> readListeServeur() {
		HashMap<ItemStack, String> menuContent = new HashMap<ItemStack, String>();
		
		File f = new File(Main.getInstance().getDataFolder(), "Menu/config.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
		
		for (String slot : config.getKeys(false)) {
			ItemStack is = config.getItemStack(slot+".block");
			String nom = config.getString(slot+".text");
			String lore_s = config.getString(slot+".lore");
			
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(lore_s);
			
			ItemStack block = Functions.customItem(is, nom, lore);
			
			menuContent.put(block, slot);
		}
		return menuContent;
	}
	
	public static String getActionBySlot(int Slot) {
		String slot = ""+Slot;
		
		File f = new File(Main.getInstance().getDataFolder(), "Menu/config.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
		
		String action = config.getString(slot+".action");
		
		return action;
	}
	
	public static void addMenu(ItemStack is, String name, String lore, String action, int num, Player p) {
		File f = new File(Main.getInstance().getDataFolder(), "Menu/config.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
		
		if(config.get(""+num)==null) {
			if(num>=0 && num <=26) {
				config.set(""+num+".block", is);
				config.set(""+num+".text", name);
				config.set(""+num+".lore", lore);
				config.set(""+num+".action", action);
			
				try {
					config.save(f);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			else {
				p.sendMessage("Le slot doit être entre 0 et 26 compris.");
			}
		
		}
		else {
			p.sendMessage("L'emplacement dans lequel vous souhaitez ajouter l'item n'est pas libre.");
			p.sendMessage("Merci d'utiliser la commande '/menu remove' avant.");
		}
	}
	
	public static void removeMenu(int a, Player p) {
		File f = new File(Main.getInstance().getDataFolder(), "Menu/config.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
		
		if (a>=0 && a<=26) {
			if(config.get(""+a)!=null) {
				p.sendMessage("Le bloc a bien été retiré du menu.");
				config.set(""+a, null);		
				try {
					config.save(f);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			else {
				p.sendMessage("Il n'y a rien à cette emplacement.");
			}
		}
		else {
			p.sendMessage("Le slot doit entre entre 0 et 26 compris.");
		}
	}
}

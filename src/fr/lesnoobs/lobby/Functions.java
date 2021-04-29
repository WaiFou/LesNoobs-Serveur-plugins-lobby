package fr.lesnoobs.lobby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Functions {
	public static void playerJoin(Player p) {
		p.setGameMode(GameMode.SURVIVAL);
		p.teleport(new Location(p.getWorld(), 0.5, 4, 0.5, 180, 0));
		clearInventory(p);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("Click moi dessus pour choisir ton seurveur de jeu");
		lore.add("Certains serveurs ne sont compatibles qu'avec certaines versions:");
		lore.add("Ces versions peuvent etre vanilla ou moddé");
		lore.add("Les joueurs Bedrock n'ont pas accès à l'entièreté du serveur");
		p.getInventory().setItem(0, customItem(new ItemStack(Material.COMPASS), "Menu des serveurs", lore));
		
	}
		
	public static void clearInventory(Player p) {
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
	}
	
	public static ItemStack customItem(ItemStack is, String name, ArrayList<String> lore){ //lore => text sous item
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	public static void hotbarMessage(Player p, String msg) {
        String s = ChatColor.translateAlternateColorCodes('&', msg);
        IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s +
                "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap sortHashMap(HashMap map) {
		List linkedlist = new LinkedList(map.entrySet());
		Collections.sort(linkedlist, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = linkedlist.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		} 
		return sortedHashMap;
	}
}

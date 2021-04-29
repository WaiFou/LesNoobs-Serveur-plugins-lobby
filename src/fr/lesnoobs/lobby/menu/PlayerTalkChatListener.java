package fr.lesnoobs.lobby.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("deprecation")
public class PlayerTalkChatListener implements Listener {
	@EventHandler
	public static void onPlayerTalk(PlayerChatEvent e) {
		Player p = e.getPlayer();
		if (ListeServeur.hmAddMenu.containsKey(p)) {
			if(ListeServeur.hmAddMenu.get(p)==1) {
				String name = e.getMessage();
				ListeServeur.hmAddMenu.put(p, 2);
				ListeServeur.nameAddMenu.put(p, name);
				p.sendMessage("Veuillez maintenant choisir un lore.");
			}
			else if(ListeServeur.hmAddMenu.get(p)==2){
				String lore = e.getMessage();
				ListeServeur.hmAddMenu.put(p, 3);
				ListeServeur.loreAddMenu.put(p, lore);
				p.sendMessage("Veuillez maintenant chosir un slot.");
			}
			else if(ListeServeur.hmAddMenu.get(p)==3){
				String mess = e.getMessage();
				try {
    				int slot = Integer.parseInt(mess);
    				ListeServeur.hmAddMenu.put(p, 4);
    				ListeServeur.slotAddMenu.put(p, slot);
					p.sendMessage("Veuillez maintenant choisir un serveur. (survie, minijeux, ...)");
				} catch (NumberFormatException f) {
					p.sendMessage("Veuillez uniquement indiquer un nombre entier.");
				}
			}
			
			else if (ListeServeur.hmAddMenu.get(p)==4) {
				String name = ListeServeur.nameAddMenu.get(p);
				String lore = ListeServeur.loreAddMenu.get(p);
				int slot = ListeServeur.slotAddMenu.get(p);
				String action = e.getMessage();
					
				name = ChatColor.translateAlternateColorCodes('&', name);
				lore = ChatColor.translateAlternateColorCodes('&', lore);
					
				ListeServeur.addMenu(ListeServeur.itemStackAddMenu.get(p), name, lore, action, slot, p);
					
					
				ListeServeur.hmAddMenu.remove(p);
				ListeServeur.itemStackAddMenu.remove(p);
				ListeServeur.nameAddMenu.remove(p);
				ListeServeur.slotAddMenu.remove(p);
				p.sendMessage("Votre item a bien été ajouté au menu !");
			}
			e.setCancelled(true);
		}
	}
}

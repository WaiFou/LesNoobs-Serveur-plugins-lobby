package fr.lesnoobs.lobby.menu;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MenuCommand implements CommandExecutor{
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
            	//open menu
    		}
            else {
                if (args[0].equalsIgnoreCase("add")) {
                	if (p.isOp()) {
                		if (args.length==1) {
                			if(p.getItemInHand().getType() != Material.AIR){
                				p.sendMessage("Veuillez choisir un nom.");
                				ListeServeur.hmAddMenu.put(p, 1);
                				ListeServeur.itemStackAddMenu.put(p, new ItemStack(p.getItemInHand()));
                			}
                			else {
                				Error(p, 4);
                			}
                		}
                		else {
                			Error(p, 1);
                		}
                	}
            		else {
            			Error(p, 5);
            		}
                
                }
                else if (args[0].equalsIgnoreCase("remove")) {
                	if (p.isOp()) {
                		if (args.length==2) {
                			try {
                				ListeServeur.removeMenu(Integer.parseInt(args[1]), p);
                			} catch (NumberFormatException e) {
                				Error(p, 3);
                				return false;
							}
                		}
                		else {
                			Error(p, 2);
                		}
                	}
            		else {
            			Error(p, 5);
            		}
                
                }
                else {
                	Error(p, 0);
                }
            }
        }
		return false;
    }
	
	public static void Error(Player p, int i) {
		p.sendMessage("Merci de respecter la syntaxe:");
		if (i==0) {
			p.sendMessage(" - /menu                         Ouvrir le menu.");
			p.sendMessage(" - /menu add                     Ajouter au menu le bloc que vous avez dans la main avec le nom et le lore que vous avez défini.");
			p.sendMessage(" - /menu remove <id slot>        Retire du menu le bloc correspondant au slot que vous avez choisi.");
		}
		else if (i==1) {
			p.sendMessage(" - /menu add                     Ajouter au menu le bloc que vous avez dans la main avec le nom et le lore que vous avez défini.");
		}
		else if (i==2) {
			p.sendMessage(" - /menu remove <id slot>        Retire du menu le bloc correspondant au slot que vous avez choisi.");
		}
		else if (i== 3) {
			p.sendMessage("L'<id slot> doit être un entier.");
			p.sendMessage(" - /menu remove <id slot>        Retire du menu le bloc correspondant au slot que vous avez choisi.");
		}
		else if (i==4) {
			p.sendMessage("Vous devez avoir un bloc dans votre main.");
		}
		else if (i==5) {
			p.sendMessage("Vous devez être op pour utiliser cette commande.");
		}
	}
}

package fr.lesnoobs.lobby;

import org.bukkit.plugin.java.JavaPlugin;

import fr.lesnoobs.lobby.jump.Scoreboard;
import fr.lesnoobs.lobby.jump.UpdateTimeHotBar;
import fr.lesnoobs.lobby.listeners.FoodLevelChange;
import fr.lesnoobs.lobby.listeners.PlayerBrokeBlock;
import fr.lesnoobs.lobby.listeners.onRain;
import fr.lesnoobs.lobby.listeners.playerDrop;
import fr.lesnoobs.lobby.listeners.playerInteract;
import fr.lesnoobs.lobby.listeners.playerJoin;
import fr.lesnoobs.lobby.listeners.playerPlaceBlock;
import fr.lesnoobs.lobby.listeners.playerTakeDamage;
import fr.lesnoobs.lobby.menu.Menu;
import fr.lesnoobs.lobby.menu.MenuCommand;
import fr.lesnoobs.lobby.menu.PlayerTalkChatListener;

public class Main extends JavaPlugin{
	public static Main INSTANCE;
	
	public void onEnable() {
		INSTANCE = this;
		
		UpdateTimeHotBar updateTimeHB = new UpdateTimeHotBar();
		updateTimeHB.runTaskTimer(this, 0, 20);
		
		Scoreboard.setScoreBoard();
		
		getCommand("menu").setExecutor(new MenuCommand());
		
		getServer().getPluginManager().registerEvents(new FoodLevelChange(), this);
		getServer().getPluginManager().registerEvents(new onRain(), this);
		getServer().getPluginManager().registerEvents(new playerTakeDamage(), this);
		getServer().getPluginManager().registerEvents(new playerDrop(), this);
		getServer().getPluginManager().registerEvents(new playerJoin(), this);
		getServer().getPluginManager().registerEvents(new PlayerBrokeBlock(), this);
		getServer().getPluginManager().registerEvents(new playerPlaceBlock(), this);
		getServer().getPluginManager().registerEvents(new playerInteract(), this);
		getServer().getPluginManager().registerEvents(new Menu(), this);
		getServer().getPluginManager().registerEvents(new PlayerTalkChatListener(), this);
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	
	public void onDisable() {
		
	}
	
	public static Main getInstance() {
		return INSTANCE;
	}
}

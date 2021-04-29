package fr.lesnoobs.lobby.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class onRain implements Listener{
	@EventHandler
    public static void Rain(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}

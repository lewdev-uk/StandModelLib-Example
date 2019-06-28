package uk.lewdev.shipmodel;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import uk.lewdev.shipmodel.events.ShipSpawnListener;
import uk.lewdev.shipmodel.events.ShipInteractEvent;
import uk.lewdev.standmodels.StandModelLib;

public class ShipPlugin extends JavaPlugin {
	
	public static ShipPlugin instance;
	private Config ourConfig;
	
	private StandModelLib modelLib;
	
	@Override
	public void onEnable() {
		instance = this;
		
		if (! this.getDataFolder().exists()) {
			this.getDataFolder().mkdirs();
		}

		super.saveDefaultConfig();
		
		this.ourConfig = new Config(this);
		
		// Lets create our StandModelLib instance.
		long updateTickSpeed = 15;
		long animationTicks = 3;
		this.modelLib = new StandModelLib(this, updateTickSpeed, animationTicks);
		
		Bukkit.getPluginManager().registerEvents(new ShipInteractEvent(), this);
		Bukkit.getPluginManager().registerEvents(new ShipSpawnListener(this), this);
	}
	
	@Override
	public void onDisable() {
		// This is extremely important.
		// Stop all tasks and unregister all events.
		// Then removes all stands from the world.
		this.modelLib.destroy();
	}
	
	public Config getOurConfig() {
		return this.ourConfig;
	}
	
	public StandModelLib getStandModelLib() {
		return this.modelLib;
	}
}

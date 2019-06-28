package uk.lewdev.shipmodel;

import org.bukkit.configuration.file.FileConfiguration;
import uk.lewdev.standmodels.utils.Axis;

public class Config {
	
	private static String PATH_SPAWN_COMMAND = "ship_model.spawn_command";
	private static String PATH_FACING = "ship_model.facing";
	private static String PATH_RENDER_DISTANCE = "ship_model.render_distance";
	private static String PATH_ANIM_DISTANCE = "ship_model.animation_distance";
	
	private ShipPlugin plugin;
	
	public Config(ShipPlugin plugin) {
		this.plugin = plugin;
	}
	
	public String getSpawnCommand() {
		return config().getString(PATH_SPAWN_COMMAND);
	}
	
	public Axis getDefaultAxis() {
		return Axis.valueOf(config().getString(PATH_FACING));
	}
	
	public int getShipRenderDistance() {
		return config().getInt(PATH_RENDER_DISTANCE);
	}
	
	public int getShipAnimDistance() {
		return config().getInt(PATH_ANIM_DISTANCE);
	}
	
	private final FileConfiguration config() {
		return this.plugin.getConfig();
	}
}

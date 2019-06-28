package uk.lewdev.shipmodel.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import uk.lewdev.shipmodel.ShipModel;
import uk.lewdev.shipmodel.ShipPlugin;
import uk.lewdev.standmodels.utils.Axis;

public class ShipSpawnListener implements Listener {
	
	private final ShipPlugin plugin;
	
	public ShipSpawnListener(ShipPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getInventory().getItemInMainHand().getType() == Material.BEDROCK) {
				
				//Get the center top Location of the block
				Location center = event.getClickedBlock().getLocation().add(0.5, 1, 0.5);
				Axis facing = Axis.getAxis(p.getLocation());
				
				this.plugin.getStandModelLib().getModelManager().spawnModel(new ShipModel(center, facing));
				
				event.setCancelled(true);
			}
		}
	}
}
package uk.lewdev.shipmodel.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import uk.lewdev.standmodels.events.custom.ModelInteractEvent;
import uk.lewdev.standmodels.model.Model;

public class ShipInteractEvent implements Listener {
	
	@EventHandler
	public void onModelInteract(ModelInteractEvent event) {
		Model m = event.getModel();
		Player p = event.getInteractor();
		
		p.sendMessage("Oi stop it!");
		p.setVelocity(p.getLocation().toVector().subtract(m.getCenter().toVector()).normalize().setY(0.5));
	}
}
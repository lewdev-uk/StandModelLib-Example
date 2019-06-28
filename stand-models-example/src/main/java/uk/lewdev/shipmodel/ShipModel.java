package uk.lewdev.shipmodel;

import java.util.HashSet;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import uk.lewdev.standmodels.model.Model;
import uk.lewdev.standmodels.parser.ModelBuildInstruction;
import uk.lewdev.standmodels.parser.ModelSpawnCommandParser;
import uk.lewdev.standmodels.utils.Axis;

public class ShipModel extends Model {
	
	//Load the spawn command out of the config
	private static String spawnCmd = ShipPlugin.instance.getOurConfig().getSpawnCommand();
	
	//Generate the list of instructions by building a new ModelSpawnCommandParser and providing it with the spawn command.
	private static List<ModelBuildInstruction> ins = new ModelSpawnCommandParser(spawnCmd)
			.getInstructions();
	
	//Load other optional variables out of the config - it's up to you how you handle this stuff.
	private static Axis defaultFacing = ShipPlugin.instance.getOurConfig().getDefaultAxis();
	private static int renderDistance = ShipPlugin.instance.getOurConfig().getShipRenderDistance();
	private static int animDistance = ShipPlugin.instance.getOurConfig().getShipAnimDistance();
	
	//Custom ShipModel specific object variables to handle the animation
	private boolean goingUp = true;
	private final Location originalCenter = this.getCenter();
	private Location animationCenter = this.getCenter();

	// Now our model constructor, which requires way less arguments than the Model constructor! Much nicer to work with.
	// Again: How you handle this data & data structure is completely up to you. This is just a quick and simple tutorial.
	public ShipModel(Location center, Axis desired) {
		super(ins, center, defaultFacing, desired, renderDistance, animDistance);
		super.setAnimated(true);
	}
	
	@Override
	// We override this method from the Model class.
	// It is optional to Override, meaning if your model doesn't animate - you don't have to implement this method here!
	public void animationTick(HashSet<Player> playersInAnimationDistance) {
		playersInAnimationDistance.stream().forEach(player -> {
			player.playSound(this.getCenter(), Sound.BLOCK_BEACON_ACTIVATE, 0.02f, 0.01f);
		});
		
		//If the animation has moved past 0.5 blocks in any direction from the original center,
		//it's time to start moving in the opposite.
		if(this.goingUp &&
				(this.animationCenter.getY() - this.originalCenter.getY()) >= 0.3) {
			this.goingUp = false;
		}
		else if(! this.goingUp &&
				(this.animationCenter.getY() - this.originalCenter.getY() <= 0)) {
			this.goingUp = true;
		}
		
		if(goingUp) {
			this.animationCenter.add(0, 0.05, 0);
		} else {
			this.animationCenter.subtract(0, 0.05, 0);
		}
		
		super.setCenter(this.animationCenter);
	}
}

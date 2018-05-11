package de.wingaming.present.entity;

import java.util.List;

import de.wingaming.present.Main;
import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.particle.Particle;
import de.wingaming.present.utils.ImageUtils;
import de.wingaming.present.utils.Location;
import de.wingaming.present.world.World;
import javafx.scene.image.Image;

public class ParticleLadderHelper extends Entity {
	
	private Particle particle;
	
	public ParticleLadderHelper(Location position) {
		super(AnimationManager.getAnimation("empty"), position, EntityType.LADDER_HELPER, 16, 16);
		
		setGravity(false);
		
		this.particle = new Particle(getPosition().getX()-8, getPosition().getY()-8, 16, 16, ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/keys/w.png")), 32));
	}
	
	@Override
	public void update() {
		super.update();
		
		particle.setX(getPosition().getX()-8 - Main.game.getCurrentWorld().getCamera().getX()*World.TILE_SIZE);
		particle.setY(getPosition().getY()-8 - Main.game.getCurrentWorld().getCamera().getY()*World.TILE_SIZE);
		
		List<Entity> nearby = getWorld().getEntitiesInRadius(this.getPosition(), 3);

		boolean found = false;
		
		for (Entity entity : nearby) {
			if (entity.getType() == EntityType.PLAYER) {
				getWorld().addPartcle(particle);
				found = true;
				break;
			}
		}
		
		if (!found)
			getWorld().removePartcle(particle);
	}
}

package de.wingaming.present.entity;

import java.util.List;

import de.wingaming.present.Main;
import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.gui.overlay.Overlay;
import de.wingaming.present.input.KeyboardManager;
import de.wingaming.present.particle.Particle;
import de.wingaming.present.utils.ImageUtils;
import de.wingaming.present.utils.Location;
import de.wingaming.present.world.World;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class EntityPresent extends Entity {

	private Particle particle;
	
	public EntityPresent(Location position) {
		super(AnimationManager.getAnimation("present"), position, EntityType.PRESENT, 16, 16);
		
		setGravity(true);
		
		this.particle = new Particle(getPosition().getX(), getPosition().getY(), 16, 16, ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/keys/e.png")), 32));
	}
	
	@Override
	public void update() {
		super.update();
		
		particle.setX(getPosition().getX() - Main.game.getCurrentWorld().getCamera().getX()*World.TILE_SIZE);
		particle.setY(getPosition().getY() - 32 - Main.game.getCurrentWorld().getCamera().getY()*World.TILE_SIZE);
		
		List<Entity> nearby = getWorld().getEntitiesInRadius(this.getPosition(), 1.5);

		boolean found = false;
		
		for (Entity entity : nearby) {
			if (entity.getType() == EntityType.PLAYER) {
				Player player = (Player) entity;
				
				getWorld().addPartcle(particle);
				found = true;
				
				if (player.isOnGround() && KeyboardManager.isDown(KeyCode.E)) {
					getWorld().removePartcle(particle);
					getWorld().markEntityToRemove(this);
					
					Overlay.presentBar.addPresent();
					
					System.out.println("pick up");
				}
				
				break;
			}
		}
		
		if (!found)
			getWorld().removePartcle(particle);
	}
}
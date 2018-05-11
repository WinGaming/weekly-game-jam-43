package de.wingaming.present.entity;

import java.util.List;

import de.wingaming.present.Main;
import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.input.KeyboardManager;
import de.wingaming.present.particle.Particle;
import de.wingaming.present.utils.ImageUtils;
import de.wingaming.present.utils.Location;
import de.wingaming.present.world.TileType;
import de.wingaming.present.world.World;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Friend extends Entity {

	private Particle particleKey;
	
	public Friend(Location position) {
		super(AnimationManager.getAnimation("friend"), position, EntityType.FRIEND, 22*0.75, 50*0.75);
		
		this.particleKey = new Particle(getPosition().getX(), getPosition().getY(), 16, 16, ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/keys/e.png")), 2));
	}
	
	@Override
	public void update() {
		super.update();

		particleKey.setX(getPosition().getX() - Main.game.getCurrentWorld().getCamera().getX()*World.TILE_SIZE);
		particleKey.setY(getPosition().getY() - getHeight()/2 - Main.game.getCurrentWorld().getCamera().getY()*World.TILE_SIZE);
		
		boolean found = false;
		List<Entity> nearby = getWorld().getEntitiesInRadius(this.getPosition().clone().add(-getWidth(), getHeight()), 0.75);

		for (Entity entity : nearby) {
			if (entity.getType() == EntityType.PLAYER) {
				((Player) entity).setCanControll(false);
//				Overlay.presentBar.setCollected(0);
				
				Main.game.getCurrentWorld().addPartcle(particleKey);
				
				if (KeyboardManager.isDown(KeyCode.E)) {
					for (int y = -1; y < 2; y++) {
						for (int x = -2; x < 3; x++) {
							int rx = x+getPosition().getTileX();
							int ry = (int) (y+((getPosition().getY()+getHeight())/World.TILE_SIZE));
							
							if (getWorld().getTileTypeAt(rx, ry) != null)
								getWorld().setTile(rx, ry, TileType.PLANKS_DARK);
						}
					}
				}
				
				found = true;
				break;
			}
		}
		
		if (!found) {
			Main.game.getCurrentWorld().removePartcle(particleKey);
		}
	}
}
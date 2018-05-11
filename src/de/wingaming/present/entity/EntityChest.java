package de.wingaming.present.entity;

import java.util.List;

import de.wingaming.present.Main;
import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.gui.overlay.Overlay;
import de.wingaming.present.input.KeyboardManager;
import de.wingaming.present.item.Items;
import de.wingaming.present.particle.Particle;
import de.wingaming.present.utils.ImageUtils;
import de.wingaming.present.utils.Location;
import de.wingaming.present.world.World;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class EntityChest extends Entity {

	private boolean opened = false;
	private Particle particle;
	private Particle itemParticle;
	
	public EntityChest(Location position) {
		super(AnimationManager.getAnimation("chest"), position, EntityType.CHEST_DOUBLEJUMP, 20, 20);
		
		setGravity(true);
		
		this.particle = new Particle(getPosition().getX(), getPosition().getY(), 16, 16, ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/keys/e.png")), 32));
		this.itemParticle = new Particle(getPosition().getX(), getPosition().getY(), 16, 16, ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/items/hook_particle.png")), 32));
	}
	
	@Override
	public void update() {
		super.update();
		
		particle.setX(getPosition().getX() - Main.game.getCurrentWorld().getCamera().getX()*World.TILE_SIZE);
		particle.setY(getPosition().getY() - 32 - Main.game.getCurrentWorld().getCamera().getY()*World.TILE_SIZE);

		itemParticle.setX(getPosition().getX() - Main.game.getCurrentWorld().getCamera().getX()*World.TILE_SIZE);
		itemParticle.setY(getPosition().getY() - 25 - Main.game.getCurrentWorld().getCamera().getY()*World.TILE_SIZE);
		
		List<Entity> nearby = getWorld().getEntitiesInRadius(this.getPosition(), 2);

		boolean found = false;
		
		if (!opened)
			for (Entity entity : nearby) {
				if (entity.getType() == EntityType.PLAYER) {
					getWorld().addPartcle(particle);
					found = true;
					
					if (KeyboardManager.isDown(KeyCode.E)) {
						Overlay.inventory.setItem(0, Items.doubleJump);
						getAnimation().setCurrentAnimation("opened");
						getWorld().addPartcle(itemParticle);
						opened = true;
					}
					
					break;
				}
			}
		
		if (!found)
			getWorld().removePartcle(particle);
	}
}
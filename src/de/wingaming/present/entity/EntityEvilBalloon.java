package de.wingaming.present.entity;

import java.util.List;

import de.wingaming.present.Main;
import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.gui.GameOverlay;
import de.wingaming.present.utils.Location;

public class EntityEvilBalloon extends LivingEntity {

	public EntityEvilBalloon(Location position) {
		super(20, AnimationManager.getAnimation("evil_balloon"), position, EntityType.EVIL_BALLOON, 9*2.5, 32*2.5);
		
		getVelocity().setX(1);
	}
	
	@Override
	public void update() {
		super.update();
		
		getVelocity().setY(Main.game.getCurrentWorld().getPlayer().getVelocity().getY());
		
//		if (Main.game.getCurrentWorld().getPlayer().getPosition().getX()+Main.game.getCurrentWorld().getPlayer().getWidth() > getPosition().getX()) {
//			Main.game.getCurrentWorld().getPlayer().getPosition().setX(getPosition().getX()-Main.game.getCurrentWorld().getPlayer().getWidth());
//			Main.game.getCurrentWorld().getPlayer().getVelocity().setX(-2);
//		}
		
		List<Entity> entities = getWorld().getEntitiesInRadius(getPosition().clone().add(getWidth()/2, getHeight()/2), 2.75);
		
		for (Entity entity : entities) {
			if (entity.getType() == EntityType.PLAYER) {
				GameOverlay.showAu();
			}
		}
	}
	
	@Override
	public void collideX() {
		getVelocity().setX(-getVelocity().getX());
	}
}

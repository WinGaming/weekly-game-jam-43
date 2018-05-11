package de.wingaming.present.entity;

import java.util.Random;

import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.utils.Location;

public class EntityBalloon extends Entity {

	public EntityBalloon(Location position) {
		super(AnimationManager.getAnimation("balloon"), position, EntityType.BALLOON, 9*2.5, 32*2.5);
		
		setGravity(false);
		
		getVelocity().setX((new Random().nextBoolean() ? -1 : 1) * 1);
		getVelocity().setY((new Random().nextBoolean() ? -1 : 1) * 0.5);
		
		int r = new Random().nextInt(4);
		getAnimation().setCurrentAnimation(r == 0 ? "red" : r == 1 ? "green" : r == 2 ? "blue" : "yellow");
	}
	
	@Override
	public void update() {
		super.update();
		
		increasePosition(getVelocity().getX(), getVelocity().getY());
	}
	
	@Override
	public void collideX() {
		getVelocity().setX(-getVelocity().getX());
	}
	
	@Override
	public void collideY(boolean bottom) {
		getVelocity().setY(-getVelocity().getY());
	}
}

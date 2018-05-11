package de.wingaming.present.entity;

import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.utils.Location;

public class EntityExplosion extends Entity {
	
	private long spawnTime;
	
	public EntityExplosion(Location position) {
		super(AnimationManager.getAnimation("explosion"), position, EntityType.EXPLOSION, 20, 20);
		
		spawnTime = System.currentTimeMillis();
		
		setGravity(true);
	}
	
	@Override
	public void update() {
		super.update();
		
		if (System.currentTimeMillis() - spawnTime > 100) {
			getWorld().markEntityToRemove(this);
			
			return;
		}
	}
}
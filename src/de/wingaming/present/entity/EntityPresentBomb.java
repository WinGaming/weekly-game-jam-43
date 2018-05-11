package de.wingaming.present.entity;

import java.util.List;

import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.gui.GameOverlay;
import de.wingaming.present.utils.Location;

public class EntityPresentBomb extends Entity {
	
	private boolean exploded = false;
	private long spawnTime;
	
	private boolean ignorePlayer = false;
	
	public EntityPresentBomb(Location position, boolean ignorePlayer) {
		super(AnimationManager.getAnimation("present_bomb"), position, EntityType.PRESENT_BOMB, 20, 20);
		
		this.ignorePlayer = ignorePlayer;
		
		spawnTime = System.currentTimeMillis();
		
		setGravity(true);
	}
	
	@Override
	public void update() {
		super.update();
		
		if (exploded) return;
		
		if (System.currentTimeMillis() - spawnTime > 2000) {
			getWorld().markEntityToRemove(this);
			
			getWorld().markEntityToSpawn(new EntityExplosion(getPosition().clone()));
			
			exploded = true;
			
			return;
		}

		List<Entity> nearby = getWorld().getEntitiesInRadius(this.getPosition(), 1.5);
		for (Entity entity : nearby) {
			if (entity instanceof LivingEntity) {
				getWorld().markEntityToRemove(this);
				getWorld().markEntityToSpawn(new EntityExplosion(getPosition().clone()));

				LivingEntity le = (LivingEntity) entity;
				le.setHealth(le.getHealth()-2);
				
				exploded = true;
				break;
			}
			
			if (!ignorePlayer && entity.getType() == EntityType.PLAYER) {
				getWorld().markEntityToRemove(this);
				getWorld().markEntityToSpawn(new EntityExplosion(getPosition().clone()));
				
				GameOverlay.showDamage();
				
				exploded = true;
				
				break;
			}
		}
	}
}
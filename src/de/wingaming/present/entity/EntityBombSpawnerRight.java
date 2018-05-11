package de.wingaming.present.entity;

import java.util.Random;

import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.utils.Location;
import de.wingaming.present.world.World;

public class EntityBombSpawnerRight extends Entity {

	private long lastSpawn;
	
	public EntityBombSpawnerRight(Location position, double width, double height) {
		super(AnimationManager.getAnimation("bomb_spawner"), position, EntityType.BOMB_SPAWNER, width, height);
		
		setGravity(false);
		
		lastSpawn = System.currentTimeMillis();
	}
	
	@Override
	public void update() {
		super.update();
		
		if (System.currentTimeMillis() - lastSpawn > 2500 + new Random().nextInt(100)) {
			EntityPresentBomb bomb = new EntityPresentBomb(getPosition().clone().add(World.TILE_SIZE, 0), false);
			
			getWorld().markEntityToSpawn(bomb);
			
			lastSpawn = System.currentTimeMillis();
		}
	}
}
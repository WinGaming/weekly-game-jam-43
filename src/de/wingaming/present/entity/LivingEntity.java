package de.wingaming.present.entity;

import de.wingaming.present.animation.Animation;
import de.wingaming.present.utils.Location;

public class LivingEntity extends Entity {

	private double health, maxHealth;
	private long damageAnimationStarted;
	
	public LivingEntity(double maxHealth, Animation animation, Location position, EntityType type, double width, double height) {
		super(animation, position, type, width, height);
		
		this.health = this.maxHealth = maxHealth;
	}
	
	@Override
	public void update() {
		super.update();
		
		if (this.health <= 0) {
			getWorld().markEntityToRemove(this);
		}
		
		if (getAnimation().getCurrentAnimation().equals("damaged") && System.currentTimeMillis()-damageAnimationStarted > 250) {
			getAnimation().setCurrentAnimation("default");
		}
	}
	
	public void setHealth(double health) {
		if (health <= this.health) {
			getAnimation().setCurrentAnimation("damaged");
			damageAnimationStarted = System.currentTimeMillis();
		}
		
		this.health = health;
	}
	
	public double getHealth() {
		return health;
	}
	
	public double getMaxHealth() {
		return maxHealth;
	}
}
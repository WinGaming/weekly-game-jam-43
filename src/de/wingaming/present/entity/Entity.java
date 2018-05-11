package de.wingaming.present.entity;

import de.wingaming.present.animation.Animation;
import de.wingaming.present.math.Vector2d;
import de.wingaming.present.utils.Direction;
import de.wingaming.present.utils.Location;
import de.wingaming.present.world.World;
import javafx.scene.image.Image;

public class Entity {
	
	private boolean gravity;
	private Location position;
	private EntityType type;
	
	private double width, height;
	
	private Animation animation;
	
	private Direction lookDirect = Direction.RIGHT;
	private Vector2d velocity;
	private final double maxXVelocity = 5, maxYVelocity = 5;
	
	public Entity(Animation animation, Location position, EntityType type, double width, double height) {
		this.animation = animation;
		this.position = position;
		this.type = type;
		this.width = width;
		this.height = height;
		
		this.gravity = true;
		this.velocity = new Vector2d(0, 0);
	}
	
	public void update() {
		try {
			animation.update();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		
		if (gravity) {
			velocity.substract(getWorld().getGravity());
			
			velocity.setX(Math.min(velocity.getX(), maxXVelocity));
			velocity.setY(Math.min(velocity.getY(), maxYVelocity));
			
			increasePosition(velocity.getX(), velocity.getY());
		}
	}
	
	public void increasePosition(double dx, double dy) {
		if (this.position.getX() + dx < 0) dx = this.position.getX()-World.DEBUG_FACTOR;
		
		this.position = getWorld().collide(this, position, dx, dy);
	}
	
	public void setLookDirect(Direction lookDirect) {
		this.lookDirect = lookDirect;
	}
	
	public Direction getLookDirect() {
		return lookDirect;
	}
	
	public World getWorld() {
		return position.getWorld();
	}
	
	public Location getPosition() {
		return position;
	}
	
	public EntityType getType() {
		return type;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}
	
	public boolean hasGravity() {
		return gravity;
	}
	
	public void collideX() {
		velocity.setX(0);
	}
	
	public void collideY(boolean bottom) {
		velocity.setY(0);
	}
	
	public Vector2d getVelocity() {
		return velocity;
	}
	
	public Animation getAnimation() {
		return animation;
	}
	
	public Image getCurrentTexture() {
		return animation.getCurrentImage();
	}
}
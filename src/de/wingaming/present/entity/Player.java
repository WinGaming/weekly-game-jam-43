package de.wingaming.present.entity;

import de.wingaming.present.Main;
import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.camera.Camera;
import de.wingaming.present.gui.overlay.Overlay;
import de.wingaming.present.input.KeyboardManager;
import de.wingaming.present.item.Items;
import de.wingaming.present.math.Vector2d;
import de.wingaming.present.utils.Direction;
import de.wingaming.present.utils.Location;
import de.wingaming.present.world.TileType;
import de.wingaming.present.world.World;
import javafx.scene.input.KeyCode;

public class Player extends Entity {

	private boolean jumping = false;
	private boolean onGround = false;
	
	private boolean usedDoubleJump = false;
	private long jumpUse;
	
	private boolean canControll = true;
	
	private long lastBomb;
	
	public Player(Location position) {
		super(AnimationManager.getAnimation("player"), position, EntityType.PLAYER, 28*0.75, 54*0.75);
		
		lastBomb = System.currentTimeMillis();
	} 
	
	@Override
	public void update() {
		super.update();
		
		boolean moved = false;
		boolean onLadder = false;
		
		if (getVelocity().getY() != 0) {
			onGround = false;
//			getAnimation().setCurrentAnimation("falling");
		}
		
		getVelocity().setX(getVelocity().getX()*0.75);
		
		if (canControll) {
			if (KeyboardManager.isDown(KeyCode.W)) {
				int x = getPosition().getTileX();
				int y = (int) ((getPosition().getY()+getHeight())/World.TILE_SIZE);
				TileType type = getWorld().getDecoTileTypeAt(x, y);
				
				if (type == TileType.LADDER) {
					onLadder = true;
					getVelocity().setY(0);
					increasePosition(0, -1);
					
					if (!getAnimation().getCurrentAnimation().equals("ladder")) getAnimation().setCurrentAnimation("ladder");
				}
			}
			
			if (onLadder) {
				increasePosition(0, -getVelocity().getY());
			}
			
			if (KeyboardManager.isDown(KeyCode.D)) {
				increasePosition(1.5, 0);
				setLookDirect(Direction.RIGHT);
				if (onGround && !getAnimation().getCurrentAnimation().equals("walk_right")) getAnimation().setCurrentAnimation("walk_right");
				moved = true;
			}
			
			if (KeyboardManager.isDown(KeyCode.A)) {
				increasePosition(-1.5, 0);
				setLookDirect(Direction.LEFT);
				if (onGround && !getAnimation().getCurrentAnimation().equals("walk_left")) getAnimation().setCurrentAnimation("walk_left");
				moved = true;
			}
			
			if (Overlay.inventory.getItem(1) != null && KeyboardManager.isDown(KeyCode.F) && System.currentTimeMillis()-lastBomb > 1000) {
				EntityPresentBomb bomb = new EntityPresentBomb(getPosition().clone().add(getWidth()/2, getHeight()/World.TILE_SIZE), true);
				
				getWorld().markEntityToSpawn(bomb);
				
				lastBomb = System.currentTimeMillis();
			}
			
			if (KeyboardManager.isDown(KeyCode.SPACE)) {
				if (!jumping) {
					usedDoubleJump = !onGround;
					jumping = true;
					getVelocity().add(new Vector2d(0, -5));
					getAnimation().setCurrentAnimation(getLookDirect() == Direction.LEFT ? "jump_left" : "jump_right");
					moved = true;
					
					jumpUse = System.currentTimeMillis();
				} else if (Overlay.inventory.getItem(0) != null && Overlay.inventory.getItem(0).equals(Items.doubleJump) && jumping && !usedDoubleJump && System.currentTimeMillis() - jumpUse > 250) {
					usedDoubleJump = true;
					getVelocity().setY(0);
					getVelocity().add(new Vector2d(0, -5));
					moved = true;
				}
			}
		}
		
		if (!onLadder && !onGround && getAnimation().getCurrentAnimation().startsWith("walk")) {
			getAnimation().setCurrentAnimation(getLookDirect() == Direction.LEFT ? "default_left" : "default_right");
		}

		if (!onLadder && !moved && !jumping && !getAnimation().getCurrentAnimation().startsWith("default")) {
			getAnimation().setCurrentAnimation(getLookDirect() == Direction.LEFT ? "default_left" : "default_right");
		}
		
		Camera camera = getPosition().getWorld().getCamera();
		double cdx = getPosition().getX() - camera.getX()*World.TILE_SIZE; //camera-delta-x
		double cdy = getPosition().getY() - camera.getY()*World.TILE_SIZE; //camera-delta-y
		
		if (cdx > Main.WIDTH*0.75) { //1152
			camera.setX((getPosition().getX() - Main.WIDTH*0.75)/World.TILE_SIZE);
		} else if (cdx < Main.WIDTH*0.25) { //64
			camera.setX(Math.max(0, (getPosition().getX() - Main.WIDTH*0.25)/World.TILE_SIZE));
		}
		
		if (cdy > Main.HEIGHT*0.7) { //576
			camera.setY((getPosition().getY() - Main.HEIGHT*0.7)/World.TILE_SIZE);
		} else if (cdy < Main.HEIGHT*0.2 && getVelocity().getY() <= 0) { //36
			camera.setY((getPosition().getY() - Main.HEIGHT*0.2)/World.TILE_SIZE);
		}
	}
	
	public void setCanControll(boolean canControll) {
		this.canControll = canControll;
	}
	
	public boolean canControll() {
		return canControll;
	}
	
	@Override
	public void collideY(boolean bottom) {
		super.collideY(bottom);
		
		if (bottom) {
			onGround = true;
			jumping = false;
			usedDoubleJump = false;
		}
		
		canControll = true;
	}
	
	public boolean isOnGround() {
		return onGround;
	}
}
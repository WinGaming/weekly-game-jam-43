package de.wingaming.present.utils;

import de.wingaming.present.math.Vector2d;
import de.wingaming.present.world.TileType;
import de.wingaming.present.world.World;

public class Location {
	
	private World world;
	private double x, y;

	public Location(World world, double x, double y) {
		this.world = world;
		this.x = x;
		this.y = y;
	}
	
	public Location add(double dx, double dy) {
		this.x += dx;
		this.y += dy;
		
		return this;
	}
	
	public Vector2d toVector() {
		return new Vector2d(x, y);
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public double getX() {
		return x;
	}
	
	public int getTileX() {
		return (int) (x/World.TILE_SIZE);
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getY() {
		return y;
	}
	
	public int getTileY() {
		return (int) (y/World.TILE_SIZE);
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Location clone() {
		return new Location(world, x, y);
	}
	
	public TileType getTile() {
		return world.getTileTypeAt((int) x, (int) y);
	}
	
	public TileType getDecoTile() {
		return world.getDecoTileTypeAt((int) x, (int) y);
	}
}
package de.wingaming.present.world;

import java.util.ArrayList;
import java.util.List;

import de.wingaming.present.Main;
import de.wingaming.present.camera.Camera;
import de.wingaming.present.entity.Entity;
import de.wingaming.present.entity.Friend;
import de.wingaming.present.entity.Player;
import de.wingaming.present.math.Vector2d;
import de.wingaming.present.particle.Particle;
import de.wingaming.present.utils.Location;

public class World {

	public static int TILE_SIZE = 16;
	public static final int tilesInViewWidth = Main.WIDTH/TILE_SIZE+1, tilesInViewHeight = Main.HEIGHT/TILE_SIZE+1;
	
	private TileType[][] tiles = new TileType[511][511];
	private TileType[][] decoTiles = new TileType[511][511];
	private Camera camera;
	
	private Player player;
	private Friend friend;
	
	private final Vector2d gravity = new Vector2d(0, -0.15);
	
	public static final double DEBUG_FACTOR = 0.00001;
	
	private List<Entity> entities = new ArrayList<>();
	private List<Particle> particles = new ArrayList<>();
	
	private String name;
	
	private boolean paused = false;
	
	public World(String name, Player character, Friend friend) {
		this.name = name;
		this.camera = new Camera();
		this.player = character;
		this.friend = friend;
		
		spawnEntity(friend);
		spawnEntity(player);
	}
	
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void setFriend(Friend friend) {
		removeEntity(this.friend);
		this.friend = friend;
		spawnEntity(friend);
	}
	
	public void setPlayer(Player player) {
		removeEntity(this.player);
		this.player = player;
		spawnEntity(player);
	}

	public String getName() {
		return name;
	}
	
	public void update() {
		//TODO: put in if
		camera.update();
		
		if (!paused) {
			for (Entity entity : entityRemoveList) {
				removeEntity(entity);
			}
			
			for (Entity entity : entitySpawnList) {
				spawnEntity(entity);
			}
			
			entityRemoveList.clear();
			entitySpawnList.clear();
			
			for (Entity entity : entities) {
				entity.update();
			}
			
			player.update();
			friend.update();
		}
	}
	
	public void render() {
		int dx = (int) camera.getX();
		int dy = (int) camera.getY();
		
		double xDiff = (camera.getX() < 0 ? -1 : 1) * Math.abs(camera.getX()-dx)*TILE_SIZE;
		double yDiff = (camera.getY() < 0 ? -1 : 1) * Math.abs(camera.getY()-dy)*TILE_SIZE;
		
		for (int y = 0; y < tilesInViewHeight; y++) {
			for (int x = 0; x < tilesInViewWidth; x++) {
				int tileX = x+dx;
				int tileY = y+dy;
				
				if (!(tileX < 0 || tileY < 0) && !(tileX > tiles.length || tileY > tiles.length) && tiles[tileY][tileX] != null)
					Main.gc.drawImage(tiles[tileY][tileX].getTexture(), x*TILE_SIZE-xDiff, y*TILE_SIZE-yDiff, TILE_SIZE, TILE_SIZE);
			//	else
			//		PresentGame.gc.drawImage(TileType.TILE_GROUND_TOP.getTexture(), x*TILE_SIZE-xDiff, y*TILE_SIZE-yDiff, TILE_SIZE, TILE_SIZE);
			}
		}
		
		for (int y = 0; y < tilesInViewHeight; y++) {
			for (int x = 0; x < tilesInViewWidth; x++) {
				int tileX = x+dx;
				int tileY = y+dy;
				
				if (!(tileX < 0 || tileY < 0) && !(tileX > decoTiles.length || tileY > decoTiles.length) && decoTiles[tileY][tileX] != null)
					Main.gc.drawImage(decoTiles[tileY][tileX].getTexture(), x*TILE_SIZE-xDiff, y*TILE_SIZE-yDiff, TILE_SIZE, TILE_SIZE);
			}
		}
		
		for (Entity entity : entities) {
			Main.gc.drawImage(entity.getCurrentTexture(), entity.getPosition().getX()-camera.getX()*TILE_SIZE, entity.getPosition().getY()-camera.getY()*TILE_SIZE, entity.getWidth(), entity.getHeight());
		}
		
		Main.gc.drawImage(friend.getCurrentTexture(), friend.getPosition().getX()-camera.getX()*TILE_SIZE, friend.getPosition().getY()-camera.getY()*TILE_SIZE, friend.getWidth(), friend.getHeight());
		Main.gc.drawImage(player.getCurrentTexture(), player.getPosition().getX()-camera.getX()*TILE_SIZE, player.getPosition().getY()-camera.getY()*TILE_SIZE, player.getWidth(), player.getHeight());
		
		for (Particle particle : particles) {
			Main.gc.drawImage(particle.getTexture(), particle.getX(), particle.getY(), particle.getWidth(), particle.getHeight());
		}
	}
	
	public void spawnEntity(Entity entity) {
		if (!entities.contains(entity)) entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		if (entities.contains(entity)) entities.remove(entity);
	}
	
	public void addPartcle(Particle particle) {
		if (!particles.contains(particle)) particles.add(particle);
	}
	
	public void removePartcle(Particle particle) {
		if (particles.contains(particle)) particles.remove(particle);
	}
	
	public List<Entity> getEntitiesInRadius(Location loc, double radius) {
		List<Entity> result = new ArrayList<>();
		
		for (Entity entityEntry : entities) {
			if (loc.toVector().distance(entityEntry.getPosition().clone().add(entityEntry.getWidth()/2, entityEntry.getHeight()).toVector())/TILE_SIZE <= radius) {
				result.add(entityEntry);
			}
		}
		
		return result;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public void setTile(int x, int y, TileType type) {
		tiles[y][x] = type;
	}
	
	public TileType getTileTypeAt(int x, int y) {
		return y >= tiles.length || y < 0 || x >= tiles[y].length || x < 0 ? null : tiles[y][x];
	}
	
	public void setDecoTile(int x, int y, TileType type) {
		decoTiles[y][x] = type;
	}
	
	public TileType getDecoTileTypeAt(int x, int y) {
		return y >= decoTiles.length || y < 0 || x >= decoTiles[y].length || x < 0 ? null : decoTiles[y][x];
	}
	
	public Location collide(Entity entity, Location startLocation, double dx, double dy) {
		Location targetLocation = startLocation.clone().add(dx, dy);
		Location result = targetLocation.clone();
		
		Location topLeft = result.clone();
		Location bottomRight = result.clone().add(entity.getWidth(), entity.getHeight());
		
		if (dy != 0) {
			Location toLocation = startLocation.clone().add(0, dy);
			Location newLocation = startLocation.clone();
			
			int xCount = bottomRight.getTileX() - topLeft.getTileX() + 1;
			List<Location> testLocations = new ArrayList<>();
			
			if (dy > 0) {
				for (int i = 0; i < xCount; i++) {
					testLocations.add(new Location(entity.getWorld(), toLocation.getX()+i*TILE_SIZE, toLocation.getY()+entity.getHeight()));
				}
				
				if (collide(testLocations)) {
					newLocation.setY(toLocation.clone().add(0, entity.getHeight()).getTileY()*TILE_SIZE-entity.getHeight()-DEBUG_FACTOR);
					entity.collideY(true);
				} else {
					newLocation.add(0, dy);
				}
			} else if (dy < 0) {
				for (int i = 0; i < xCount; i++) {
					testLocations.add(new Location(entity.getWorld(), toLocation.getX()+i*TILE_SIZE, toLocation.getY()));
				}
				
				if (collide(testLocations)) {
					newLocation.setY(toLocation.getTileY()*TILE_SIZE+TILE_SIZE+DEBUG_FACTOR);
					entity.collideY(false);
				} else {
					newLocation.add(0, dy);
				}
			}
			
			return collide(entity, newLocation, dx, 0);
		} else if (dx != 0) {
			Location toLocation = startLocation.clone().add(dx, 0);
			Location newLocation = startLocation.clone();
			
			int yCount = bottomRight.getTileY() - topLeft.getTileY() + 1;
			List<Location> testLocations = new ArrayList<>();
			
			if (dx > 0) {
				for (int i = 0; i < yCount; i++) {
					testLocations.add(new Location(entity.getWorld(), toLocation.getX()+entity.getWidth(), toLocation.getY()+i*TILE_SIZE));
				}
				
				if (collide(testLocations)) {
					newLocation.setX(toLocation.clone().add(entity.getWidth(), 0).getTileX()*TILE_SIZE-entity.getWidth()-DEBUG_FACTOR);
					entity.collideX();
				} else {
					newLocation.add(dx, 0);
				}
			} else if (dx < 0) {
				for (int i = 0; i < yCount; i++) {
					testLocations.add(new Location(entity.getWorld(), toLocation.getX(), toLocation.getY()+i*TILE_SIZE));
				}
				
				if (collide(testLocations)) {
					newLocation.setX(toLocation.getTileX()*TILE_SIZE+TILE_SIZE+DEBUG_FACTOR);
					entity.collideX();
				} else {
					newLocation.add(dx, 0);
				}
			}
			
			return collide(entity, newLocation, 0, dy);
		} else {
			return targetLocation;
		}
	}
	
	private boolean collide(List<Location> locations) {
		for (Location location : locations) {
			int x = location.getTileX();
			int y = location.getTileY();
			
			if (getTileTypeAt(x, y) != null) {
				if (getTileTypeAt(x, y).isSolid()) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public Vector2d getGravity() {
		return gravity;
	}
	
	private List<Entity> entityRemoveList = new ArrayList<>();
	public void markEntityToRemove(Entity entity) {
		entityRemoveList.add(entity);
	}
	
	private List<Entity> entitySpawnList = new ArrayList<>();
	public void markEntityToSpawn(Entity entity) {
		entitySpawnList.add(entity);
	}
	
	public TileType[][] getTiles() {
		return tiles;
	}
	
	public TileType[][] getDecoTiles() {
		return decoTiles;
	}
	
	public Player getPlayer() {
		return player;
	}
}
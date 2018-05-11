package de.wingaming.present.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import de.wingaming.present.animation.AnimationManager;
import de.wingaming.present.entity.Entity;
import de.wingaming.present.entity.EntityBalloon;
import de.wingaming.present.entity.EntityBombSpawnerRight;
import de.wingaming.present.entity.EntityChest;
import de.wingaming.present.entity.EntityChestBomb;
import de.wingaming.present.entity.EntityEvilBalloon;
import de.wingaming.present.entity.EntityExplosion;
import de.wingaming.present.entity.EntityPresent;
import de.wingaming.present.entity.EntityPresentBomb;
import de.wingaming.present.entity.EntityType;
import de.wingaming.present.entity.Friend;
import de.wingaming.present.entity.ParticleJumpHelper;
import de.wingaming.present.entity.ParticleLadderHelper;
import de.wingaming.present.entity.Player;
import de.wingaming.present.utils.Location;
import de.wingaming.present.world.TileType;
import de.wingaming.present.world.World;

public class Loader {
	
	public static World loadWorld(String fileName) {
		try {
//			InputStream is = Main.class.getResourceAsStream("saves/"+fileName+".world");
			File file = new File("saves/"+fileName+".world");
			if (!file.exists()) {
				World w = new World(fileName, null, null);
				Location nLoc = new Location(w, 0, 0);
				
				w.setPlayer(new Player(nLoc));
				w.setFriend(new Friend(nLoc));
				
				return w;
			}

			World world = new World(fileName, null, null);
			Player player = new Player(new Location(world, 0, 0));
			Friend friend = new Friend(new Location(world, 0, 0));
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line = null;
			while((line = reader.readLine()) != null) {
				if (line.contains(">>") && !line.contains(">>>")) {
					//Blocks
					String[] data = line.split(">>");
					int dx = 0;
					int y = Integer.parseInt(data[0]);
					String blockString = data[1];
					
					String[] blocksdata = blockString.split(";");
					
					for (String blockdata : blocksdata) {
						int count;
						TileType type;
						
						if (!blockdata.contains(",")) {
							count = 1;
							type = TileType.getType(blockdata);
						} else {
							String[] blockdataData = blockdata.split(",");
							count = Integer.parseInt(blockdataData[0]);
							type = TileType.getType(blockdataData[1]);
						}
						
						for (int i = 0; i < count; i++) {
							world.setTile(i+dx, y, type);
						}

						dx += count;
					}
				} else if (line.startsWith("ent:")) {
					String entityString = line.split(":")[1];
					String[] data = entityString.split(",");
					EntityType type = EntityType.UNKNOWN;
					
					try { type = EntityType.valueOf(data[0]);
					} catch (IllegalArgumentException ex) {}
					
					double x = Double.parseDouble(data[1]);
					double y = Double.parseDouble(data[2]);
					Entity entity;
					
					switch (type) {
					case PRESENT:
						entity = new EntityPresent(new Location(world, x, y));
						break;
					case LADDER_HELPER:
						entity = new ParticleLadderHelper(new Location(world, x, y));
						break;
					case SPACE_HELPER:
						entity = new ParticleJumpHelper(new Location(world, x, y));
						break;
					case CHEST_DOUBLEJUMP:
						entity = new EntityChest(new Location(world, x, y));
						break;
					case CHEST_BOMB:
						entity = new EntityChestBomb(new Location(world, x, y));
						break;
					case PRESENT_BOMB:
						entity = new EntityPresentBomb(new Location(world, x, y), false);
						break;
					case BOMB_SPAWNER:
						entity = new EntityBombSpawnerRight(new Location(world, x, y), 16, 16);
						break;
					case EXPLOSION:
						entity = new EntityExplosion(new Location(world, x, y));
						break;
					case EVIL_BALLOON:
						entity = new EntityEvilBalloon(new Location(world, x, y));
						break;
					case BALLOON:
						entity = new EntityBalloon(new Location(world, x, y));
						break;
					default:
						entity = new Entity(AnimationManager.getAnimation("unknown"), new Location(world, x, y), type, 32, 32);
						break;
					}
					
					world.spawnEntity(entity);
				} else if (line.contains(">>>")) {
					//Blocks
					String[] data = line.split(">>>");
					int dx = 0;
					int y = Integer.parseInt(data[0]);
					String blockString = data[1];
					
					String[] blocksdata = blockString.split(";");
					
					for (String blockdata : blocksdata) {
						int count;
						TileType type;
						
						if (!blockdata.contains(",")) {
							count = 1;
							type = TileType.getType(blockdata);
						} else {
							String[] blockdataData = blockdata.split(",");
							count = Integer.parseInt(blockdataData[0]);
							type = TileType.getType(blockdataData[1]);
						}
						
						for (int i = 0; i < count; i++) {
							world.setDecoTile(i+dx, y, type);
						}

						dx += count;
					}
				} else if (line.startsWith("friend:")) {
					String[] location = line.split(":")[1].split(",");
					
					friend = new Friend(new Location(world, Double.parseDouble(location[0]), Double.parseDouble(location[1])));
				} else if (line.startsWith("player:")) {
					String[] location = line.split(":")[1].split(",");
					
					player = new Player(new Location(world, Double.parseDouble(location[0]), Double.parseDouble(location[1])));
				}
			}
			
			world.setFriend(friend);
			world.setPlayer(player);
			
			reader.close();
			
			return world;
		} catch (IOException ex) {
			ex.printStackTrace();
			World w = new World(fileName, null, null);
			Location nLoc = new Location(w, 0, 0);
			
			w.setPlayer(new Player(nLoc));
			w.setFriend(new Friend(nLoc));
			
			return w;
		}
	}
	
	public static void saveWorld(String name, World world) {
		File file = new File("saves/"+name+".world");
		
		try {
			if (!file.exists()) file.createNewFile();
			
			TileType lastType = null;
			int typeCount = 0;
			
			StringBuilder builder = new StringBuilder();
			
			for (int y = 0; y < world.getTiles().length; y++) {
				builder.append(y+">>");
				
				for (int x = 0; x < world.getTiles()[y].length; x++) {
					TileType type = world.getTileTypeAt(x, y);
					
					if (type != lastType) {
						if (typeCount == 1)
							builder.append(TileType.getSymbol(lastType)+";");
						else
							builder.append(typeCount+","+TileType.getSymbol(lastType)+";");
						
						typeCount = 1;
						lastType = type;
					} else {
						typeCount++;
					}
				}
				
				typeCount = 0;
				
				builder.append(typeCount+","+TileType.getSymbol(lastType)+"\n");
			}
			
			builder.append("\n");
			
			for (int y = 0; y < world.getDecoTiles().length; y++) {
				builder.append(y+">>>");
				
				for (int x = 0; x < world.getDecoTiles()[y].length; x++) {
					TileType type = world.getDecoTileTypeAt(x, y);
					
					if (type != lastType) {
						if (typeCount == 1)
							builder.append(TileType.getSymbol(lastType)+";");
						else
							builder.append(typeCount+","+TileType.getSymbol(lastType)+";");
						
						typeCount = 1;
						lastType = type;
					} else {
						typeCount++;
					}
				}
				
				typeCount = 0;
				
				builder.append(typeCount+","+TileType.getSymbol(lastType)+"\n");
			}
			
			builder.append("\n");
			
			for (Entity entity : world.getEntities()) {
				if (entity.getType() == EntityType.PLAYER) {
					builder.append("player:"+entity.getPosition().getX()+","+entity.getPosition().getY()+"\n");
				} else if (entity.getType() == EntityType.FRIEND) {
					builder.append("friend:"+entity.getPosition().getX()+","+entity.getPosition().getY()+"\n");
				} else {
					builder.append("ent:"+entity.getType().name()+","+entity.getPosition().getX()+","+entity.getPosition().getY()+"\n");
				}
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(builder.toString());
			writer.close();
			
			System.out.println("Saved!");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
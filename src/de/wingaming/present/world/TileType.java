package de.wingaming.present.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.wingaming.present.Main;
import de.wingaming.present.utils.ImageUtils;
import javafx.scene.image.Image;

public class TileType {
	
	private static List<TileType> tiles = new ArrayList<>();
	
	private static Map<String, TileType> types = new HashMap<>();
	
	public static final TileType TILE_SHELF = new TileType(new Image(Main.class.getResourceAsStream("res/shelf.png")));
	public static final TileType TILE_SHELF2 = new TileType(new Image(Main.class.getResourceAsStream("res/shelf2.png")));
	
	public static final TileType TILE_BG = new TileType(new Image(Main.class.getResourceAsStream("res/background.png")));
	
	public static final TileType TILE_GROUND_TOP = new TileType(new Image(Main.class.getResourceAsStream("res/ground_top.png"))).setSolid(true);
	public static final TileType TILE_GROUND_TOP_LEFT = new TileType(new Image(Main.class.getResourceAsStream("res/ground_top_left.png"))).setSolid(true);
	public static final TileType TILE_GROUND_TOP_RIGHT = new TileType(new Image(Main.class.getResourceAsStream("res/ground_top_right.png"))).setSolid(true);
	
	public static final TileType ROCK = new TileType(new Image(Main.class.getResourceAsStream("res/rock.png"))).setSolid(true);
	
	public static final TileType SNOW_TOP = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_top.png"))).setSolid(true);
	public static final TileType SNOW_BOTTOM = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_bottom.png"))).setSolid(true);
	public static final TileType SNOW_LEFT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_left.png"))).setSolid(true);
	public static final TileType SNOW_RIGHT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_right.png"))).setSolid(true);
	
	public static final TileType SNOW_TOP_LEFT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_top_left.png"))).setSolid(true);
	public static final TileType SNOW_TOP_RIGHT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_top_right.png"))).setSolid(true);
	public static final TileType SNOW_BOTTTOM_LEFT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_bottom_left.png"))).setSolid(true);
	public static final TileType SNOW_BOTTTOM_RIGHT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_bottom_right.png"))).setSolid(true);
	public static final TileType SNOW_NO_LEFT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_no_left.png"))).setSolid(true);
	public static final TileType SNOW_TOPDOWN_RIGHT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_corner_topdown_right.png"))).setSolid(true);
	public static final TileType SNOW_TOP_DOWN = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_corner_top_down.png"))).setSolid(true);
	
	public static final TileType SNOW_CORNER_TOP_LEFT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_corner_top_left.png"))).setSolid(true);
	public static final TileType SNOW_CORNER_TOP_RIGHT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_corner_top_right.png"))).setSolid(true);
	public static final TileType SNOW_CORNER_BOTTTOM_LEFT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_corner_bottom_left.png"))).setSolid(true);
	public static final TileType SNOW_CORNER_BOTTTOM_RIGHT = new TileType(new Image(Main.class.getResourceAsStream("res/snow/snow_corner_bottom_right.png"))).setSolid(true);
	
	public static final TileType UNDERGROUND = new TileType(new Image(Main.class.getResourceAsStream("res/underground.png")));
	public static final TileType UNDERGROUND_TOP = new TileType(new Image(Main.class.getResourceAsStream("res/underground_top.png")));
	
	public static final TileType LADDER = new TileType(new Image(Main.class.getResourceAsStream("res/ladder.png"))).setLadder(true);
	
	public static final TileType CHEST = new TileType(new Image(Main.class.getResourceAsStream("res/chest.png"))).setLadder(true);
	
	public static final TileType PLANKS = new TileType(new Image(Main.class.getResourceAsStream("res/planks.png"))).setSolid(true);
	public static final TileType PLANKS_BROKEN = new TileType(new Image(Main.class.getResourceAsStream("res/planks_broken.png"))).setSolid(true);
	public static final TileType PLANKS_DARK = new TileType(new Image(Main.class.getResourceAsStream("res/planks_dark.png")));
	
	public static final TileType WATER = new TileType(new Image(Main.class.getResourceAsStream("res/water.png")));
	
	static {
		registerType("!", TILE_BG);
		registerType("§", TILE_GROUND_TOP);
		registerType("%", TILE_GROUND_TOP_LEFT);
		registerType("&", TILE_GROUND_TOP_RIGHT);
		registerType("/", TILE_SHELF);
		registerType("(", TILE_SHELF2);
		
		registerType(")", ROCK);
		
		registerType("1", SNOW_TOP);
		registerType("2", SNOW_BOTTOM);
		registerType("3", SNOW_LEFT);
		registerType("4", SNOW_RIGHT);
		
		registerType("5", SNOW_TOP_LEFT);
		registerType("6", SNOW_TOP_RIGHT);
		registerType("7", SNOW_BOTTTOM_LEFT);
		registerType("8", SNOW_BOTTTOM_RIGHT);
		
		registerType("9", SNOW_CORNER_TOP_LEFT);
		registerType("0", SNOW_CORNER_TOP_RIGHT);
		registerType("z", SNOW_CORNER_BOTTTOM_LEFT);
		registerType("?", SNOW_CORNER_BOTTTOM_RIGHT);
		
		registerType("=", UNDERGROUND);
		registerType("a", UNDERGROUND_TOP);
		
		registerType("b", LADDER);
		
		registerType("c", CHEST);
		
		registerType("d", SNOW_NO_LEFT);
		registerType("e", SNOW_TOPDOWN_RIGHT);
		registerType("f", SNOW_TOP_DOWN);
		
		registerType("g", PLANKS);
		registerType("h", PLANKS_DARK);
		registerType("i", PLANKS_BROKEN);
		
		registerType("j", WATER);
	}
	
	private static int i;
	public static void nextType() {
		i++;
		
		if (i >= tiles.size()) {
			i = 0;
		}
	}
	
//	public static TileType getCurrentType() {
//		return tiles.get(i);
//	}
	
	public static void registerType(String symbol, TileType type) {
		tiles.add(type);
		
		types.put(symbol, type);
	}
	
	public static TileType getType(String symbol) {
		return types.get(symbol);
	}
	
	public static String getSymbol(TileType type) {
		for (String symbol : types.keySet()) {
			if (types.get(symbol) == type)
				return symbol;
		}
		
		return "*";
	}
	
	private boolean ladder;
	private boolean solid;
	private Image texture;
	
	private TileType(Image texture) {
		this.texture = ImageUtils.resample(texture, 2);
	}
	
	public TileType setSolid(boolean solid) {
		this.solid = solid;
		return this;
	}
	
	public TileType setLadder(boolean ladder) {
		this.ladder = ladder;
		return this;
	}
	
	public Image getTexture() {
		return texture;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public boolean isLadder() {
		return ladder;
	}
}
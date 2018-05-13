package de.wingaming.present;

import de.wingaming.present.render.RenderAble;
import de.wingaming.present.world.World;
import javafx.scene.paint.Color;

public class Game {
	
	private RenderAble ui;
	private World currentWorld;
	
	public Game() {
		
	}
	
	public void update() {
		Main.gc.setFill(Color.SKYBLUE);
		Main.gc.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		Main.gc.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		if (currentWorld != null) {
			currentWorld.update();
			currentWorld.render();
		}
		
		if (ui != null) {
			ui.update();
			ui.render();
		}
		
//		if (KeyboardManager.isDown(KeyCode.G)) {
//			KeyboardManager.release(KeyCode.G);
//			
//			TileType.nextType();
//		}
		
//		if (KeyboardManager.isDown(KeyCode.Z)) {
//			KeyboardManager.release(KeyCode.Z);
//			
//			Loader.saveWorld("t2", currentWorld);
//		}
		
//		if (KeyboardManager.isDown(KeyCode.I)) {
//			KeyboardManager.release(KeyCode.I);
//			
//			getCurrentWorld().spawnEntity(new EntityPresent(getCurrentWorld().getPlayer().getPosition()));
//		}
		
//		if (KeyboardManager.isDown(KeyCode.ESCAPE)) {
//			KeyboardManager.release(KeyCode.ESCAPE);
//			
//			currentWorld.setPaused(!currentWorld.isPaused());
//		}
		
		//if (TileType.getCurrentType() != null) Main.gc.drawImage(TileType.getCurrentType().getTexture(), Main.WIDTH - 50, Main.HEIGHT - 80, 32, 32);
	}
	
	public void setUi(RenderAble ui) {
		this.ui = ui;
	}
	
	public RenderAble getUi() {
		return ui;
	}
	
	public void setCurrentWorld(World currentWorld) {
		this.currentWorld = currentWorld;
	}
	
	public World getCurrentWorld() {
		return currentWorld;
	}
}
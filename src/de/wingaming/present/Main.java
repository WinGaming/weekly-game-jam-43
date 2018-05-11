package de.wingaming.present;

import de.wingaming.present.files.Loader;
import de.wingaming.present.gui.GameOverlay;
import de.wingaming.present.input.KeyboardManager;
import de.wingaming.present.input.Mouse;
import de.wingaming.present.render.Renderer;
import de.wingaming.present.world.TileType;
import de.wingaming.present.world.World;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static final int WIDTH = 1280, HEIGHT = 720;
	
	public static GraphicsContext gc;
	
	public static Game game;
	
	public static void main(String[] args) {
		game = new Game();
		
		game.setCurrentWorld(Loader.loadWorld("t2"));
		game.setUi(new GameOverlay());
		
		launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("UltraPresentGame");
		window.setWidth(WIDTH);
		window.setHeight(HEIGHT);
		window.setResizable(false);
		
		Group group = new Group();
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		gc = canvas.getGraphicsContext2D();
		Scene scene = new Scene(group);
		
		scene.setOnKeyPressed(e -> KeyboardManager.press(e.getCode()));
		scene.setOnKeyReleased(e -> KeyboardManager.release(e.getCode()));
		
		group.getChildren().add(canvas);
		
		window.setScene(scene);
		window.show();
		
		Renderer renderer = new Renderer();
		renderer.start();
		
//		scene.setOnMouseClicked(e ->  {
//			int tileX = (int) ((e.getX()+game.getCurrentWorld().getCamera().getX()*World.TILE_SIZE) / World.TILE_SIZE);
//			int tileY = (int) ((e.getY()+game.getCurrentWorld().getCamera().getY()*World.TILE_SIZE) / World.TILE_SIZE);
//
//			if (game.getCurrentWorld() != null)
//				if (e.getButton() == MouseButton.MIDDLE)
//					game.getCurrentWorld().setDecoTile(tileX, tileY, !KeyboardManager.isDown(KeyCode.CONTROL) ? TileType.getCurrentType() : null);
//				else
//					game.getCurrentWorld().setTile(tileX, tileY, e.getButton() == MouseButton.PRIMARY ? TileType.getCurrentType() : null);
//		});
//		
//		scene.setOnMousePressed(e -> Mouse.setPressed(true));
//		scene.setOnMouseReleased(e -> Mouse.setPressed(false));
//		
//		scene.setOnMouseDragged(e -> {
//			int tileX = (int) ((e.getX()+game.getCurrentWorld().getCamera().getX()*World.TILE_SIZE) / World.TILE_SIZE);
//			int tileY = (int) ((e.getY()+game.getCurrentWorld().getCamera().getY()*World.TILE_SIZE) / World.TILE_SIZE);
//
//			if (game.getCurrentWorld() != null)
//				if (e.getButton() == MouseButton.MIDDLE)
//					game.getCurrentWorld().setDecoTile(tileX, tileY, !KeyboardManager.isDown(KeyCode.CONTROL) ? TileType.getCurrentType() : null);
//				else
//					game.getCurrentWorld().setTile(tileX, tileY, e.getButton() == MouseButton.PRIMARY ? TileType.getCurrentType() : null);
//		});
	}
}
package de.wingaming.present.gui.overlay;

import de.wingaming.present.Main;
import de.wingaming.present.world.World;
import javafx.scene.paint.Color;

public class InfoText {
	
	private String text;
	private double x, y;
	private Color color = Color.WHITE;
	
	public InfoText(double x, double y, String text) {
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	public void render() {
		Main.gc.setFill(color);
		Main.gc.setFont(Main.gc.getFont().font(20));
		Main.gc.fillText(text, x-100 + 20 - Main.game.getCurrentWorld().getCamera().getX()*World.TILE_SIZE, y + 25 + 20/2 - Main.game.getCurrentWorld().getCamera().getY()*World.TILE_SIZE);
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public InfoText setColor(Color color) {
		this.color = color;
		return this;
	}
}
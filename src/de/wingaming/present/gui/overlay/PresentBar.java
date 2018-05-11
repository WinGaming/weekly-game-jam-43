package de.wingaming.present.gui.overlay;

import de.wingaming.present.Main;
import de.wingaming.present.utils.ImageUtils;
import javafx.scene.image.Image;

public class PresentBar {
	
	private static Image slot_empty = ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/overlay/present_empty.png")), 2);
	private static Image slot_full = ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/overlay/present_full.png")), 2);
	
	private int collected, max;
	private double x, y;
	
	public PresentBar(double x, double y, int max) {
		this.x = x;
		this.y = y;
		this.max = max;
	}
	
	public void render() {
		for (int i = 0; i < max; i++) {
			if (i < collected) {
				Main.gc.drawImage(slot_full, x + i*20, y, 16, 16);
			} else {
				Main.gc.drawImage(slot_empty, x + i*20, y, 16, 16);
			}
		}
	}
	
	public void setCollected(int collected) {
		this.collected = collected;
	}
	
	public void addPresent() {
		collected++;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getCollected() {
		return collected;
	}
	
	public int getMaxPresents() {
		return max;
	}
}

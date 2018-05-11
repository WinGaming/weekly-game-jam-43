package de.wingaming.present.gui.overlay;

import de.wingaming.present.Main;
import de.wingaming.present.item.Item;
import de.wingaming.present.utils.ImageUtils;
import javafx.scene.image.Image;

public class Inventory {
	
	private static Image slot = ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/overlay/slot.png")), 2);
	
	private Item[] items = new Item[3];
	private double slot_width = 32;
	
	private double x, y;
	
	public Inventory(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void render() {
		double width = 3*slot_width+3*5;
		double halfWidth = width/2;
		
		Main.gc.drawImage(slot, x-halfWidth, y, slot_width, slot_width);
		Main.gc.drawImage(slot, x-halfWidth+slot_width+5, y, slot_width, slot_width);
		Main.gc.drawImage(slot, x-halfWidth+2*(slot_width+5), y, slot_width, slot_width);
		
		if (items[0] != null) Main.gc.drawImage(items[0].getImage(), x-halfWidth, y, slot_width, slot_width);
		if (items[1] != null) Main.gc.drawImage(items[1].getImage(), x-halfWidth+slot_width+5, y, slot_width, slot_width);
		if (items[2] != null) Main.gc.drawImage(items[2].getImage(), x-halfWidth+2*(slot_width+5), y, slot_width, slot_width);
	}
	
	public void setItem(int slot, Item item) {
		items[slot] = item;
	}
	
	public Item getItem(int slot) {
		return items[slot];
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
package de.wingaming.present.item;

import javafx.scene.image.Image;

public class Item {
	
	private Image image;

	public Item(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void onUse() {
		
	}
	
	public void onUnselect() {
		
	}
}

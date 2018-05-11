package de.wingaming.present.item;

import de.wingaming.present.Main;
import de.wingaming.present.utils.ImageUtils;
import javafx.scene.image.Image;

public class ItemBomb extends Item {

	public ItemBomb() {
		super(ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/items/bomb.png")), 2));
	}
}
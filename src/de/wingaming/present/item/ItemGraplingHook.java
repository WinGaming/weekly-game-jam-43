package de.wingaming.present.item;

import de.wingaming.present.Main;
import de.wingaming.present.utils.ImageUtils;
import javafx.scene.image.Image;

public class ItemGraplingHook extends Item {

	public ItemGraplingHook() {
		super(ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/items/hook.png")), 2));
	}
}
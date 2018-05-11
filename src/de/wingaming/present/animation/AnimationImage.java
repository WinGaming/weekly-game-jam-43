package de.wingaming.present.animation;

import de.wingaming.present.utils.ImageUtils;
import javafx.scene.image.Image;

public class AnimationImage {
	
	private Image image;
	private long delay;
	
	public AnimationImage(Image image, long delay) {
		this.image = ImageUtils.resample(image, 2);
		this.delay = delay;
	}
	
	public Image getImage() {
		return image;
	}
	
	public long getDelay() {
		return delay;
	}
}
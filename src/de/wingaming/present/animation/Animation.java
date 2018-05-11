package de.wingaming.present.animation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;

public class Animation {
	
	private int currentIndex = 0;
	private String currentAnimation;
	private Map<String, List<AnimationImage>> animations = new HashMap<>();
	
	private long lastImageUpdate;
	
	public Animation(Map<String, List<AnimationImage>> animations) {
		this.currentAnimation = animations.containsKey("default") ? "default" : null;
		this.animations = animations;
		
		this.lastImageUpdate = System.currentTimeMillis();
	}
	
	public void update() {
		long currentTime = System.currentTimeMillis();
		
		if (currentTime - lastImageUpdate >= getCurrentAnimationImage().getDelay()) {
			currentIndex++;
			
			if (currentIndex >= getCurrentAnimationFrames()) {
				currentIndex = 0;
			}
			
			lastImageUpdate = currentTime;
		}
	}
	
	public void setCurrentAnimation(String currentAnimation) {
		currentIndex = 0;
		this.currentAnimation = currentAnimation;
	}
	
	public Image getCurrentImage() {
		return getCurrentAnimationImage().getImage();
	}
	
	public AnimationImage getCurrentAnimationImage() {
		return animations.get(currentAnimation).get(currentIndex) == null ? animations.get(currentAnimation).get(0) : animations.get(currentAnimation).get(currentIndex);
	}
	
	public int getCurrentAnimationFrames() {
		return animations.get(currentAnimation).size();
	}
	
	public String getCurrentAnimation() {
		return currentAnimation;
	}
}
package de.wingaming.present.render;

import de.wingaming.present.Main;
import javafx.animation.AnimationTimer;

public class Renderer extends AnimationTimer {
	
	@Override
	public void handle(long now) {
		Main.game.update();
	}
}

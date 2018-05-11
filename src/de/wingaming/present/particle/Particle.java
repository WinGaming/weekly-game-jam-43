package de.wingaming.present.particle;

import javafx.scene.image.Image;

public class Particle {

	private double x, y;
	private double width;
	private double height;
	private Image texture;
	
	public Particle(double x, double y, double width, double height, Image texture) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
	}

	public Image getTexture() {
		return texture;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getX() {
		return x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getY() {
		return y;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
}
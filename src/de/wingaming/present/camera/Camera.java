package de.wingaming.present.camera;

public class Camera {
	
	private double x = 0;
	private double y = 0;
	
	public Camera() {}
	public Camera(double x, double y) {this.x = x; this.y = y;}
	
	public void update() {
//		if (KeyboardManager.isDown(KeyCode.RIGHT)) {
//			increasePosition(0.125, 0);
//		}
//		
//		if (KeyboardManager.isDown(KeyCode.LEFT)) {
//			increasePosition(-0.125, 0);
//		}
//		
//		if (KeyboardManager.isDown(KeyCode.UP)) {
//			increasePosition(0, -0.125);
//		}
//		
//		if (KeyboardManager.isDown(KeyCode.DOWN)) {
//			increasePosition(0, 0.125);
//		}
	}
	
	public void increasePosition(double dx, double dy) {
		this.x += dx;
		this.y += dy;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
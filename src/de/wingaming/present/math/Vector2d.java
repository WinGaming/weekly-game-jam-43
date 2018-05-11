package de.wingaming.present.math;

public class Vector2d {
	
	private double x, y;
	
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2d add(Vector2d value) {
		this.x += value.x;
		this.y += value.y;
		
		return this;
	}
	
	public Vector2d substract(Vector2d value) {
		this.x -= value.x;
		this.y -= value.y;
		
		return this;
	}
	
	public Vector2d multiply(double value) {
		this.x *= value;
		this.y *= value;
		
		return this;
	}
	
	public double distance(Vector2d point) {
		Vector2d line = new Vector2d(Math.max(point.x, x) - Math.min(point.x, x), Math.max(point.y, y) - Math.min(point.y, y));
		
		return Math.sqrt(Math.pow(line.x, 2) + Math.pow(line.y, 2));
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

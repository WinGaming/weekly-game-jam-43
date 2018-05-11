package de.wingaming.present.input;

public class Mouse {
	
	private static boolean pressed;
	
	public static void setPressed(boolean pressed) {
		Mouse.pressed = pressed;
	}
	
	public static boolean isPressed() {
		return pressed;
	}
}
package de.wingaming.present.gui.overlay;

import de.wingaming.present.Main;
import javafx.scene.paint.Color;

public class Overlay {
	
	public static final PresentBar presentBar = new PresentBar(10, 10, 5);
	public static final Inventory inventory = new Inventory(Main.WIDTH/2, 5);
	public static final InfoText itemInfo = new InfoText(2000, 830, "In chests you can find new skills");
	public static final InfoText presentInfo = new InfoText(2500, 405, "Collect presents to reach a higher score");
	public static final InfoText presentInfo2 = new InfoText(2752.5,1302.5, "You can see your collected presents on the top left").setColor(Color.AZURE);
	public static final InfoText jumpTipInfo = new InfoText(2800, 510, "Tip: You can jump once when you fall");
	public static final InfoText itemDoublejumpInfo = new InfoText(2000, 855, "You can now jump twice\nJust hold space or jump again in air").setColor(Color.LIME);
	public static final InfoText bombInfo = new InfoText(3210, 1090, "Bombs blur your view").setColor(Color.RED);
	
	public static final InfoText dropInfo = new InfoText(3680,1415, "Press 'f' to drop a bomb").setColor(Color.LIME);
	
	public static final InfoText bombDamage = new InfoText(4020,1247.5, "Bombs can hurt enemys");
	public static final InfoText bombDelay = new InfoText(4037.5,1495, "You can only place one bomb every secound").setColor(Color.RED);
	
	public static final InfoText adMove = new InfoText(410,500, "You can move with A and D").setColor(Color.BLACK);
	public static final InfoText jumpMove = new InfoText(682.5,592.5, "And jump with ");
	
	public static final InfoText ladder = new InfoText(1632.5,705, "Press W to clib up ladders");
	
	public static final InfoText goal = new InfoText(850,400, "            Simple Story:\nYou're late for your brothers\n           birthday party").setColor(Color.DARKBLUE);
	
	public static final InfoText e1 = new InfoText(5050,3000, "Thanks for playing").setColor(Color.BLACK);
	public static final InfoText e2 = new InfoText(5350,2980, "This is my first game I \"finished\"").setColor(Color.BLACK);
	public static final InfoText e3 = new InfoText(5600,3020, "Normally I'm programming websites").setColor(Color.BLACK);
	public static final InfoText e4 = new InfoText(5800,3050, "I've learned a lot about programming games").setColor(Color.BLACK);
	public static final InfoText e5 = new InfoText(6000,3100, "Thanks for reading this and happy birthday :)");
}
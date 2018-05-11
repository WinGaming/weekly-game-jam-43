package de.wingaming.present.gui;

import de.wingaming.present.Main;
import de.wingaming.present.gui.overlay.Inventory;
import de.wingaming.present.gui.overlay.Overlay;
import de.wingaming.present.gui.overlay.PresentBar;
import de.wingaming.present.render.RenderAble;
import de.wingaming.present.utils.ImageUtils;
import de.wingaming.present.world.World;
import javafx.scene.image.Image;

public class GameOverlay implements RenderAble {
	
	private PresentBar presentBar;
	private Inventory inventory;
	
	private Image displayDamage = ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/displayDamage.png")), 2);
	private Image displayAu = ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/displayAu.png")), 2);
	private Image ad = ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/AD.png")), 4);
	private Image space = ImageUtils.resample(new Image(Main.class.getResourceAsStream("res/space.png")), 4);
	
	private static long damageStart = System.currentTimeMillis();
	private static boolean showDamage = false;
	
	private static long AuStart = System.currentTimeMillis();
	private static boolean showAu = false;
	
	public GameOverlay() {
		this.presentBar = Overlay.presentBar;
		this.inventory = Overlay.inventory;
	}
	
	public static void showDamage() {
		showDamage = true;
		damageStart = System.currentTimeMillis();
	}
	
	public static void showAu() {
		showAu = true;
		AuStart = System.currentTimeMillis();
	}
	
	@Override
	public void update() {
		if (System.currentTimeMillis() - damageStart > 2600) {
			showDamage = false;
		}
		
		if (System.currentTimeMillis() - AuStart > 2600) {
			showAu = false;
		}
	}
	
	@Override
	public void render() {
		presentBar.render();
		inventory.render();
		
		Overlay.itemInfo.render();
		Overlay.presentInfo.render();
		Overlay.presentInfo2.render();
		Overlay.jumpTipInfo.render();
		if (inventory.getItem(0) != null) Overlay.itemDoublejumpInfo.render();
		Overlay.bombInfo.render();
		if (inventory.getItem(1) != null) Overlay.dropInfo.render();
		Overlay.bombDamage.render();
		
		if (showDamage)
			Main.gc.drawImage(displayDamage, 0, 0, Main.WIDTH, Main.HEIGHT);
		
		if (showAu)
			Main.gc.drawImage(displayAu, 0, 0, Main.WIDTH, Main.HEIGHT);
		
		Main.gc.drawImage(ad, 400-Main.game.getCurrentWorld().getCamera().getX()*World.TILE_SIZE, 450-Main.game.getCurrentWorld().getCamera().getY()*World.TILE_SIZE, 29*3.5, 19*3.5);
		Main.gc.drawImage(space, 740-Main.game.getCurrentWorld().getCamera().getX()*World.TILE_SIZE, 605-Main.game.getCurrentWorld().getCamera().getY()*World.TILE_SIZE, 23*3.5, 9*3.5);
		Overlay.adMove.render();
		Overlay.jumpMove.render();
		
		Overlay.ladder.render();
		Overlay.bombDelay.render();
		
		Overlay.goal.render();
		
		Overlay.e1.render();
		Overlay.e2.render();
		Overlay.e3.render();
		Overlay.e4.render();
		Overlay.e5.render();
	}
}
package item;

import entity.Entity;
import main.GamePanel;

public class Heart extends Entity{
	GamePanel gameP;
	//public BufferedImage image1, image2, image3;
	public Heart(GamePanel gameP) {
		super(gameP);
		this.gameP = gameP;
		name = "Heart";
		value = 2;
		down1 = setUpImages("/items/heart_full");
		heart_full = setUpImages("/items/heart_full");
		heart_half = setUpImages("/items/heart_half");
		heart_blank = setUpImages("/items/heart_blank");
		collison = true;
	}
	
	public void use() {
		gameP.player.currentLife+= value;
	}
}

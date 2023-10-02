package item;

import entity.Entity;
import main.GamePanel;

public class Heart extends Entity{
	//public BufferedImage image1, image2, image3;
	public Heart(GamePanel gameP) {
		super(gameP);
		name = "Heart";
		heart_full = setUpImages("/items/heart_full");
		heart_half = setUpImages("/items/heart_half");
		heart_blank = setUpImages("/items/heart_blank");
		collison = true;
	}
}

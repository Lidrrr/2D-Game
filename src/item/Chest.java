package item;

import entity.Entity;
import main.GamePanel;

public class Chest extends Entity{
	public Chest(GamePanel gameP) {
		super(gameP);
		name = "Chest";
		down1 = setUpImages("/items/chest");
	}
}

package item;

import entity.Entity;
import main.GamePanel;

public class Sword extends Entity{

	public Sword(GamePanel gameP) {
		super(gameP);
		name = "sword";
		down1 = setUpImages("/items/sword");
		attackValue = 1;
	}
	
}

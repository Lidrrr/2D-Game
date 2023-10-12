package item;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class Shield extends Entity{

	public Shield(GamePanel gameP) {
		super(gameP);
		name = "Shield";
		down1 = setUpImages("/items/shield");
		defenseValue = 1;
		isShield = true;
		description = "a normal shield";
	}

}

package item;

import entity.Entity;
import main.GamePanel;

public class Shield_Blue extends Entity{
	public Shield_Blue(GamePanel gameP) {
		super(gameP);
		name = "Blue Shield";
		down1 = setUpImages("/items/shield_blue");
		defenseValue = 1;
		isShield = true;
		description = "a blue shield";
	}
}

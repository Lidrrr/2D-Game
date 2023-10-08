package item;

import entity.Entity;
import main.GamePanel;

public class Shield extends Entity{

	public Shield(GamePanel gameP) {
		super(gameP);
		name = "shield";
		down1 = setUpImages("/items/shield");
		defenseValue = 1;
	}

}

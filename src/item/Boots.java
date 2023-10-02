package item;

import entity.Entity;
import main.GamePanel;

public class Boots extends Entity{
	public Boots(GamePanel gameP) {
		super(gameP);
		name = "Boots";
		down1 = setUpImages("/items/boots");
	}
}

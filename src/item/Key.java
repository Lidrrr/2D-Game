package item;

import entity.Entity;
import main.GamePanel;

public class Key extends Entity{
	
	public Key(GamePanel gameP) {
		super(gameP);
		name = "Key";
		down1 = setUpImages("/items/key");
		
	}

}

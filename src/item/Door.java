package item;

import entity.Entity;
import main.GamePanel;

public class Door extends Entity{
	public Door(GamePanel gameP) {
		super(gameP);
		name = "Door";
		down1 = setUpImages("/items/door");
		collison = true;
		
	}
}

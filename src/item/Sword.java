package item;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class Sword extends Entity{

	public Sword(GamePanel gameP) {
		super(gameP);
		name = "Sword";
		down1 = setUpImages("/items/sword");
		attackValue = 1;
		attackArea =  new Rectangle(0, 0, 24, 2);
		isWeapon = true;
		description = "a normal sword";
	}
	
}

package item;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class Axe extends Entity{
	public Axe(GamePanel gameP) {
		super(gameP);
		name = "Axe";
		down1 = setUpImages("/items/axe");
		defenseValue = 2;
		attackArea =  new Rectangle(0, 0, 30, 30);
		isWeapon = true;
		description = "a normal axe";
	}
}

package item;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class Potion extends Entity{
	GamePanel gameP;
	public Potion(GamePanel gameP) {
		super(gameP);
		this.gameP = gameP;
		value = 2;
		isConsumable = true;
		name = "Potion";
		down1 = setUpImages("/items/potion");
		description = "recover your life by 4";
	}
	
	public void use() {
		gameP.player.currentLife+=value;
	}
}

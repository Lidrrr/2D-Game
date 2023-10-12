package item;

import java.awt.Rectangle;

import entity.Entity;
import main.GamePanel;

public class Potion extends Entity{
	public final int recoverVal = 4;
	GamePanel gameP;
	public Potion(GamePanel gameP) {
		super(gameP);
		this.gameP = gameP;
		isConsumable = true;
		name = "Potion";
		down1 = setUpImages("/items/potion");
		description = "recover your life by 4";
	}
	
	public void use() {
		gameP.player.currentLife+=4;
		if(gameP.player.currentLife > gameP.player.maxLife)gameP.player.currentLife = gameP.player.maxLife;
	}
}

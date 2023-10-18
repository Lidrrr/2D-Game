package item;

import entity.Entity;
import main.GamePanel;

public class Mana extends Entity{

	public Mana(GamePanel gameP) {
		super(gameP);
		name = "Mana";
		mana_blank = setUpImages("/items/mana_blank");
		mana_full = setUpImages("/items/mana_full");
	}

}

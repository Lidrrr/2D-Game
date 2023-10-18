package item;

import entity.Projectiles;
import main.GamePanel;

public class Fireball extends Projectiles{

	GamePanel gameP;
	public Fireball(GamePanel gameP) {
		super(gameP);
		this.gameP = gameP;
		name = "Fireball";
		speed = 8;
		maxLife = 40;
		currentLife = maxLife;
		attack = 1;
		useCost = 1;
		living = false;
		getImage();
	}
	
	public void getImage() {
		up1 = setUpImages("/projectiles/fireball_up_1");
		up2 = setUpImages("/projectiles/fireball_up_2");
		down1 = setUpImages("/projectiles/fireball_down_1");
		down2 = setUpImages("/projectiles/fireball_down_2");
		left1 = setUpImages("/projectiles/fireball_left_1");
		left2 = setUpImages("/projectiles/fireball_left_2");
		right1 = setUpImages("/projectiles/fireball_right_1");
		right2 = setUpImages("/projectiles/fireball_right_2");
	}
}

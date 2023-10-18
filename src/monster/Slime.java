package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class Slime extends Entity{
	GamePanel gameP;
	public Slime(GamePanel gameP) {
		
		super(gameP);
		this.gameP = gameP;
		name = "slime";
		speed = 1;
		maxLife = 4;
		currentLife = maxLife;
		attack = 2;
		defense = 0;
		exp = 3;
		recP.x = 3;
		recP.y = 10;
		recP.width = 42;
		recP.height = 30;
		recX = recP.x;
		recY = recP.y;
		
		setImages();
	}
	
	private void setImages() {
		up2 = setUpImages("/monsters/slime1");
		up3 = setUpImages("/monsters/slime2");
		down2 = setUpImages("/monsters/slime1");
		down3 = setUpImages("/monsters/slime2");
		left1 = setUpImages("/monsters/slime1");
		left2 = setUpImages("/monsters/slime2");
		right1 = setUpImages("/monsters/slime1");
		right2 = setUpImages("/monsters/slime2");
	}

	// when player hits it, move to the direction as player faces(move away)
	public void damageReact() {
		entityCounter = 0;
		direction = gameP.player.direction;
	}
	
	// slime behavior
	public void update() {		
		moveCounter++;
		
		if(moveCounter == 100) {
			Random random = new Random();
			int i = random.nextInt(100);	
			if(i<= 25) {
				direction = "up";
			}
			else if (i > 25 && i <= 50) {
				direction = "down";
			}
			else if(i > 50 && i <= 75){
				direction = "left";
			}
			else {
				direction = "right";
			}			
			moveCounter = 0;
		}
		move();
		entityCounter++;
		animate();
		
		if(invincible) {
			invinvibleCount++;
			if(invinvibleCount > 40) {
				invincible = false;
				invinvibleCount = 0;
			}
		}
	}
}

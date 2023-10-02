package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class Slime extends Entity{

	public Slime(GamePanel gameP) {
		super(gameP);
		name = "slime";
		speed = 1;
		maxLife = 4;
		currentLife = maxLife;
		
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
	}
}

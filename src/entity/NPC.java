package entity;

import java.util.Random;

import main.GamePanel;

public class NPC extends Entity{
	
	public NPC(GamePanel gameP) {
		super(gameP);
		name = "npc";
		direction = "down";
		speed = 1;
		getNPCImage();
		setDialogue();
	}
	
	// read in NPC images
	public void getNPCImage() {
		up1 = setUpImages("/npc/up1");
		up2 = setUpImages("/npc/up2");
		up3 = setUpImages("/npc/up3");
		down1 = setUpImages("/npc/down1");
		down2 = setUpImages("/npc/down2");
		down3 = setUpImages("/npc/down3");
		left1 = setUpImages("/npc/left1");
		left2 = setUpImages("/npc/left2");
		right1 = setUpImages("/npc/right1");
		right2 = setUpImages("/npc/right2");
	}
	
	// NPC behavior
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
	
	// set dialogues
	public void setDialogue() {
		dialogues[0] = "Explore the surroundings, \ncomplete quests, and grow stronger.";
		dialogues[1] = "That's the path of a true adventurer.";
		dialogues[2] = "Remember my words, \nevery choice you make shapes your destiny. ";
		dialogues[3] = "Make wise decisions, \nand you'll become a legendary hero!";
		
		switch (gameP.player.direction){
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	
	// set dialogues to be visible to ui class
	public void transfer() {
		gameP.ui.diaString = dialogues[dialogueIndex];
		//dialogueIndex++;
	}
	
	
	
	
	
	
}

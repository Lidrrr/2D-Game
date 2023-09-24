package entity;

import main.GamePanel;

public class NPC extends Entity{
	public NPC(GamePanel gameP) {
		super(gameP);
		
		direction = "down";
		speed = 1;
		getNPCImage();
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
}

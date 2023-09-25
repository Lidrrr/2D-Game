package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.ExtraTools;
import main.GamePanel;
import main.KeyController;

public class Player extends Entity{
	public final int screenX, screenY;
	KeyController keyC;
	
	public Player(GamePanel gameP, KeyController keyC) {
		
		super(gameP);
		this.keyC = keyC;
		
		// fix player at center
		screenX = gameP.screenWidth / 2 - (gameP.finalTileSize/2);
		screenY = gameP.screenHeight / 2 - (gameP.finalTileSize/2);
		recP = new Rectangle(16, 20, 26, 26);
		recX = recP.x;
		recY = recP.y;
		setDefaultPlayer();
		getPlayerImage();
	}

	public void setDefaultPlayer() {
		worldX = 23 * gameP.finalTileSize;
		worldY = 21 * gameP.finalTileSize;
		speed = 4;
		direction = "down";
	}
	
	// read in player images
	public void getPlayerImage() {
		up1 = setUpImages("/player/up1");
		up2 = setUpImages("/player/up2");
		up3 = setUpImages("/player/up3");
		down1 = setUpImages("/player/down1");
		down2 = setUpImages("/player/down2");
		down3 = setUpImages("/player/down3");
		left1 = setUpImages("/player/left1");
		left2 = setUpImages("/player/left2");
		right1 = setUpImages("/player/right1");
		right2 = setUpImages("/player/right2");
	}
	
	
	
	// update with the key pressed
	public void update() {
		if(keyC.upPress == true) {
			direction = "up";
			move();
			entityCounter++;
		}
		else if(keyC.downPress == true) {
			direction = "down";
			move();
			entityCounter++;
		}
		else if(keyC.leftPress == true) {
			direction = "left";
			move();
			entityCounter++;
		}
		else if(keyC.rightPress == true) {
			direction = "right";
			move();
			entityCounter++;
		}
		
		animate();
	}
	
	// movement logic with collision check
	public void move() {
		isCollison = false;
		gameP.collisonC.checkCollison(this);
		int itemIndex = gameP.collisonC.checkItem(this, true);
		pickUpItems(itemIndex);
		
		// check NPC collision
		int npcIndex = gameP.collisonC.checkEntity(this, gameP.itemC.npcs);
		NPCInteraction(npcIndex);
		
		if(isCollison == false) {
			if(direction == "up") {
				worldY -= speed;
			}
			else if(direction == "down") {
				worldY += speed;
			}
			else if(direction == "left") {
				worldX -= speed;
			}
			else if(direction == "right") {
				worldX += speed;
			}
		}
	}
	
	// deal with interactions with NPC
	public void NPCInteraction(int index) {
		if(recP.intersects(gameP.itemC.npcs[0].recP)) {
			if(gameP.keyController.spacePress) {
				gameP.gameState = gameP.dialogue;
				gameP.itemC.npcs[0].setDialogue();
			}
		}
		
	}
	
	// deal with pickups
	public void pickUpItems(int index) {
		
	}
	
	// draw 'animated' player with according directions
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(direction) {
		case "up":
			if(entityNum == 0) {
				image = up1;
			}
			else if(entityNum == 1) {
				image = up2;
			}
			else if(entityNum == 2) {
				image = up3;
			}
			break;
		case "down":
			if(entityNum == 0) {
				image = down1;
			}
			else if(entityNum == 1) {
				image = down2;
			}
			else if(entityNum == 2) {
				image = down3;
			}
			break;
		case "left":
			if(entityNum == 0 || entityNum == 2) {
				image = left1;
			}
			else if(entityNum == 1) {
				image = left2;
			}
			
			break;
		case "right":
			if(entityNum == 0 || entityNum == 2) {
				image = right1;
			}
			else if(entityNum == 1) {
				image = right2;
			}
			
			break;
		}
		
		g2.drawImage(image, screenX, screenY, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

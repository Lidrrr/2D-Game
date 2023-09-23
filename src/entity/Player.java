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
	public int keyOwned = 0;
	GamePanel gameP;
	KeyController keyC;
	
	public Player(GamePanel gameP, KeyController keyC) {
		this.gameP = gameP;
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
		up1 = setUpPlayer("up1");
		up2 = setUpPlayer("up2");
		up3 = setUpPlayer("up3");
		down1 = setUpPlayer("down1");
		down2 = setUpPlayer("down2");
		down3 = setUpPlayer("down3");
		left1 = setUpPlayer("left1");
		left2 = setUpPlayer("left2");
		right1 = setUpPlayer("right1");
		right2 = setUpPlayer("right2");
	}
	
	public BufferedImage setUpPlayer(String imageString) {
		ExtraTools eTools = new ExtraTools();
		BufferedImage scaledImage = null;
		try {
			scaledImage = ImageIO.read(getClass().getResourceAsStream("/player/" + imageString + ".png"));
			scaledImage = eTools.scaIeImage(scaledImage, gameP.finalTileSize, gameP.finalTileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scaledImage;
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
	
	// for animation logic
	public void animate() {
		if(entityCounter == 0) {
			entityNum = 0;
		}
		if(entityCounter > 12) {
			if(entityNum == 0) {
				entityNum = 1;
			}
			else if(entityNum == 1) {
				entityNum = 2;
			}
			else if(entityNum == 2) {
				entityNum = 1;
			}
			entityCounter = 0;
		}
	}
	
	// movement logic with collision check
	public void move() {
		isCollison = false;
		gameP.collisonC.checkCollison(this);
		int itemIndex = gameP.collisonC.checkItem(this, true);
		pickUpItems(itemIndex);
		
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
	
	// deal with pickups
	public void pickUpItems(int index) {
		if(index != 999) {
			if(gameP.itemC.items[index].name == "Key") {
				gameP.playSound(1);
				keyOwned++;
				gameP.itemC.items[index] = null;
			}
			else if(gameP.itemC.items[index].name == "Door") {
				if(keyOwned > 0) {
					gameP.playSound(3);
					keyOwned--;
					gameP.itemC.items[index] = null;
				}
			}
			else if(gameP.itemC.items[index].name == "Boots") {
				gameP.playSound(2);
				speed+=1;
				gameP.itemC.items[index] = null;
			}
			else if(gameP.itemC.items[index].name == "Chest") {
				gameP.gameFinished = true;
				gameP.stopMusic();
				gameP.playSound(4);
				gameP.itemC.items[index] = null;
			}
		}
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

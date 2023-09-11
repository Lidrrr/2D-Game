package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyController;

public class Player extends Entity{
	public final int screenX, screenY;
	
	GamePanel gameP;
	KeyController keyC;
	
	public Player(GamePanel gameP, KeyController keyC) {
		this.gameP = gameP;
		this.keyC = keyC;
		
		// fix player at center
		screenX = gameP.screenWidth / 2 - (gameP.finalTileSize/2);
		screenY = gameP.screenHeight / 2 - (gameP.finalTileSize/2);
		recP = new Rectangle(16, 20, 26, 26);
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
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/up3.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/down3.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
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
		
		g2.drawImage(image, screenX, screenY, gameP.finalTileSize, gameP.finalTileSize, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

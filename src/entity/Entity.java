package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.ExtraTools;
import main.GamePanel;

public class Entity {
	
	public int worldX, worldY, speed;
	GamePanel gameP;
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public String direction;
	
	// to create 'animation'
	public int entityCounter = 0;
	public int entityNum = 1;
	
	public Rectangle recP = new Rectangle(0, 0, 48, 48);
	public int recX, recY;
	public boolean isCollison;
	
	public Entity(GamePanel gameP) {
		this.gameP = gameP;
	}
	
	public BufferedImage setUpImages(String imageString) {
		ExtraTools eTools = new ExtraTools();
		BufferedImage scaledImage = null;
		try {
			scaledImage = ImageIO.read(getClass().getResourceAsStream(imageString + ".png"));
			scaledImage = eTools.scaIeImage(scaledImage, gameP.finalTileSize, gameP.finalTileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scaledImage;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gameP.player.worldX + gameP.player.screenX;
		int screenY = worldY - gameP.player.worldY + gameP.player.screenY;
		
		// not draw obj outside the screen
		if(worldX + gameP.finalTileSize > gameP.player.worldX - gameP.player.screenX &&
		   worldX - gameP.finalTileSize < gameP.player.worldX + gameP.player.screenX	&&
		   worldY + gameP.finalTileSize > gameP.player.worldY - gameP.player.screenY &&
		   worldY - gameP.finalTileSize < gameP.player.worldY + gameP.player.screenY) {
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
}

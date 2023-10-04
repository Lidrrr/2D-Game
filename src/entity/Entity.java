package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.ExtraTools;
import main.GamePanel;

public class Entity {
	
	public int worldX, worldY, speed;
	GamePanel gameP;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public String direction = "static";
	
	// dialogues
	public int dialogueIndex = 0;
	public String dialogues[] = new String[20];
	
	// to create 'animation'
	public int entityCounter = 0;
	public int entityNum = 1;
	
	// for NPC move
	public int moveCounter = 0;
	
	// attack
	public boolean attacking = false;
	public boolean invincible = false;
	public int invinvibleCount = 0;
	
	// for items
	public BufferedImage image, heart_full, heart_half, heart_blank;
	public String name;
	public boolean collison = false;
	
	// life
	public int maxLife, currentLife;
	
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
	
	public BufferedImage setUpAttackImages(String imageString, int width, int height) {
		ExtraTools eTools = new ExtraTools();
		BufferedImage scaledImage = null;
		try {
			scaledImage = ImageIO.read(getClass().getResourceAsStream(imageString + ".png"));
			scaledImage = eTools.scaIeImage(scaledImage, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scaledImage;
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gameP.player.worldX + gameP.player.screenX;
		int screenY = worldY - gameP.player.worldY + gameP.player.screenY;
		
		// not draw item outside the screen
		if(worldX + gameP.finalTileSize > gameP.player.worldX - gameP.player.screenX &&
		   worldX - gameP.finalTileSize < gameP.player.worldX + gameP.player.screenX	&&
		   worldY + gameP.finalTileSize > gameP.player.worldY - gameP.player.screenY &&
		   worldY - gameP.finalTileSize < gameP.player.worldY + gameP.player.screenY) {
			switch(direction) {
			case "up":
				if(entityNum == 1) {
					image = up2;
				}
				else if(entityNum == 2) {
					image = up3;
				}
				break;
			case "down":
				if(entityNum == 1) {
					image = down2;
				}
				else if(entityNum == 2) {
					image = down3;
				}
				break;
			case "left":
				if(entityNum == 1) {
					image = left1;
				}
				else if(entityNum == 2) {
					image = left2;
				}
				
				break;
			case "right":
				if(entityNum == 1) {
					image = right1;
				}
				else if(entityNum == 2) {
					image = right2;
				}
				
				break;
			case "static":
				image = down1;
				break;
			}
			if(name == "player" && invincible) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			}
			g2.drawImage(image, screenX, screenY, gameP.finalTileSize, gameP.finalTileSize, null);
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}
	// for animation logic
	public void animate() {
		//if(entityCounter == 0) {
			//entityNum = 0;
		//}
		if(entityCounter > 12) {
			if(entityNum == 1) {
				entityNum = 2;
			}
			else if(entityNum == 2) {
				entityNum = 1;
			}
			//else if(entityNum == 2) {
				//entityNum = 1;
			//}
			entityCounter = 0;
		}
	}
	
	public void update() {}
	public void transfer() {}
	public void move() {
		
		isCollison = false;
		gameP.collisonC.checkCollison(this);
		gameP.collisonC.checkItem(this, isCollison);
		gameP.collisonC.checkEntity(this, gameP.itemC.npcs);
		gameP.collisonC.checkEntity(this, gameP.itemC.monsters);
		boolean touched = gameP.collisonC.NPCTouchPlayer(this);
		
		if(this.name == "slime" && touched) {
			if(!gameP.player.invincible) {
				gameP.player.currentLife--;
				gameP.player.invincible = true;
			}
		}
		
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
	
	
	
	
	
	
	
	
	
}

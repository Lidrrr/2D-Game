package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
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
	
	//player status
	public int level, strength, dexterity, exp, nextLvlExp, coin, attack, defense;
	public Entity currentWeapon;
	public Entity currentShield;
	public boolean isWeapon = false;
	public boolean isShield = false;
	public boolean isConsumable = false;
	
	// weapon status
	public int attackValue;
	public int defenseValue;
	// attack
	public boolean attacking = false;
	public boolean invincible = false;
	public int invinvibleCount = 0;
	
	// for items
	public BufferedImage image, heart_full, heart_half, heart_blank, mana_full, mana_blank;
	public String name, description;
	public boolean collison = false;
	
	// life
	public int maxLife, currentLife, currentMana, maxMana;
	public int useCost;
	public Projectiles projectile;
	public int receivedDamage;
	//health bar
	public boolean hpBar = false;
	int hpBarCounter = 0;
	// dying animation
	public boolean living = true;
	public boolean dying = false;
	int dyingCounter = 0;
	
	public Rectangle recP = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0 ,0, 0);
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
			
			//if(shootCounter < 30)shootCounter++;
			
			// health bar
			if(name == "slime" && hpBar) {
				hpBarCounter++;
				double hpScale = (double)(gameP.finalTileSize-10)/maxLife;
				double hpNum = hpScale*currentLife;
				
				g2.setColor(new Color(35,35,35));
				g2.fillRect(screenX+4, screenY-5, gameP.finalTileSize-8, 8);
				
				g2.setColor(new Color(255,0,30));
				g2.fillRect(screenX+5, screenY-4, (int)hpNum, 6);
				
				if(hpBarCounter > 360) {
					hpBarCounter = 0;
					hpBar = false;
				}
			}
			
			
			if(name == "slime" && invincible) {
				hpBar = true;
				hpBarCounter = 0;
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
				if(receivedDamage!=0) {
					//receivedDamage=0;
					//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
				}
				
			}
			if(dying) {
				dyingAnimation(g2);
			}
			
			g2.drawImage(image, screenX, screenY, gameP.finalTileSize, gameP.finalTileSize, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}
	
	// dying animation
	public void dyingAnimation(Graphics2D g2) {
		if (dyingCounter >= 35) {
            dying = false;
            living = false;
            dyingCounter = 0;
        } else {
            float alpha = (dyingCounter % 10 < 5) ? 1.0f : 0.0f;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            dyingCounter++;
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
	void damagePlayer(int attack) {
		if(!gameP.player.invincible) {
			receivedDamage = attack - gameP.player.defense;
			if(receivedDamage<0)receivedDamage=0;
			gameP.player.currentLife-=receivedDamage;
			if(receivedDamage>0)gameP.player.invincible = true;
		}
	}
	public void use() {}
	public void update() {}
	public void transfer() {}
	public void damageReact() {}
	public void move() {
		
		isCollison = false;
		gameP.collisonC.checkCollison(this);
		gameP.collisonC.checkItem(this, isCollison);
		gameP.collisonC.checkEntity(this, gameP.itemC.npcs);
		gameP.collisonC.checkEntity(this, gameP.itemC.monsters);
		boolean touched = gameP.collisonC.NPCTouchPlayer(this);
		
		if(this.name == "slime" && touched) {
			damagePlayer(attack);
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

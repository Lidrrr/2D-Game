package entity;

import java.awt.AlphaComposite;
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
	int attackCount = 0, attackNum = 0;
	KeyController keyC;
	
	public Player(GamePanel gameP, KeyController keyC) {
		
		super(gameP);
		this.keyC = keyC;
		name = "player";
		// fix player at center
		screenX = gameP.screenWidth / 2 - (gameP.finalTileSize/2);
		screenY = gameP.screenHeight / 2 - (gameP.finalTileSize/2);
		recP = new Rectangle(16, 16, 16, 16);
		recX = recP.x;
		recY = recP.y;
		setDefaultPlayer();
		getPlayerImage();
		getAttackImage();
	}

	public void setDefaultPlayer() {
		worldX = 23 * gameP.finalTileSize;
		worldY = 21 * gameP.finalTileSize;
		speed = 4;
		direction = "static";
		maxLife = 6;
		currentLife = maxLife;
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
	
	// read in player attacking images
	private void getAttackImage() {
		attackUp1 = setUpAttackImages("/player/attack_up_1", gameP.finalTileSize, gameP.finalTileSize*2);
		attackUp2 = setUpAttackImages("/player/attack_up_2", gameP.finalTileSize, gameP.finalTileSize*2);
		attackDown1 = setUpAttackImages("/player/attack_down_1", gameP.finalTileSize, gameP.finalTileSize*2);
		attackDown2 = setUpAttackImages("/player/attack_down_2", gameP.finalTileSize, gameP.finalTileSize*2);
		attackLeft1 = setUpAttackImages("/player/attack_left_1", gameP.finalTileSize*2, gameP.finalTileSize);
		attackLeft2 = setUpAttackImages("/player/attack_left_2", gameP.finalTileSize*2, gameP.finalTileSize);
		attackRight1 = setUpAttackImages("/player/attack_right_1", gameP.finalTileSize*2, gameP.finalTileSize);
		attackRight2 = setUpAttackImages("/player/attack_right_2", gameP.finalTileSize*2, gameP.finalTileSize);
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
		else if(keyC.spacePress == true) {
			move();
			entityCounter++;
		}
		
		animate();
		if(invincible) {
			invinvibleCount++;
			if(invinvibleCount > 60) {
				invincible = false;
				invinvibleCount = 0;
			}
		}
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
		
		// check monster collision
		int monsterIndex = gameP.collisonC.checkEntity(this, gameP.itemC.monsters);
		MonsterInteration(monsterIndex);
		
		if(gameP.keyController.attackPress) {
			playerAttack();
		}
		// check damage
		gameP.eHandler.checkEvent();
		
		
		
		if(isCollison == false && keyC.spacePress == false) {
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
		gameP.keyController.spacePress = false;
	}
	
	
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int tempX = screenX, tempY = screenY;
		
		// not draw item outside the screen
		if(worldX + gameP.finalTileSize > gameP.player.worldX - gameP.player.screenX &&
		   worldX - gameP.finalTileSize < gameP.player.worldX + gameP.player.screenX	&&
		   worldY + gameP.finalTileSize > gameP.player.worldY - gameP.player.screenY &&
		   worldY - gameP.finalTileSize < gameP.player.worldY + gameP.player.screenY) {
			switch(direction) {
			case "up":
				if(gameP.keyController.attackPress) {
					tempY -= gameP.finalTileSize;
					if(attackNum == 1) {
						image = attackUp2;
					}
					else if(attackNum == 2) {
						image = attackUp2;
					}
				}
				else {
					if(entityNum == 1) {
						image = up2;
					}
					else if(entityNum == 2) {
						image = up3;
					}
				}
				
				break;
			case "down":
				if(gameP.keyController.attackPress) {
					if(attackNum == 1) {
						image = attackDown1;
					}
					else if(attackNum == 2) {
						image = attackDown2;
					}
				}
				else {
					if(entityNum == 1) {
						image = down2;
					}
					else if(entityNum == 2) {
						image = down3;
					}
				}
				break;
			case "left":
				if(gameP.keyController.attackPress) {
					tempX -= gameP.finalTileSize;
					if(attackNum == 1) {
						image = attackLeft1;
					}
					else if(attackNum == 2) {
						image = attackLeft2;
					}
				}
				else {
					if(entityNum == 1) {
						image = left1;
					}
					else if(entityNum == 2) {
						image = left2;
					}
				}
				break;
			case "right":
				if(gameP.keyController.attackPress) {
					if(attackNum == 1) {
						image = attackRight1;
					}
					else if(attackNum == 2) {
						image = attackRight2;
					}
				}
				else {
					if(entityNum == 1) {
						image = right1;
					}
					else if(entityNum == 2) {
						image = right2;
					}
				}
				break;
			case "static":
				image = down1;
				break;
			}
			if(name == "player" && invincible) {
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
			}
			g2.drawImage(image, tempX, tempY, null);
			
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}
	
	// attacking
	public void playerAttack() {
		attackCount++;
		if(attackCount <= 24) {
			attackNum = 2;
		}
		else if (attackCount > 24 && attackCount < 28) {
			attackNum = 1;
			
		}
		else {
			attackCount = 0;
		}
	}
	
	// deal with interactions with NPC
	private void MonsterInteration(int index) {
		if(index != 999) {
			if(!invincible) {
				currentLife--;
				invincible = true;
			}
			
		}
	}
	
	// deal with interactions with NPC
	public void NPCInteraction(int index) {
		if(index != 999) {
			if(gameP.keyController.spacePress) {
				gameP.gameState = gameP.dialogue;
				gameP.itemC.npcs[0].transfer();
			}
		}
		
	}
	
	// deal with pickups
	public void pickUpItems(int index) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

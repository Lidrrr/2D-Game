package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import item.Fireball;
import item.Key;
import item.Shield;
import item.Sword;
import main.ExtraTools;
import main.GamePanel;
import main.KeyController;

public class Player extends Entity{
	public final int screenX, screenY;
	int attackCount = 0, attackNum = 0;
	KeyController keyC;
	boolean attacking = false;
	public ArrayList<Entity> bagItems = new ArrayList<>();
	public final int bagSize = 20;
	int shootCounter = 0;
	int manaCounter = 0;
	
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
		setBagItems();
	}

	public void setBagItems() {
		bagItems.add(currentShield);
		bagItems.add(currentWeapon);
		bagItems.add(new Key(gameP));
		
	}
	
	// default values
	public void setDefaultPlayer() {
		worldX = 23 * gameP.finalTileSize;
		worldY = 21 * gameP.finalTileSize;
		speed = 4;
		direction = "static";
		maxLife = 6;
		maxMana = 4;
		currentMana = maxMana;
		currentLife = maxLife;
		level = 1;
		strength = 1;
		dexterity = 1;
		exp = 0;
		nextLvlExp = 5;
		coin = 0;
		currentWeapon = new Sword(gameP);
		currentShield = new Shield(gameP);
		attack = getAttackVal();
		defense = getDefenseVal();
		projectile = new Fireball(gameP);
	}
	
	// higher strength means higher attack value
	public int getAttackVal() {
		attackArea = currentWeapon.attackArea;
		return strength * currentWeapon.attackValue;
	}
	
	// higher dexterity means higher defense value
	public int getDefenseVal() {
		return dexterity * currentShield.defenseValue;
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
		if(currentWeapon.name == "Sword") {
			attackUp1 = setUpAttackImages("/player/attack_up_1", gameP.finalTileSize, gameP.finalTileSize*2);
			attackUp2 = setUpAttackImages("/player/attack_up_2", gameP.finalTileSize, gameP.finalTileSize*2);
			attackDown1 = setUpAttackImages("/player/attack_down_1", gameP.finalTileSize, gameP.finalTileSize*2);
			attackDown2 = setUpAttackImages("/player/attack_down_2", gameP.finalTileSize, gameP.finalTileSize*2);
			attackLeft1 = setUpAttackImages("/player/attack_left_1", gameP.finalTileSize*2, gameP.finalTileSize);
			attackLeft2 = setUpAttackImages("/player/attack_left_2", gameP.finalTileSize*2, gameP.finalTileSize);
			attackRight1 = setUpAttackImages("/player/attack_right_1", gameP.finalTileSize*2, gameP.finalTileSize);
			attackRight2 = setUpAttackImages("/player/attack_right_2", gameP.finalTileSize*2, gameP.finalTileSize);
		}
		else if(currentWeapon.name == "Axe") {
			attackUp1 = setUpAttackImages("/player/axe_up_1", gameP.finalTileSize, gameP.finalTileSize*2);
			attackUp2 = setUpAttackImages("/player/axe_up_2", gameP.finalTileSize, gameP.finalTileSize*2);
			attackDown1 = setUpAttackImages("/player/axe_down_1", gameP.finalTileSize, gameP.finalTileSize*2);
			attackDown2 = setUpAttackImages("/player/axe_down_2", gameP.finalTileSize, gameP.finalTileSize*2);
			attackLeft1 = setUpAttackImages("/player/axe_left_1", gameP.finalTileSize*2, gameP.finalTileSize);
			attackLeft2 = setUpAttackImages("/player/axe_left_2", gameP.finalTileSize*2, gameP.finalTileSize);
			attackRight1 = setUpAttackImages("/player/axe_right_1", gameP.finalTileSize*2, gameP.finalTileSize);
			attackRight2 = setUpAttackImages("/player/axe_right_2", gameP.finalTileSize*2, gameP.finalTileSize);
		}
	}
	
	// switch between different weapons and shields
	public void switchEquip() {
		int index = gameP.ui.slotCol + (5*gameP.ui.slotRow);
		if(index < bagSize) {
			Entity selectedEntity = bagItems.get(index);
			if(selectedEntity.isShield) {
				currentShield = selectedEntity;
				attackValue = getAttackVal();
			}
			else if (selectedEntity.isWeapon) {
				defenseValue = getDefenseVal();
				currentWeapon = selectedEntity;
				getAttackImage();
			}
			else if(selectedEntity.isConsumable){
				 selectedEntity.use();
				 bagItems.remove(index);
			}
		}
	}
	
	// update with the key pressed
	public void update() {
		if(keyC.attackPress) {
			if(keyC.rightPress || keyC.leftPress || keyC.upPress || keyC.downPress)move();
			
			playerAttack();
		}
		else if (gameP.keyController.shootPress && !projectile.living && shootCounter == 30 && (currentMana >= projectile.useCost)) {
			projectile.set(worldX, worldY, direction, true, this);
			currentMana-=projectile.useCost;
			shootCounter = 0;
			gameP.projectiles.add(projectile);
		}
		else {
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
		}
		
		if(shootCounter < 30)shootCounter++;
		if(manaCounter<240) {
			manaCounter++;
		}
		else {
			if(currentMana<maxMana)currentMana++;
			manaCounter=0;
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
		
		// check damage
		gameP.eHandler.checkEvent();
		
		//if(gameP.keyController.attackPress)playerAttack();
		
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
						image = attackUp1;
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
				if(gameP.keyController.attackPress)image = attackDown2;
				else image = down1;
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
			attackAreaCheck();
		}
		else {
			attackNum = 2;
			attackAreaCheck();
			attackCount = 0;
		}
	}
	
	// attacking area check
	public void attackAreaCheck() {
		// save data
		int x = worldX;
		int y = worldY;
		int areaWidth = recP.width;
		int areaHeight = recP.height;
		
		switch (direction){
		case "up": worldY -= attackArea.height; break;
		case "down": worldY += attackArea.height; break;
		case "left": worldX -= attackArea.width; break;
		case "right": worldX += attackArea.width; break;
		}
		
		// attackArea becomes recP(solidArea)
		recP.width = attackArea.width;
		recP.height = attackArea.height;
		int index = gameP.collisonC.checkEntity(this, gameP.itemC.monsters);
		damageMonster(index, attack);
		
		// recover data
		worldX = x;
		worldY = y;
		recP.width = areaWidth;
		recP.height = areaHeight;
	}
	
	// deal with interactions with monster
	public void MonsterInteration(int index) {
		if(gameP.keyController.attackPress) attacking = true;
		if(index != 999) {
			if(!invincible && !gameP.itemC.monsters[index].dying) {
				receivedDamage = gameP.itemC.monsters[index].attack - defense;
				if(receivedDamage<0)receivedDamage=0;
				currentLife-=receivedDamage;
				if(receivedDamage>0)invincible = true;
				
			}
			
		}
	}
	
	// cause damage on monster
	public void damageMonster(int index, int attack) {
		if(index != 999) {
			if(gameP.itemC.monsters[index].invincible == false) {
				receivedDamage = attack - gameP.itemC.monsters[index].defense;
				if(receivedDamage<0)receivedDamage=0;
				gameP.itemC.monsters[index].currentLife -= receivedDamage;
				gameP.itemC.monsters[index].invincible = true;
				gameP.itemC.monsters[index].damageReact();
				if(gameP.itemC.monsters[index].currentLife == 0) {
					gameP.itemC.monsters[index].dying = true;
					receiveExp(index);
				}
			}
		}
	}
	
	public void receiveExp(int index) {
		exp+=gameP.itemC.monsters[index].exp;
		if(exp>=nextLvlExp) {
			// stats update
			level++;
			nextLvlExp *= 2;
			maxLife+=2;
			currentLife=maxLife;
			strength++;
			dexterity++;
			attack = getAttackVal();
			defense = getDefenseVal();
			
			gameP.gameState = gameP.dialogue;
			gameP.ui.diaString = "LEVEL UPPPPPPPPPPPPPP";
			
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
		if(index != 999) {
			if(bagItems.size() < bagSize) {
				bagItems.add(gameP.itemC.items[index]);
			}
			gameP.itemC.items[index] = null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

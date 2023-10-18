package main;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.IntToDoubleFunction;

import button.SpaceButton;
import entity.Entity;
import item.Heart;
import item.Key;
import item.Mana;

public class UI {
	GamePanel gameP;
	Graphics2D g2;
	Font pixelFont;
	BufferedImage heart_full, heart_half, heart_blank, mana_blank, mana_full;
	int commandNum = 0;
	public SpaceButton spaceButton;
	int spaceNum = 0;
	int spaceCount = 0;
	public String diaString;
	public int slotCol = 0;
	public int slotRow = 0;
	
	public UI(GamePanel gameP) {
		this.gameP = gameP;
		InputStream input = getClass().getResourceAsStream("/fonts/advanced_pixel.ttf");
		try {
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, input);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spaceButton = new SpaceButton(gameP);
		
		// heart images
		Entity heartItem = new Heart(gameP);
		heart_full = heartItem.heart_full;
		heart_half = heartItem.heart_half;
		heart_blank = heartItem.heart_blank;
		// mana images
		mana_blank = new Mana(gameP).mana_blank;
		mana_full = new Mana(gameP).mana_full;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(pixelFont);
		g2.setColor(Color.white);
		if(gameP.gameState == gameP.menu) {
			drawMenu();
		}
		else if(gameP.gameState == gameP.playing) {
			drawPlayerLife();
			drawPlayerMana();
			drawButton();
		}
		else if(gameP.gameState == gameP.pause) {
			drawPlayerLife();
			drawPlayerMana();
			g2.drawString("Pause", 50, 50);
		}
		else if(gameP.gameState == gameP.dialogue) {
			drawPlayerLife();
			drawPlayerMana();
			drawDialogue();
		}
		else if(gameP.gameState == gameP.status) {
			drawPlayerLife();
			drawPlayerMana();
			drawStatus();
			drawBag();
		}
	}
	
	// what is in player's bag
	public void drawBag() {
		// frame
		drawWindowFrame(432, 72, 288, 240);
		
		// slot 
		final int startX = 452;
		final int startY = 92;
		int slotX = startX;
		int slotY = startY;
		final int slotSize = 50;
		// items
		for(int i = 0; i < gameP.player.bagItems.size(); i++) {
			if(gameP.player.bagItems.get(i) == gameP.player.currentWeapon ||
					gameP.player.bagItems.get(i) == gameP.player.currentShield) {
				g2.setColor(Color.YELLOW);
				g2.fillOval(slotX, slotY, 5, 5);
			}
			if(gameP.player.bagItems.get(i).isConsumable) {
				g2.setColor(Color.GREEN);
				g2.fillOval(slotX, slotY, 5, 5);
			}
			g2.drawImage(gameP.player.bagItems.get(i).down1, slotX, slotY, null);
			slotX+=slotSize;
			if(i == 4 || i == 9 || i == 14) {
				slotX = startX;
				slotY += gameP.finalTileSize;
			}
		}
		
		// description
		
		int textX = 452;
		int textY = 353;
		int itemIndex = slotCol + (5*slotRow);
		if(itemIndex < gameP.player.bagItems.size()){
			drawWindowFrame(432, 313, 288, 144);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
			g2.drawString("[ "+gameP.player.bagItems.get(itemIndex).name+" ]", textX, textY);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
			g2.drawString(gameP.player.bagItems.get(itemIndex).description, textX, textY+26);
		}
		
		// cursor
		int curX = startX + (slotSize*slotCol);
		int curY = startY + (slotSize*slotRow);
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(2));
		g2.drawRoundRect(curX, curY, gameP.finalTileSize, gameP.finalTileSize, 10, 10);
	}
	
	public void drawWindowFrame(int x, int y, int width, int height) {
		Color color = new Color(0, 0, 0, 225);
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		color = new Color(255, 255, 255);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(x+2, y+2, width-4, height-4, 25, 25);
	}
	
	// draw status screen
	public void drawStatus() {
		// frame
		drawWindowFrame(72, 72, 240, 480);
		
		// text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		int textX = 92;
		int textY = 120;
		final int lineSpace = 32;
		g2.drawString("Level", textX, textY);
		textY+=lineSpace;
		g2.drawString("Life", textX, textY);
		textY+=lineSpace;
		g2.drawString("Mana", textX, textY);
		textY+=lineSpace;
		g2.drawString("Strength", textX, textY);
		textY+=lineSpace;
		g2.drawString("Dexterity", textX, textY);
		textY+=lineSpace;
		g2.drawString("Attack", textX, textY);
		textY+=lineSpace;
		g2.drawString("Defense", textX, textY);
		textY+=lineSpace;
		g2.drawString("Exp", textX, textY);
		textY+=lineSpace;
		g2.drawString("Next Level", textX, textY);
		textY+=lineSpace;
		g2.drawString("Coin", textX, textY);
		textY+=(lineSpace*2-8);
		g2.drawString("Weapon", textX, textY);
		textY+=(lineSpace*2-8);
		g2.drawString("Shield", textX, textY);
		textY+=(lineSpace*2-8);
		
		// values
		textY = 120;
		textX = getAlignToRightX(String.valueOf(gameP.player.level), 282);
		g2.drawString(String.valueOf(gameP.player.level), textX, textY);
		textY+=lineSpace;
		String lifeString = String.valueOf(gameP.player.currentLife) + '/' + String.valueOf(gameP.player.maxLife);
		textX = getAlignToRightX(lifeString, 282);
		g2.drawString(lifeString, textX, textY);
		textY+=lineSpace;
		lifeString = String.valueOf(gameP.player.currentMana) + '/' + String.valueOf(gameP.player.maxMana);
		textX = getAlignToRightX(lifeString, 282);
		g2.drawString(lifeString, textX, textY);
		textY+=lineSpace;
		textX = getAlignToRightX(String.valueOf(gameP.player.strength), 282);
		g2.drawString(String.valueOf(gameP.player.strength), textX, textY);
		textY+=lineSpace;
		textX = getAlignToRightX(String.valueOf(gameP.player.dexterity), 282);
		g2.drawString(String.valueOf(gameP.player.dexterity), textX, textY);
		textY+=lineSpace;
		textX = getAlignToRightX(String.valueOf(gameP.player.attack), 282);
		g2.drawString(String.valueOf(gameP.player.attack), textX, textY);
		textY+=lineSpace;
		textX = getAlignToRightX(String.valueOf(gameP.player.defense), 282);
		g2.drawString(String.valueOf(gameP.player.defense), textX, textY);
		textY+=lineSpace;
		textX = getAlignToRightX(String.valueOf(gameP.player.exp), 282);
		g2.drawString(String.valueOf(gameP.player.exp), textX, textY);
		textY+=lineSpace;
		textX = getAlignToRightX(String.valueOf(gameP.player.nextLvlExp), 282);
		g2.drawString(String.valueOf(gameP.player.nextLvlExp), textX, textY);
		textY+=lineSpace;
		textX = getAlignToRightX(String.valueOf(gameP.player.coin), 282);
		g2.drawString(String.valueOf(gameP.player.coin), textX, textY);
		textY+=(lineSpace-8);
		
		// weapon images
		g2.drawImage(gameP.player.currentWeapon.down1,282-gameP.finalTileSize,textY, null);
		textY+=(lineSpace*2-8);
		g2.drawImage(gameP.player.currentShield.down1,282-gameP.finalTileSize,textY, null);
		textY+=lineSpace;
	}
	
	// draw buttons
	private void drawButton() {
		if(spaceButton.isSpace) {
			g2.drawImage(spaceButton.image1, (gameP.screenWidth-gameP.finalTileSize*2)/2+12, 480, null);
			spaceNum++;
			if(spaceNum > 48) {
				spaceNum = 0;
				gameP.ui.spaceButton.isSpace = false;
			}
			
		}
	}
	public void drawPlayerMana() {
		int x = 20;
		int y = 72;
		for(int i = 0; i < gameP.player.maxMana; i++) {
			g2.drawImage(mana_blank, x, y, null);
			x+= 36;
		}
		x = 20;
		y = 72;
		for(int i = 0; i < gameP.player.currentMana; i++) {
			g2.drawImage(mana_full, x, y, null);
			x+= 36;
		}
	}
	// draw player life
	public void drawPlayerLife() {
		int x = gameP.finalTileSize/2;
		int y = gameP.finalTileSize/2;
		for(int i = 0; i < gameP.player.maxLife/2; i++) {
			g2.drawImage(heart_blank, x, y, null);
			x+= gameP.finalTileSize;
		}
		
		x = gameP.finalTileSize/2;
		y = gameP.finalTileSize/2;
		for(int i = 0; i < gameP.player.currentLife; i++) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gameP.player.currentLife) {
				g2.drawImage(heart_full, x, y, null);
			}
			x+= gameP.finalTileSize;
		}
	}
	//draw menu screen
	public void drawMenu() {
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
		String textString = "Adventure Never Ends";
		int y = gameP.finalTileSize*3;
		// title
		g2.setColor(Color.gray);
		g2.drawString(textString, getCentreX(textString)+4, y+4);
		g2.setColor(Color.white);
		g2.drawString(textString, getCentreX(textString), y);
		
		// player image
		int x = (gameP.screenWidth-gameP.finalTileSize*2)/2-12;
		g2.drawImage(gameP.player.down2, x, 192, gameP.finalTileSize*2,gameP.finalTileSize*2, null);
		
		// menu
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
		textString = "New Game";
		y+= gameP.finalTileSize*4;
		g2.drawString(textString, getCentreX(textString), y);
		if(commandNum == 0) {g2.drawString(">", getCentreX(textString)-gameP.finalTileSize/2, y);}
		textString = "Load Game";
		y+= gameP.finalTileSize;
		g2.drawString(textString, getCentreX(textString), y);
		if(commandNum == 1) {g2.drawString(">", getCentreX(textString)-gameP.finalTileSize/2, y);}
		textString = "Quit";
		y+= gameP.finalTileSize;
		g2.drawString(textString, getCentreX(textString), y);
		if(commandNum == 2) {g2.drawString(">", getCentreX(textString)-gameP.finalTileSize/2, y);}
	}
	
	public int getCentreX(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gameP.screenWidth/2 - length/2;
		return x;
	}
	
	public int getAlignToRightX(String text, int endX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = endX - length;
		return x;
	}
	
	// draw hints
	public void drawHints() {
		// frames
		Color color = new Color(0, 0, 0, 225);
		g2.setColor(color);
		g2.fillRoundRect(96, 24, 576, 192, 35, 35);
		color = new Color(255, 255, 255);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(98, 26, 572, 188, 25, 25);
		
		// texts
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		int y = 72;
		for(String lines: diaString.split("\n")) {
			g2.drawString(lines, 144, y);
			y+= 40;
		}
		
	}
	
	// draw dialogue
	public void drawDialogue() {
		// frames
		Color color = new Color(0, 0, 0, 225);
		g2.setColor(color);
		g2.fillRoundRect(96, 24, 576, 192, 35, 35);
		color = new Color(255, 255, 255);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(98, 26, 572, 188, 25, 25);
		
		// texts
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		int y = 72;
		//String diaString =  gameP.itemC.npcs[0].dialogues[gameP.itemC.npcs[0].dialogueIndex];
		for(String lines: diaString.split("\n")) {
			g2.drawString(lines, 144, y);
			y+= 40;
		}
		
	}
	
	
	
	
	
	
	
	
}

package main;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import button.SpaceButton;
import item.Heart;
import item.Item;
import item.Key;

public class UI {
	GamePanel gameP;
	Graphics2D g2;
	Font pixelFont;
	BufferedImage heart_full, heart_half, heart_blank;
	int commandNum = 0;
	public SpaceButton spaceButton;
	public String diaString;
	
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
		Item heartItem = new Heart(gameP);
		heart_full = heartItem.heart_full;
		heart_half = heartItem.heart_half;
		heart_blank = heartItem.heart_blank;
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
			drawButton();
		}
		else if(gameP.gameState == gameP.pause) {
			drawPlayerLife();
			g2.drawString("Pause", 50, 50);
		}
		else if(gameP.gameState == gameP.dialogue) {
			drawPlayerLife();
			drawDialogue();
		}
	}
	
	// draw buttons
	private void drawButton() {
		if(spaceButton.isSpace) {
			g2.drawImage(spaceButton.image1, (gameP.screenWidth-gameP.finalTileSize*2)/2+12, 480, null);
			gameP.ui.spaceButton.isSpace = false;
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

package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import item.Key;

public class UI {
	GamePanel gameP;
	Graphics2D g2;
	Font pixelFont;
	
	public UI(GamePanel gameP) {
		this.gameP = gameP;
		InputStream input = getClass().getResourceAsStream("/fonts/advanced_pixel.ttf");
		try {
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, input);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(pixelFont);
		g2.setColor(Color.white);
		if(gameP.gameState == gameP.playing) {
			
		}
		else if(gameP.gameState == gameP.pause) {
			g2.drawString("Pause", 50, 50);
		}
		else if(gameP.gameState == gameP.dialogue) {
			drawDialogue();
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
		String diaString =  gameP.itemC.npcs[0].dialogues[gameP.itemC.npcs[0].dialogueIndex];
		for(String lines: diaString.split("\n")) {
			g2.drawString(lines, 144, y);
			y+= 40;
		}
		
	}
	
	
	
	
	
	
	
	
}

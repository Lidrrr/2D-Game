package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import item.Key;

public class UI {
	GamePanel gameP;
	Font font_40;
	Font font_80;
	BufferedImage image;
	
	public UI(GamePanel gameP) {
		this.gameP = gameP;
		font_40 = new Font("Arial", Font.PLAIN, 40);
		font_80 = new Font("Arial", Font.BOLD, 80);
		Key key = new Key();
		image = key.image;
	}
	
	public void draw(Graphics2D g2) {
	
		if(gameP.gameFinished) {
			g2.setFont(font_80);
			g2.setColor(Color.RED);
			String textString = "Congratulation!";
			int length = (int)g2.getFontMetrics().getStringBounds(textString, g2).getWidth();
			int x = gameP.screenWidth/2 - length/2;
			int y = gameP.screenHeight/2 + (gameP.finalTileSize*2);
			g2.drawString(textString, x, y);
			
			gameP.gameThread = null;
		}
		else {
			g2.setFont(font_40);
			g2.setColor(Color.white);
			
			g2.drawImage(image, gameP.finalTileSize/2, gameP.finalTileSize/2, gameP.finalTileSize, gameP.finalTileSize, null);
			g2.drawString("x " + gameP.player.keyOwned, 74, 65);
		}
		
	}
}

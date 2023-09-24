package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import item.Key;

public class UI {
	GamePanel gameP;
	Graphics2D g2;
	Font font_40;
	Font font_80;
	
	public UI(GamePanel gameP) {
		this.gameP = gameP;
		font_40 = new Font("Arial", Font.PLAIN, 40);
		font_80 = new Font("Arial", Font.BOLD, 80);
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(font_40);
		g2.setColor(Color.white);
		if(gameP.gameState == gameP.playing) {
			
		}
		else if(gameP.gameState == gameP.pause) {
			g2.drawString("Pause", 50, 50);
		}
	}
}

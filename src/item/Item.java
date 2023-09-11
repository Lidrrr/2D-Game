package item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Item {
	public BufferedImage image;
	public String name;
	public boolean collison = false;
	public int worldX, worldY;
	
	public void draw(Graphics2D g2, GamePanel gameP) {
		int screenX = worldX - gameP.player.worldX + gameP.player.screenX;
		int screenY = worldY - gameP.player.worldY + gameP.player.screenY;
		
		// not draw obj outside the screen
		if(worldX + gameP.finalTileSize > gameP.player.worldX - gameP.player.screenX &&
		   worldX - gameP.finalTileSize < gameP.player.worldX + gameP.player.screenX	&&
		   worldY + gameP.finalTileSize > gameP.player.worldY - gameP.player.screenY &&
		   worldY - gameP.finalTileSize < gameP.player.worldY + gameP.player.screenY) {
			g2.drawImage(image, screenX, screenY, gameP.finalTileSize, gameP.finalTileSize, null);
		}
	}
}

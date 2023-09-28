package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gameP;
	Rectangle eRectangle;
	int defaultX, dedaultY;
	 public EventHandler(GamePanel gameP) {
		 this.gameP = gameP;
		 
		 eRectangle = new Rectangle(23, 23, 2, 2);
		 defaultX = eRectangle.x;
		 dedaultY = eRectangle.y;
	 }
	 
	 public void checkEvent() {
		 if(touchArea(24, 14, "right")) {
			 playerDamage(gameP.dialogue);
		 }
		 if(touchArea(23, 7, "any")) {
			 healPool(gameP.dialogue);
		 }
		 if(touchArea(26, 14, "right")) {
			 teleport(gameP.dialogue);
		 }
	 }
	 
	 public void teleport(int gameState) {
		gameP.gameState = gameState;
		gameP.ui.diaString = "Teleporting";
		gameP.player.worldX = gameP.finalTileSize*37;
		gameP.player.worldY = gameP.finalTileSize*10;
	}
	 public void playerDamage(int gameState) {
		gameP.gameState = gameState;
		gameP.ui.diaString = "You got damaged!";
		gameP.player.currentLife -= 1;

	 }
	 
	 // heals player's life
	 public void healPool(int gameState) {
		 if(gameP.keyController.spacePress) {
			 gameP.gameState = gameState;
			 gameP.ui.diaString = "You have been healed";
			 gameP.player.currentLife = gameP.player.maxLife;
		 }
	 }
	 
	 // check if player touches event center area
	 private boolean touchArea(int col, int row, String direction) {
		boolean touched = false;
		
		gameP.player.recP.x += gameP.player.worldX;
		gameP.player.recP.y += gameP.player.worldY;
		eRectangle.x += col * gameP.finalTileSize;
		eRectangle.y += row *gameP.finalTileSize;
		
		if(gameP.player.recP.intersects(eRectangle)) {
			if(gameP.player.direction.contentEquals(direction) || direction.contentEquals("any")) {
				touched = true;
			}
		}
		
		gameP.player.recP.x = gameP.player.recX;
		gameP.player.recP.y = gameP.player.recY;
		eRectangle.x = defaultX;
		eRectangle.y = dedaultY;
		return touched;
	}
	 
	 
	 
	 
	 
	 
	 
}

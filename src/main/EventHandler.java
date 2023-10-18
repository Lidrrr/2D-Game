package main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gameP;
	EventRec eRectangle[][];
	int defaultX, dedaultY;
	 public EventHandler(GamePanel gameP) {
		 this.gameP = gameP;
		 eRectangle = new EventRec[gameP.worldCol][gameP.worldRow];
		 int col = 0, row = 0;
		 while(col < gameP.worldCol && row < gameP.worldRow) {
			 eRectangle[col][row] = new EventRec();
			 eRectangle[col][row].x = 23;
			 eRectangle[col][row].y = 23;
			 eRectangle[col][row].width = 2;
			 eRectangle[col][row].height = 2;
			 eRectangle[col][row].defaultX = eRectangle[col][row].x;
			 eRectangle[col][row].defaultY = eRectangle[col][row].y;
			 
			 col++;
			 if(col == gameP.worldCol) {
				 col = 0;
				 row++;
			 }
		 }
		 
	 }
	 
	 public void checkEvent() {
		 if(touchArea(24, 14, "right")) {
			 playerDamage(24, 14, gameP.dialogue);
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
	 public void playerDamage(int col, int row, int gameState) {
		gameP.gameState = gameState;
		gameP.ui.diaString = "You got damaged!";
		gameP.player.currentLife -= 1;
		eRectangle[col][row].eventFinish = true;
	 }
	 
	 // heals player's life
	 public void healPool(int gameState) {
		 if(gameP.keyController.spacePress) {
			 gameP.gameState = gameState;
			 gameP.ui.diaString = "You have been healed";
			 gameP.player.currentLife = gameP.player.maxLife;
			 gameP.player.currentMana = gameP.player.maxMana;
		 }
	 }
	 
	 // check if player touches event center area
	 private boolean touchArea(int col, int row, String direction) {
		boolean touched = false;
		
		gameP.player.recP.x += gameP.player.worldX;
		gameP.player.recP.y += gameP.player.worldY;
		eRectangle[col][row].x += col * gameP.finalTileSize;
		eRectangle[col][row].y += row *gameP.finalTileSize;
		
		if(gameP.player.recP.intersects(eRectangle[col][row]) && eRectangle[col][row].eventFinish == false) {
			if(gameP.player.direction.contentEquals(direction) || direction.contentEquals("any")) {
				touched = true;
			}
		}
		
		gameP.player.recP.x = gameP.player.recX;
		gameP.player.recP.y = gameP.player.recY;
		eRectangle[col][row].x = eRectangle[col][row].defaultX;
		eRectangle[col][row].y = eRectangle[col][row].defaultY;
		return touched;
	}
	 
	 
	 
	 
	 
	 
	 
}

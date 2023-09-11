package main;

import entity.Entity;

public class CollisonCheck {
	GamePanel gameP;
	
	public CollisonCheck(GamePanel gameP) {
		this.gameP = gameP;
	}
	
	public void checkCollison(Entity e) {
		int leftX = e.worldX + e.recP.x;
		int rightX = e.worldX + e.recP.x + e.recP.width;
		int upY = e.worldY + e.recP.y;
		int downY = e.worldY + e.recP.y + e.recP.height;
		
		int leftCol = leftX/gameP.finalTileSize;
		int rightCol = rightX/gameP.finalTileSize;
		int upRow = upY/gameP.finalTileSize;
		int downRow = downY/gameP.finalTileSize;
		
		int check1, check2;
		
		if(e.direction == "up") {
			upRow = (upY - e.speed)/gameP.finalTileSize;
			check1 = gameP.tileC.mapNum[leftCol][upRow];
			check2 = gameP.tileC.mapNum[rightCol][upRow];
			if(gameP.tileC.tiles[check1].collison == true || gameP.tileC.tiles[check2].collison == true) {
				e.isCollison = true;
			}
		}
		else if(e.direction == "down") {
			downRow = (downY + e.speed)/gameP.finalTileSize;
			check1 = gameP.tileC.mapNum[leftCol][downRow];
			check2 = gameP.tileC.mapNum[rightCol][downRow];
			if(gameP.tileC.tiles[check1].collison == true || gameP.tileC.tiles[check2].collison == true) {
				e.isCollison = true;
			}
		}
		else if(e.direction == "left") {
			leftCol = (leftX - e.speed)/gameP.finalTileSize;
			check1 = gameP.tileC.mapNum[leftCol][upRow];
			check2 = gameP.tileC.mapNum[leftCol][downRow];
			if(gameP.tileC.tiles[check1].collison == true || gameP.tileC.tiles[check2].collison == true) {
				e.isCollison = true;
			}
		}
		else if(e.direction == "right") {
			rightCol = (rightX + e.speed)/gameP.finalTileSize;
			check1 = gameP.tileC.mapNum[rightCol][upRow];
			check2 = gameP.tileC.mapNum[rightCol][downRow];
			if(gameP.tileC.tiles[check1].collison == true || gameP.tileC.tiles[check2].collison == true) {
				e.isCollison = true;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

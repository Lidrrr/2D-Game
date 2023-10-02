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
	
	// check collisions of entity and items
	public int checkItem(Entity e, boolean ifPlayer) {
		
		int index = 999;
		
		for(int i = 0; i < gameP.itemC.items.length; i++) {
			if(gameP.itemC.items[i] != null) {
				e.recP.x += e.worldX;
				e.recP.y += e.worldY;
				gameP.itemC.items[i].recP.x += gameP.itemC.items[i].worldX;
				gameP.itemC.items[i].recP.y += gameP.itemC.items[i].worldY;
				
				if(e.direction == "up") {
					e.recP.y -= e.speed;
					
				}
				else if(e.direction == "down") {
					e.recP.y += e.speed;
					
				}
				else if(e.direction == "left") {
					e.recP.x -= e.speed;
					
				}
				else if(e.direction == "right") {
					e.recP.x += e.speed;
					
				}
				if(e.recP.intersects(gameP.itemC.items[i].recP)) {
					if(gameP.itemC.items[i].collison == true) {
						e.isCollison = true;
					}
					if(ifPlayer == true) {
						index = i;
					}
				}
				e.recP.x = e.recX;
				e.recP.y = e.recY;
				gameP.itemC.items[i].recP.x = gameP.itemC.items[i].recX;
				gameP.itemC.items[i].recP.y = gameP.itemC.items[i].recY;
			}
		}
		
		return index;
	}
	
	public int checkEntity(Entity e, Entity[] targets) {

		int index = 999;
		
		for(int i = 0; i < targets.length; i++) {
			if(targets[i] != null) {
				e.recP.x += e.worldX;
				e.recP.y += e.worldY;
				targets[i].recP.x += targets[i].worldX;
				targets[i].recP.y += targets[i].worldY;
				
				if(e.direction == "up") {
					e.recP.y -= e.speed;
					
				}
				else if(e.direction == "down") {
					e.recP.y += e.speed;
					
				}
				else if(e.direction == "left") {
					e.recP.x -= e.speed;
				}
				else if(e.direction == "right") {
					e.recP.x += e.speed;
					
				}
				if(e.recP.intersects(targets[i].recP)) {
					if(targets[i] != e) {
						e.isCollison = true;
						if(targets[i].name == "npc" && e.name == "player")gameP.ui.spaceButton.isSpace = true;
						index = i;
					}
					
				}
				e.recP.x = e.recX;
				e.recP.y = e.recY;
				targets[i].recP.x = targets[i].recX;
				targets[i].recP.y = targets[i].recY;
			}
		}
		
		return index;
	}
	
	public void NPCTouchPlayer(Entity e) {
		e.recP.x += e.worldX;
		e.recP.y += e.worldY;
		gameP.player.recP.x += gameP.player.worldX;
		gameP.player.recP.y += gameP.player.worldY;
		
		if(e.direction == "up") {
			e.recP.y -= e.speed;
			if(e.recP.intersects(gameP.player.recP)) {
				e.isCollison = true;
			}
		}
		else if(e.direction == "down") {
			e.recP.y += e.speed;
			if(e.recP.intersects(gameP.player.recP)) {
				e.isCollison = true;
			}
		}
		else if(e.direction == "left") {
			e.recP.x -= e.speed;
			if(e.recP.intersects(gameP.player.recP)) {
				e.isCollison = true;
			}
		}
		else if(e.direction == "right") {
			e.recP.x += e.speed;
			if(e.recP.intersects(gameP.player.recP)) {
				e.isCollison = true;
			}
		}
		e.recP.x = e.recX;
		e.recP.y = e.recY;
		gameP.player.recP.x = gameP.player.recX;
		gameP.player.recP.y = gameP.player.recY;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

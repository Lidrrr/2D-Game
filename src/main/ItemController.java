package main;

import item.Chest;
import item.Door;
import item.Key;

public class ItemController {
	GamePanel gameP;
	
	public ItemController(GamePanel gameP) {
		this.gameP = gameP;
	}
	
	public void createItems() {
		gameP.items[0] = new Key();
		gameP.items[0].worldX = 23 * gameP.finalTileSize;
		gameP.items[0].worldY = 7 * gameP.finalTileSize;
		
		gameP.items[1] = new Key();
		gameP.items[1].worldX = 23 * gameP.finalTileSize;
		gameP.items[1].worldY = 40 * gameP.finalTileSize;
		
		gameP.items[2] = new Key();
		gameP.items[2].worldX = 37 * gameP.finalTileSize;
		gameP.items[2].worldY = 7 * gameP.finalTileSize;
		
		gameP.items[3] = new Door();
		gameP.items[3].worldX = 10 * gameP.finalTileSize;
		gameP.items[3].worldY = 11 * gameP.finalTileSize;
		
		gameP.items[4] = new Door();
		gameP.items[4].worldX = 8 * gameP.finalTileSize;
		gameP.items[4].worldY = 20 * gameP.finalTileSize;
		
		gameP.items[5] = new Door();
		gameP.items[5].worldX = 12 * gameP.finalTileSize;
		gameP.items[5].worldY = 22 * gameP.finalTileSize;
		
		gameP.items[6] = new Chest();
		gameP.items[6].worldX = 10 * gameP.finalTileSize;
		gameP.items[6].worldY = 7 * gameP.finalTileSize;
	}
}

package item;

import main.GamePanel;

public class ItemController {
	GamePanel gameP;
	public Item items[] = new Item[10];
	
	public ItemController(GamePanel gameP) {
		this.gameP = gameP;
	}
	
	public void createItems() {
		items[0] = new Key(gameP);
		items[0].worldX = 23 * gameP.finalTileSize;
		items[0].worldY = 7 * gameP.finalTileSize;
		
		items[1] = new Key(gameP);
		items[1].worldX = 23 * gameP.finalTileSize;
		items[1].worldY = 40 * gameP.finalTileSize;
		
		items[2] = new Key(gameP);
		items[2].worldX = 37 * gameP.finalTileSize;
		items[2].worldY = 7 * gameP.finalTileSize;
		
		items[3] = new Door(gameP);
		items[3].worldX = 10 * gameP.finalTileSize;
		items[3].worldY = 11 * gameP.finalTileSize;
		
		items[4] = new Door(gameP);
		items[4].worldX = 8 * gameP.finalTileSize;
		items[4].worldY = 20 * gameP.finalTileSize;
		
		items[5] = new Door(gameP);
		items[5].worldX = 12 * gameP.finalTileSize;
		items[5].worldY = 22 * gameP.finalTileSize;
		
		items[6] = new Chest(gameP);
		items[6].worldX = 10 * gameP.finalTileSize;
		items[6].worldY = 7 * gameP.finalTileSize;
		
		items[7] = new Boots(gameP);
		items[7].worldX = 37 * gameP.finalTileSize;
		items[7].worldY = 42 * gameP.finalTileSize;
	}
}

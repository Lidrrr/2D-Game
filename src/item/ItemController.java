package item;

import entity.Entity;
import entity.NPC;
import main.GamePanel;

public class ItemController {
	GamePanel gameP;
	public Item items[] = new Item[10];
	public Entity npcs[] = new Entity[10];
	
	public ItemController(GamePanel gameP) {
		this.gameP = gameP;
	}
	
	public void createItems() {
		
	}
	
	public void createNPCs() {
		npcs[0] = new NPC(gameP);
		npcs[0].worldX = gameP.finalTileSize*21;
		npcs[0].worldY = gameP.finalTileSize*21;
	}
}

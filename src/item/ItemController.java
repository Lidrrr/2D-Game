package item;

import entity.Entity;
import entity.NPC;
import main.GamePanel;
import monster.Slime;

public class ItemController {
	GamePanel gameP;
	public Entity items[] = new Entity[10];
	public Entity npcs[] = new Entity[10];
	public Entity monsters[] = new Entity[20];
	//public Button buttons[] = new Button[10];
	
	
	public ItemController(GamePanel gameP) {
		this.gameP = gameP;
	}
	
	public void createItems() {
	}
	
	public void createNPCs() {
		npcs[0] = new NPC(gameP);
		npcs[0].worldX = gameP.finalTileSize*22;
		npcs[0].worldY = gameP.finalTileSize*23;
		
		
	}
	
	public void createMonsters() {
		monsters[0] = new Slime(gameP);
		monsters[0].worldX = gameP.finalTileSize*22;
		monsters[0].worldY = gameP.finalTileSize*37;
		
	}
}

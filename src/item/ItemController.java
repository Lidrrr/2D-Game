package item;

import entity.Entity;
import entity.NPC;
import main.GamePanel;
import monster.Slime;
import tile_interactive.Cutree;

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
		items[0] = new Axe(gameP);
		items[0].worldX = gameP.finalTileSize*21;
		items[0].worldY = gameP.finalTileSize*23;
		
		items[1] = new Shield_Blue(gameP);
		items[1].worldX = gameP.finalTileSize*22;
		items[1].worldY = gameP.finalTileSize*24;
		
		items[2] = new Heart(gameP);
		items[2].worldX = gameP.finalTileSize*24;
		items[2].worldY = gameP.finalTileSize*19;
	}
	
	public void createNPCs() {
		npcs[0] = new NPC(gameP);
		npcs[0].worldX = gameP.finalTileSize*22;
		npcs[0].worldY = gameP.finalTileSize*23;
		
		
	}
	public void createInterativeTiles() {
		gameP.iTiles[0] = new Cutree(gameP, 25, 8);
		gameP.iTiles[1] = new Cutree(gameP, 26, 8);
		gameP.iTiles[2] = new Cutree(gameP, 27, 8);
	}
	public void createMonsters() {
		monsters[0] = new Slime(gameP);
		monsters[0].worldX = gameP.finalTileSize*22;
		monsters[0].worldY = gameP.finalTileSize*37;
		monsters[1] = new Slime(gameP);
		monsters[1].worldX = gameP.finalTileSize*23;
		monsters[1].worldY = gameP.finalTileSize*37;
		monsters[2] = new Slime(gameP);
		monsters[2].worldX = gameP.finalTileSize*22;
		monsters[2].worldY = gameP.finalTileSize*38;
	}
}

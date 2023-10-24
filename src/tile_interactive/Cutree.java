package tile_interactive;

import main.GamePanel;

public class Cutree extends TileInteractive{
	GamePanel gameP;
	public Cutree(GamePanel gameP, int col, int row) {
		super(gameP, col, row);
		this.gameP = gameP;
		worldX = gameP.finalTileSize*col;
		worldY = gameP.finalTileSize*row;
		breakable = true;
		currentLife = 15;
		down1 = setUpImages("/tiles/cutree");
	}

	
	public TileInteractive interact() {
		TileInteractive tile = this;
		if(gameP.player.currentWeapon.name == "Axe") {
			tile = new Trunk(gameP, worldX/gameP.finalTileSize, worldY/gameP.finalTileSize);
		}
		return tile;
	}
}

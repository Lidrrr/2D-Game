package tile_interactive;

import java.awt.Rectangle;

import main.GamePanel;

public class Trunk extends TileInteractive{
	GamePanel gameP;
	public Trunk(GamePanel gameP, int col, int row) {
		super(gameP, col, row);
		this.gameP = gameP;
		worldX = gameP.finalTileSize*col;
		worldY = gameP.finalTileSize*row;
		down1 = setUpImages("/tiles/trunk");
		recP = new Rectangle(0,0,0,0);
		recX = recP.x;
		recY = recP.y;
	}

}

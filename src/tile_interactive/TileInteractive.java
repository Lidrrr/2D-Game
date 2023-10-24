package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class TileInteractive extends Entity{

	public boolean breakable = false;
	public TileInteractive(GamePanel gameP, int col, int row) {
		super(gameP);
		
	}
	public TileInteractive interact() {
		TileInteractive tile = null;
		return tile;
	}
}

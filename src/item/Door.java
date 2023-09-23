package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Door extends Item{
	public Door(GamePanel gameP) {
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/door.png"));
			eTools.scaIeImage(image, gameP.finalTileSize, gameP.finalTileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collison = true;
	}
}

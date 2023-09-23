package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Chest extends Item{
	public Chest(GamePanel gameP) {
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/chest.png"));
			eTools.scaIeImage(image, gameP.finalTileSize, gameP.finalTileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

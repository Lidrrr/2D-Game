package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Key extends Item{
	
	public Key(GamePanel gameP) {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/key.png"));
			eTools.scaIeImage(image, gameP.finalTileSize, gameP.finalTileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}

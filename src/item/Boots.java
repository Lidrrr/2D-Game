package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Boots extends Item{
	public Boots(GamePanel gameP) {
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/boots.png"));
			eTools.scaIeImage(image, gameP.finalTileSize, gameP.finalTileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

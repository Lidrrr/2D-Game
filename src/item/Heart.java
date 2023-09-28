package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Heart extends Item{
	//public BufferedImage image1, image2, image3;
	public Heart(GamePanel gameP) {
		name = "Heart";
		try {
			heart_full = ImageIO.read(getClass().getResourceAsStream("/items/heart_full.png"));
			heart_half = ImageIO.read(getClass().getResourceAsStream("/items/heart_half.png"));
			heart_blank = ImageIO.read(getClass().getResourceAsStream("/items/heart_blank.png"));
			heart_full = eTools.scaIeImage(heart_full, gameP.finalTileSize, gameP.finalTileSize);
			heart_half = eTools.scaIeImage(heart_half, gameP.finalTileSize, gameP.finalTileSize);
			heart_blank = eTools.scaIeImage(heart_blank, gameP.finalTileSize, gameP.finalTileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collison = true;
	}
}

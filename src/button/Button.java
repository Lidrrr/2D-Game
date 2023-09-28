package button;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.ExtraTools;
import main.GamePanel;

public class Button {
	GamePanel gameP;
	public BufferedImage image1, image2;
	public String name;
	public int worldX, worldY;
	public Rectangle recP = new Rectangle(0,0,48,48);
	public int recX = recP.x;
	public int recY = recP.y;
	ExtraTools eTools = new ExtraTools();
	
	
	public BufferedImage setUpImages(String imageString) {
		ExtraTools eTools = new ExtraTools();
		BufferedImage scaledImage = null;
		try {
			scaledImage = ImageIO.read(getClass().getResourceAsStream(imageString + ".png"));
			scaledImage = eTools.scaIeImage(scaledImage, gameP.finalTileSize, gameP.finalTileSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scaledImage;
	}
	
}

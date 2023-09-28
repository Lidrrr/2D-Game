package button;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class SpaceButton extends Button{
	public boolean isSpace = false;
	public SpaceButton(GamePanel gameP) {
		this.gameP = gameP;
		
		name = "SpaceKey";
		image1 = setUpImages("/buttons/SpaceKey1");
		image2 = setUpImages("/buttons/SpaceKey2");
	}
}

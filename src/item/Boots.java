package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Boots extends Item{
	public Boots() {
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/boots.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

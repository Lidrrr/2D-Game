package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Chest extends Item{
	public Chest() {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/chest.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

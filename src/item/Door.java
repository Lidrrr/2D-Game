package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Door extends Item{
	public Door() {
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/door.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collison = true;
	}
}

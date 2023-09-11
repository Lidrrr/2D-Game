package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX, worldY, speed;
	
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public String direction;
	
	// to create 'animation'
	public int entityCounter = 0;
	public int entityNum = 1;
	
	public Rectangle recP;
	public boolean isCollison;
}

package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileController {
	GamePanel gameP;
	public Tile[] tiles;
	public int mapNum[][];
	
	public TileController(GamePanel gameP) {
		this.gameP = gameP;
		tiles = new Tile[10];
		mapNum = new int[gameP.worldCol][gameP.worldRow];
		
		getTileImage();
		drawMap("/maps/world01.txt");
	}
	
	public void getTileImage() {
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tiles[1].collison = true;
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tiles[2].collison = true;
			tiles[3] = new Tile();
			tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			tiles[4] = new Tile();
			tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tiles[4].collison = true;
			tiles[5] = new Tile();
			tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawMap(String file) {
		try {
			InputStream input = getClass().getResourceAsStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			
			int col = 0;
			int row = 0;
			
			
			while(col < gameP.worldCol && row < gameP.worldRow) {
				String line = br.readLine();
				
				while(col < gameP.worldCol) {
					String nums[] = line.split(" ");
					int num = Integer.parseInt(nums[col]);
					
					mapNum[col][row] = num;
					col++;
				}
				if(col == gameP.worldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		while(col < gameP.worldCol && row < gameP.worldRow) {
			
			int worldX = col * gameP.finalTileSize;
			int worldY = row * gameP.finalTileSize;
			int screenX = worldX - gameP.player.worldX + gameP.player.screenX;
			int screenY = worldY - gameP.player.worldY + gameP.player.screenY;
			
			int tileNum = mapNum[col][row];
			
			// not draw map outside the screen
			if(worldX + gameP.finalTileSize > gameP.player.worldX - gameP.player.screenX &&
			   worldX - gameP.finalTileSize < gameP.player.worldX + gameP.player.screenX	&&
			   worldY + gameP.finalTileSize > gameP.player.worldY - gameP.player.screenY &&
			   worldY - gameP.finalTileSize < gameP.player.worldY + gameP.player.screenY) {
				g2.drawImage(tiles[tileNum].image, screenX, screenY, gameP.finalTileSize, gameP.finalTileSize, null);
			}
			
			col++;
			
			if(col == gameP.worldCol) {
				col = 0;
				row++;
			}
		}
		
	}
	
	
	
	
	
	
}

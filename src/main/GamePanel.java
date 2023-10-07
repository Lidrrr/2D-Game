package main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import item.ItemController;
import tile.TileController;

public class GamePanel extends JPanel implements Runnable{
	final int tileSize = 16; // 16 x 16
	final int ratio = 3;
	public final int finalTileSize = ratio * tileSize; // 48 x 48
	public boolean gameFinished = false;
	
	// game state
	public int gameState;
	public final int menu = 0;
	public final int playing = 1;
	public final int pause = 2;
	public final int dialogue = 3;
	
	// screen info.
	public final int screenCol = 16;
	public final int screenRow = 12;
	public final int screenWidth = finalTileSize * screenCol; // 768 pixels
	public final int screenHeight = finalTileSize * screenRow; // 576 pixels
	
	// map info.
	public final int worldCol = 50;
	public final int worldRow = 50;
	public final int worldWidth = worldCol * finalTileSize;
	public final int worldHeight = worldRow * finalTileSize;
	
	public TileController tileC = new TileController(this);
	public KeyController keyController = new KeyController(this);
	Sound sound = new Sound();
	Sound music = new Sound();
	public UI ui =  new UI(this);
	Thread gameThread;
	
	public Player player = new Player(this, keyController);
	public CollisonCheck collisonC = new CollisonCheck(this);
	public EventHandler eHandler = new EventHandler(this);
	public ItemController itemC = new ItemController(this);
	
	ArrayList<Entity> entities = new ArrayList<Entity>();
	
	// FPS
	int FPS = 60;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyController);
		this.setFocusable(true);
	}

	public void setUpGame() {
		itemC.createItems();
		itemC.createNPCs();
		itemC.createMonsters();
		playMusic(0);
		stopMusic();
		gameState = menu;
	}
	
	// start the thread of the game
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double delta = 0;
		double interval = 1000000000/FPS;
		long lastTime = System.nanoTime();
		long currentTime;
		
		//draw the game with fps 60
		while(gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / interval;
			lastTime = currentTime;
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void update() {
		if(gameState == playing) {
			player.update();
			
			for(int i = 0; i < itemC.npcs.length; i++) {
				if(itemC.npcs[i] != null) {itemC.npcs[i].update();}
			}
			
			for(int i = 0; i < itemC.monsters.length; i++) {
				if(itemC.monsters[i] != null) {
					if(itemC.monsters[i].living && !itemC.monsters[i].dying) itemC.monsters[i].update();
					else if(!itemC.monsters[i].living) itemC.monsters[i] = null;
				}
			}
		}
		else if(gameState == pause){
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		if(gameState == menu) {
			ui.draw(g2);
		}
		else {
			// tiles
			tileC.draw(g2);
			
			// add entities
			entities.add(player);
			for(int i = 0;i < itemC.npcs.length; i++) {
				if(itemC.npcs[i] != null) {
					entities.add(itemC.npcs[i]);
				}
			}
			for(int i = 0;i < itemC.items.length; i++) {
				if(itemC.items[i] != null) {
					entities.add(itemC.items[i]);
					
				}
			}
			
			for(int i = 0;i < itemC.monsters.length; i++) {
				if(itemC.monsters[i] != null) {
					entities.add(itemC.monsters[i]);
					
				}
			}
			
			Collections.sort(entities, new Comparator<Entity>() {

				@Override
				public int compare(Entity o1, Entity o2) {
					int result = Integer.compare(o1.worldY, o2.worldY);
					return result;
				}
			});
			
			// draw entities
			for(int i = 0; i < entities.size(); i++) {
				entities.get(i).draw(g2);
			}
			// clear entities
			entities.clear();
			
			ui.draw(g2);
			//g2.dispose();
		}
		
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSound(int i) {
		sound.setFile(i);
		sound.play();
	}
	
	
	
	
	
}

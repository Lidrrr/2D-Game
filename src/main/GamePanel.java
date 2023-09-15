package main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import item.Item;
import item.ItemController;
import tile.TileController;

public class GamePanel extends JPanel implements Runnable{
	final int tileSize = 16; // 16 x 16
	final int ratio = 3;
	public final int finalTileSize = ratio * tileSize; // 48 x 48
	
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
	KeyController keyController = new KeyController();
	Sound sound = new Sound();
	Thread gameThread;
	
	public Player player = new Player(this, keyController);
	public CollisonCheck collisonC = new CollisonCheck(this);
	
	public ItemController itemC = new ItemController(this);
	
	// FPS
	int FPS = 60;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyController);
		this.setFocusable(true);
	}

	public void setUpObj() {
		itemC.createItems();
		playMusic(0);
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
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		tileC.draw(g2);
		for(int i = 0; i < itemC.items.length; i++) {
			if(itemC.items[i] != null) {
				itemC.items[i].draw(g2, this);
			}
		}
		
		player.draw(g2);
		g2.dispose();
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		sound.stop();
	}
	
	public void playSound(int i) {
		sound.setFile(i);
		sound.play();
	}
	
	
	
	
	
}

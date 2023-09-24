package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener{
	GamePanel gameP;
	public boolean upPress, downPress, leftPress, rightPress;

	public KeyController(GamePanel gameP) {
		this.gameP = gameP;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	// What to do after key is pressed with WASD
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) {
			upPress = true;
		}
		if(key == KeyEvent.VK_A) {
			leftPress = true;
		}
		if(key == KeyEvent.VK_S) {
			downPress = true;
		}
		if(key == KeyEvent.VK_D) {
			rightPress = true;
		}
		if(key == KeyEvent.VK_P) {
			if(gameP.gameState == gameP.playing) {
				gameP.gameState = gameP.pause;
			}
			else if(gameP.gameState == gameP.pause) {
				gameP.gameState = gameP.playing;
			}
		}
	}

	// What to do after key is released with WASD
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) {
			upPress = false;
		}
		if(key == KeyEvent.VK_A) {
			leftPress = false;
		}
		if(key == KeyEvent.VK_S) {
			downPress = false;
		}
		if(key == KeyEvent.VK_D) {
			rightPress = false;
		}
		
	}

}

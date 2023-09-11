package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener{
	public boolean upPress, downPress, leftPress, rightPress;

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

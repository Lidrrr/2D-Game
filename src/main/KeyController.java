package main;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import javax.print.attribute.standard.Media;

public class KeyController implements KeyListener{
	GamePanel gameP;
	public boolean upPress, downPress, leftPress, rightPress, spacePress, attackPress;

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
		// menu state
		if(gameP.gameState == gameP.menu) {
			if(key == KeyEvent.VK_W) {
				gameP.ui.commandNum--;
				if(gameP.ui.commandNum < 0) {
					gameP.ui.commandNum = 2;
				}
			}
			if(key == KeyEvent.VK_S) {
				gameP.ui.commandNum++;
				if(gameP.ui.commandNum > 2) {
					gameP.ui.commandNum = 0;
				}
			}
			if(key == KeyEvent.VK_ENTER) {
				if(gameP.ui.commandNum == 0) {
					gameP.gameState = gameP.playing;
				}
				else if(gameP.ui.commandNum == 2) {
					System.exit(0);;
				}
			}
		}
		
		// playing state
		if(gameP.gameState == gameP.playing) {
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
				gameP.gameState = gameP.pause;
			}
			if(key == KeyEvent.VK_SPACE) {
				spacePress = true;
			}
			if(key == KeyEvent.VK_J) {
				attackPress = true;
			}
		}
		
		// pause state
		else if(gameP.gameState == gameP.pause) {
			gameP.gameState = gameP.playing;
		}
		// dialogue state
		else if(gameP.gameState == gameP.dialogue) {
			if(key == KeyEvent.VK_SPACE) {
				if(gameP.itemC.npcs[0].dialogues[gameP.itemC.npcs[0].dialogueIndex + 1] != null) {
					gameP.itemC.npcs[0].dialogueIndex++;
				}
				
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
		if(key == KeyEvent.VK_SPACE) {
			spacePress = false;
		}
		if(key == KeyEvent.VK_J) {
			attackPress = false;
		}
	}

}

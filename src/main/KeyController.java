package main;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import javax.print.attribute.standard.Media;

public class KeyController implements KeyListener{
	GamePanel gameP;
	public boolean upPress, downPress, leftPress, rightPress, spacePress, attackPress, shootPress;

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
			menuState(key);
		}
		
		// playing state
		if(gameP.gameState == gameP.playing) {
			playState(key);
		}
		
		// pause state
		else if(gameP.gameState == gameP.pause) {
			pauseState(key);
		}
		// dialogue state
		else if(gameP.gameState == gameP.dialogue) {
			dialogueState(key);
		}
		// status state
		else if(gameP.gameState == gameP.status) {
			statusState(key);
			cursorMove(key);
		}
	}
	
	public void cursorMove(int key) {
		if(key == KeyEvent.VK_W) {
			gameP.ui.slotRow--;
			if(gameP.ui.slotRow < 0) {
				gameP.ui.slotRow = 0;
			}
		}
		if(key == KeyEvent.VK_A) {
			gameP.ui.slotCol--;
			if(gameP.ui.slotCol < 0) {
				gameP.ui.slotCol = 0;
			}
		}
		if(key == KeyEvent.VK_S) {
			gameP.ui.slotRow++;
			if(gameP.ui.slotRow > 3) {
				gameP.ui.slotRow = 3;
			}
		}
		if(key == KeyEvent.VK_D) {
			gameP.ui.slotCol++;
			if(gameP.ui.slotCol > 4) {
				gameP.ui.slotCol = 4;
			}
		}
	}
	
	public void menuState(int key) {
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
	
	public void playState(int key) {
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
		if(key == KeyEvent.VK_I) {
			gameP.gameState = gameP.status;
		}
		if(key == KeyEvent.VK_SPACE) {
			spacePress = true;
		}
		if(key == KeyEvent.VK_J) {
			attackPress = true;
		}
		if(key == KeyEvent.VK_K) {
			shootPress = true;
		}
	}
	
	public void pauseState(int key) {
		gameP.gameState = gameP.playing;
	}

	public void dialogueState(int key) {
		if(key == KeyEvent.VK_SPACE) {
			if(gameP.itemC.npcs[0].dialogues[gameP.itemC.npcs[0].dialogueIndex + 1] != null) {
				gameP.itemC.npcs[0].dialogueIndex++;
			}
			
			gameP.gameState = gameP.playing;
		}
	}
	
	public void statusState(int key) {
		if(key == KeyEvent.VK_I) {
			gameP.gameState = gameP.playing;
		}
		if(key == KeyEvent.VK_K) {
			gameP.player.switchEquip();
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
		if(key == KeyEvent.VK_K) {
			shootPress = false;
		}
	}

}

package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundUrl[] = new URL[10];
	
	public Sound() {
		soundUrl[0] = getClass().getResource("/sounds/BlueBoyAdventure.wav");
		soundUrl[1] = getClass().getResource("/sounds/coin.wav");
		soundUrl[2] = getClass().getResource("/sounds/powerup.wav");
		soundUrl[3] = getClass().getResource("/sounds/unlock.wav");
		soundUrl[4] = getClass().getResource("/sounds/fanfare.wav");
	}
	
	public void setFile(int i) {
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundUrl[i]);
			clip = AudioSystem.getClip();
			clip.open(inputStream);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}

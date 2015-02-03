package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JLabel;
import javax.swing.Timer;

public class BlindTimer {
	
	private static JLabel label;
	Timer countdownTimer;
	static int timeRemaining = 900;
	static int blindsLevel = 1;
	Map<Integer, String> levelsMap;
	
	static int blindsLevelPaused = 1;
	static int timeRemainingPause = 900;
	static boolean pauseOn = false;
	
	
	public BlindTimer(JLabel label){
		this.createBlindsMap();
		countdownTimer = new Timer(1000, new CountdownTimerListener());
		BlindTimer.label = label;
		countdownTimer.start();
	}
	
	private class CountdownTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!pauseOn){
				if(--timeRemaining > 0){
					label.setText(getText());
				} else {
					if(blindsLevel < 25){
						blindsLevel ++;
						System.out.println("troca blinds");
						timeRemaining = 900;
						new Sound().run();
					} else {
						label.setText("Finalizado");
						countdownTimer.stop();
					}
				}
			}
		}
		
		private String getText(){
			int minutes = timeRemaining/60;
			int seconds = timeRemaining%60;
			String title = "Desterro Poker League";
			String level = "<br> Blinds Level: " + blindsLevel;
			String blindsDescription = "<br> <font color=\"red\"> Blinds: " + getBlindLevel() + "</font>";
			String timeRemaining = "<br> Time Remaining: " + minutes + ":" + seconds;
			String nextBlinds = "<br> <font size=\"70\"> Next: " + getNextBlinds() + "</font>";
			
			String text = "<html>"+ title + level + blindsDescription + timeRemaining + nextBlinds + "</html>"; 
			return text;
		}
	}
	
	public static void pauseTime(){
		blindsLevelPaused = blindsLevel;
		timeRemainingPause = timeRemaining;
		pauseOn = true;
		label.setText("Waiting...");
	}
	
	public static void restartTime(){
		blindsLevel = blindsLevelPaused;
		timeRemaining = timeRemainingPause;
		pauseOn = false;
	}
	
	public void setLabelText(String text){
		label.setText(text);
	}
	
	public void setTimeRemaining(int time){
		timeRemaining = time;
	}
	
	public String getBlindLevel(){
		return this.levelsMap.get(blindsLevel);
	}
	
	public String getNextBlinds(){
		return this.levelsMap.get(blindsLevel + 1);
	}
	
	public void createBlindsMap(){
		this.levelsMap = new HashMap<Integer, String>();
		levelsMap.put(1, "5 / 10");
		levelsMap.put(2, "10 / 20");
		levelsMap.put(3, "15 / 30");
		levelsMap.put(4, "20 / 40");
		levelsMap.put(5, "25 / 50");
		levelsMap.put(6, "30 / 60");
		levelsMap.put(7, "40 / 80 - ante 10");
		levelsMap.put(8, "50 / 100 - ante 10");
		levelsMap.put(9, "75 / 150 - ante 20");
		levelsMap.put(10, "100 / 200 - ante 20");
		//levelsMap.put(11, "SB 125 - BB 250 - ante 30");
		levelsMap.put(11, "150 / 300 - ante 30");
		//levelsMap.put(13, "SB 175 - BB 350 - ante 40");
		levelsMap.put(12, "200 / 400 - ante 40");
		levelsMap.put(13, "250 / 500 - ante 50");
		levelsMap.put(14, "300 / 600 - ante 50");
		levelsMap.put(15, "350 / 700 - ante 100");
		levelsMap.put(16, "400 / 800 - ante 100");
		levelsMap.put(17, "500 / 1000 - ante 100");
		levelsMap.put(18, "750 / 1500 - ante 200");
		levelsMap.put(19, "1000 / 2000 - ante 200");
		levelsMap.put(20, "2000 / 4000 - ante 500");
		levelsMap.put(21, "3000 / 6000 - ante 500");
		levelsMap.put(22, "4000 / 8000 - ante 500");
		levelsMap.put(23, "5000 / 10000 - ante 1000");
		levelsMap.put(24, "7500 / 15000 - ante 2000");
	}
	
	private class Sound extends Thread{
		public void run(){
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File("C:/beep-18.wav")));
				clip.start();
				Thread.sleep(2000);
				clip.stop();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}

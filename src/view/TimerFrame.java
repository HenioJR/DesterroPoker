package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JButton restartTimeButton;
	private JButton pauseButton;
	public BlindTimer blindTimer;
	
	public TimerFrame() {
		JPanel panel = new JPanel();
		this.createButton();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1300, 700);
		this.label = new JLabel("Iniciando...", JLabel.CENTER);
		this.label.setFont( new Font( "Serif", Font.BOLD, 90 ) ); 
		this.label.setSize(1300, 650);
		this.getContentPane().add(label);
		this.blindTimer = new BlindTimer(this.label);
		this.setVisible(true);
		panel.add(this.label);
		panel.add(this.restartTimeButton);
		panel.add(this.pauseButton);
		this.add(panel);
	}
	
	public BlindTimer getBlindTimer(){
		return this.blindTimer;
	}
	
	private void createButton(){
		this.restartTimeButton = new JButton();
		restartTimeButton.setSize(200, 100);
		restartTimeButton.setText("Reset Time");
		restartTimeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clique " + e.getActionCommand());
				BlindTimer.timeRemaining = 900;
			}
		});
		
		this.pauseButton = new JButton();
		pauseButton.setSize(200, 100);
		pauseButton.setText("Pause");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Pause")){
					System.out.println("clique " + e.getActionCommand());
					BlindTimer.pauseTime();
					JButton bt = (JButton) e.getSource();
					bt.setText("Restart");
				} else {
					System.out.println("clique " + e.getActionCommand());
					BlindTimer.restartTime();
					JButton bt = (JButton) e.getSource();
					bt.setText("Pause");
				}
			}
		});
	}
}

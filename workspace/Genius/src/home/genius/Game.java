package home.genius;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import home.genius.control.GeniusMouseListener;
import home.genius.gui.GraphicInterface;

public class Game {
	
	private static ArrayList<Integer> sequence = new ArrayList<Integer>();
	private static boolean keepPlaying = true;
	private static final int GLOW_TIME = 750;
	private static final int INTERVAL = 450;
	
	public static void main(String[] args) {
		GraphicInterface gi = new GraphicInterface();
		Thread t = new Thread(new GeniusMouseListener());
		t.start();
		int a = 0;
		delay(2000);
		
		while(keepPlaying) {
			for(int i = 0; i < sequence.size(); i++) {
				gi.glow(sequence.get(i));
				delay(GLOW_TIME);
				gi.restoreImage();
				delay(INTERVAL);
			}

			sequence.add(gi.glow());
			delay(GLOW_TIME);
			gi.restoreImage();
			delay(INTERVAL);
			
			GeniusMouseListener.activate();
			for(int i = 0; i < sequence.size(); i++) {
				do {
					a = GeniusMouseListener.getAnswer();
					System.out.println(a);
					delay(100);
				}
				while(a == -1);
				GeniusMouseListener.eraseAnswer();
				gi.glow(a);
				delay(200);
				gi.restoreImage();
				if(a != sequence.get(i)) {
					keepPlaying = false;
					break;
				}
			}
			GeniusMouseListener.deactivate();
			delay(500);
			if(!keepPlaying) {
				if(JOptionPane.showConfirmDialog(null, "Fim do jogo. Você errou e fez " +
						(sequence.size()-1) + " pontos. Deseja jogar novamente?", 
						"Fim",2) == JOptionPane.YES_OPTION) {
					keepPlaying = true;
					sequence = new ArrayList<Integer>();
				}
				else {
					System.exit(0);
				}
			}
		}
	}
	
	public static void delay(long millis) {
		long t0 = System.currentTimeMillis();
		do {}
		while((System.currentTimeMillis() - t0) < millis);
	}
}

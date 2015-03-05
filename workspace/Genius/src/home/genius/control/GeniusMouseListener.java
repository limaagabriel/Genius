package home.genius.control;

import home.genius.gui.GraphicInterface;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class GeniusMouseListener implements MouseListener, Runnable {
	
	private static boolean entered = false;
	private static boolean active = false;
	private static double[] xy = new double[2];
	private static String answer = null;
	private static int selection = -1;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(active) {
			System.out.println("click active");
			xy = getMouseXY(GraphicInterface.getFrame());
			Color pixel = new Color(GraphicInterface.getImage().getRGB((int) xy[0], (int) xy[1]));
			int average = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
			if(pixel.getGreen() > average * 1.2 && pixel.getRed() < average * 1.2) {
				answer = "green";
				selection = GraphicInterface.GREEN;
			}
			else if(pixel.getRed() > average * 1.1 && pixel.getGreen() < average * 1.1) {
				answer = "red";
				selection = GraphicInterface.RED;
			}
			else if(pixel.getGreen() > average * 1.1 && pixel.getRed() > average * 1.1 &&
					pixel.getBlue() < average * 0.9) {
				answer = "yellow";
				selection = GraphicInterface.YELLOW;
			}
			else if(pixel.getBlue() > average * 1.1 && pixel.getRed() < average * 1.0) {
				answer = "blue";
				selection = GraphicInterface.BLUE;
			}
			System.out.println(answer);
		}
		System.out.println("click");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.println("press");
		//pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println("release");
		//pressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("enter");
		entered = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("exit");
		entered = false;
	}
	
	public static double[] getMouseXY(JFrame frame) {
		double[] result = new double[2];
		PointerInfo pointer = MouseInfo.getPointerInfo();
		Point a = pointer.getLocation();
		Point b = frame.getLocation();
		result[0] = a.getX() - b.getX();
		result[1] = a.getY() - b.getY();
		return result;
	}
	
	public static boolean isInside(JFrame frame) {
		double[] xy = getMouseXY(frame);
		if((xy[0] > 0 && xy[0] < frame.getWidth()) && (xy[1] > 0 && xy[1] < frame.getHeight())) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void run() {
		while(true) {
			if(answer == null) {
				System.out.println(entered);
			}
			else {
				System.out.println(entered + " " + answer);
				answer = null;
			}
			break;
		}
	}

	public static void activate() {
		System.out.println("active");
		active = true;
	}
	
	public static void deactivate() {
		System.out.println("not active");
		active = false;
	}
	
	public static int getAnswer() {
		return selection;
	}
	
	public static void eraseAnswer() {
		selection = -1;
	}
}

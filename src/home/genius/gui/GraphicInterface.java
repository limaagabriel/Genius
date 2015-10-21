package home.genius.gui;

import home.genius.control.GeniusMouseListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class GraphicInterface {
	private static final String BG_PATH = "genius.png";

	public static final int GREEN = 0;
	public static final int RED = 1;
	public static final int YELLOW = 2;
	public static final int BLUE = 3;
	private static JFrame frame = null;
	private static BufferedImage image = null;
	
	public GraphicInterface() {
		frame = new JFrame("Genius");  
	    
	    try {
			image = ImageIO.read(new File(BG_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setImageTo(image, frame);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				if(JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Fim", 2) == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
			});
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocation(100, 100);
	    frame.setUndecorated(false);  
		frame.setVisible(true);
	}
	
	private void setImageTo(BufferedImage image, JFrame frame) { 
		ImageIcon icon = new ImageIcon(image);
		JLabel imageLabel = new JLabel(icon);
		frame.setSize(image.getWidth() + 30, image.getHeight() + 40);
		Container contentPane = frame.getContentPane();
		contentPane.removeAll();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
		imageLabel.addMouseListener(new GeniusMouseListener());
		contentPane.revalidate(); 
		frame.repaint();
		contentPane.revalidate(); 
		frame.repaint();
	}
	
	public void restoreImage() {
		try {
			image = ImageIO.read(new File(BG_PATH));
			setImageTo(image, frame);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int glow() {
		Color pixel = null;
		Random random = new Random();
		int color = random.nextInt(4);
		int d = 2;
		int average = 0;
		switch(color) {
			case GREEN:
				for(int j = 0; j < image.getHeight(); j++) {
					for(int i = 0; i < image.getWidth(); i++) {
						pixel = new Color(image.getRGB(i, j));
						average = (pixel.getRed() + pixel.getGreen() + 
								pixel.getBlue())/3;
						if(pixel.getGreen() > average * 1.2 && 
								pixel.getRed() < average * 1.2) {
							image.setRGB(i, j, new Color(pixel.getRed()/d,
									pixel.getGreen()/d, 
									pixel.getBlue()/d).getRGB());
						}
					}
				}
				break;
			case RED:
				for(int j = 0; j < image.getHeight(); j++) {
					for(int i = 0; i < image.getWidth(); i++) {
						pixel = new Color(image.getRGB(i, j));
						average = (pixel.getRed() + pixel.getGreen() + 
								pixel.getBlue())/3;
						if(pixel.getRed() > average * 1.1 && 
								pixel.getGreen() < average * 1.1) {
							image.setRGB(i, j, new Color(pixel.getRed()/d,
									pixel.getGreen()/d, 
									pixel.getBlue()/d).getRGB());
						}
					}
				}
				break;
			case YELLOW:
				for(int j = 0; j < image.getHeight(); j++) {
					for(int i = 0; i < image.getWidth(); i++) {
						pixel = new Color(image.getRGB(i, j));
						average = (pixel.getRed() + pixel.getGreen() + 
								pixel.getBlue())/3;
						if(pixel.getGreen() > average * 1.1 && 
								pixel.getRed() > average * 1.1 &&
								pixel.getBlue() < average * 0.9) {
							image.setRGB(i, j, new Color(pixel.getRed()/d,
									pixel.getGreen()/d, 
									pixel.getBlue()/d).getRGB());
						}
					}
				}
				break;
			case BLUE:
				for(int j = 0; j < image.getHeight(); j++) {
					for(int i = 0; i < image.getWidth(); i++) {
						pixel = new Color(image.getRGB(i, j));
						average = (pixel.getRed() + pixel.getGreen() + 
								pixel.getBlue())/3;
						if(pixel.getBlue() > average * 1.1 &&
								pixel.getRed() < average * 1.0) {
							image.setRGB(i, j, new Color(pixel.getRed()/d,
									pixel.getGreen()/d, 
									pixel.getBlue()/d).getRGB());
						}
					}
				}
				break;
		}
		setImageTo(image, frame);
		return color;
	}
	
	public void glow(int color) {
		Color pixel = null;
		int average = 0;
		int d = 2;
		switch(color) {
			case GREEN:
				for(int j = 0; j < image.getHeight(); j++) {
					for(int i = 0; i < image.getWidth(); i++) {
						pixel = new Color(image.getRGB(i, j));
						average = (pixel.getRed() + pixel.getGreen() + 
								pixel.getBlue())/3;
						if(pixel.getGreen() > average * 1.2 && 
								pixel.getRed() < average * 1.2) {
							image.setRGB(i, j, new Color(pixel.getRed()/d,
									pixel.getGreen()/d, 
									pixel.getBlue()/d).getRGB());
						}
					}
				}
				break;
			case RED:
				for(int j = 0; j < image.getHeight(); j++) {
					for(int i = 0; i < image.getWidth(); i++) {
						pixel = new Color(image.getRGB(i, j));
						average = (pixel.getRed() + pixel.getGreen() + 
								pixel.getBlue())/3;
						if(pixel.getRed() > average * 1.1 && 
								pixel.getGreen() < average * 1.1) {
							image.setRGB(i, j, new Color(pixel.getRed()/5,
									pixel.getGreen()/5, 
									pixel.getBlue()/5).getRGB());
						}
					}
				}
				break;
			case YELLOW:
				for(int j = 0; j < image.getHeight(); j++) {
					for(int i = 0; i < image.getWidth(); i++) {
						pixel = new Color(image.getRGB(i, j));
						average = (pixel.getRed() + pixel.getGreen() + 
								pixel.getBlue())/3;
						if(pixel.getGreen() > average * 1.1 && 
								pixel.getRed() > average * 1.1 &&
								pixel.getBlue() < average * 0.9) {
							image.setRGB(i, j, new Color(pixel.getRed()/d,
									pixel.getGreen()/d, 
									pixel.getBlue()/d).getRGB());
						}
					}
				}
				break;
			case BLUE:
				for(int j = 0; j < image.getHeight(); j++) {
					for(int i = 0; i < image.getWidth(); i++) {
						pixel = new Color(image.getRGB(i, j));
						average = (pixel.getRed() + pixel.getGreen() + 
								pixel.getBlue())/3;
						if(pixel.getBlue() > average * 1.1 &&
								pixel.getRed() < average * 1.0) {
							image.setRGB(i, j, new Color(pixel.getRed()/d,
									pixel.getGreen()/d, 
									pixel.getBlue()/d).getRGB());
						}
					}
				}
				break;
		}
		setImageTo(image, frame);
	}
	
	public static BufferedImage getImage() {
		return image;
	}

	public static JFrame getFrame() {
		return frame;
	}
}

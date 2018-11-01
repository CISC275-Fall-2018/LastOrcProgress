
package orcFinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.*;

//import lab2.Animation;


/**
 * View: Contains everything about graphics and images Know size of world, which
 * images to load etc
 *
 * has methods to provide boundaries use proper images for direction load images
 * for all direction (an image should only be loaded once!!! why?)
 **/
@SuppressWarnings("serial")
public class View extends JPanel {
	int x;
	int y;
	Direction d;
	int w = 500;
	int h = 300;
	int iw = 165;
	int ih = 165;
	int frameCount = 10;
	int picNum = 0;
	int arrayCounter = 0;
	int column;
	
	BufferedImage[][] pica;
	JFrame frame;
	private JButton pauseButton = new JButton("pause");
	MouseListener mouseListener;
	
	
	

	// Constructor: get image, segment and store in array
	public View() {		
		pica = new BufferedImage[8][11];
		for (Direction di : Direction.values()) {
			File f;
			f = new File("images/orc/orc_forward_" + di + ".png");
			BufferedImage im = createImage(f);
			pica[arrayCounter][0]= im;
			arrayCounter++;
		}
		for(int i = 0; i <8;i++) {	
			for(int j = 0;j<frameCount;j++) {
				BufferedImage im =pica[i][0].getSubimage(iw * j, 0, iw, ih);
				int h = j+1;
				pica[i][h] = im;		
			}
		}

		frame = new JFrame();
		frame.setLayout(null);
		JPanel orcPanel = new JPanel();
		orcPanel.add(pauseButton);
		orcPanel.addMouseListener(mouseListener);
		
		this.add(orcPanel);
	}

	// Read image from file and return
	private BufferedImage createImage(File filo) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(filo);
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	// Override this JPanel's paint method to cycle through picture array and draw
	// images
	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;

		// Choose correct image
		if (d.getName() == "southeast") {
			column = 1;
		}
		if (d.getName() == "northwest") {
			column = 5;
		}
		if (d.getName() == "southwest") {
			column = 7;
		}
		if (d.getName() == "northeast") {
			column = 3;
		}
		if (d.getName() == "north") {
			column = 0;
		}
		if (d.getName() == "south") {
			column = 4;
		}
		if (d.getName() == "east") {
			column = 2;
		}
		if (d.getName() == "west") {
			column = 6;
		}
		g.drawImage(pica[column][picNum+1], x, y, Color.gray, this);
	}

	public int getWidth() {
		return w;
	}
	public int getHeight() {
		return h;
	}

	public int getImageWidth() {
		return iw;
	}

	public int getImageHeight() {
		return ih;
	}

	public void update(int x, int y, Direction direct) {
		this.x = x;
		this.y = y;
		d = direct;

		frame.add(this);
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(w, h);
		frame.setVisible(true);
		frame.repaint();
		frame.getContentPane().add(pauseButton);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	void addPauseListener(ActionListener listenForPauseButton) {
		pauseButton.addActionListener(listenForPauseButton);
	}
	
   void addMouseListener(MouseAdapter listenForClick) {
	   frame.addMouseListener(listenForClick);
   }

}
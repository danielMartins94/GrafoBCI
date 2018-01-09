package Utilidades;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	  private Image img;

	  public ImagePanel() {
	    this(new ImageIcon("images/escalpo2.png").getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	   
	  }

	  public void paintComponent(Graphics g) {
		  super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
			g2d.dispose();
	  }

	}

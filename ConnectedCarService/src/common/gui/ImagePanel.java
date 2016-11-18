package common.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
 
	private BufferedImage image;
	
	public ImagePanel(File file) throws IOException {
		setLayout(null);
		//image = ImageIO.read(file);
	}
	
	public ImagePanel(BufferedImage img) throws IOException {
		setLayout(null);
		image = img;
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
    
    public void updateImage(BufferedImage img) {
    	image = img;
    }
}
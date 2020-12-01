package Math3D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(BufferedImage image) {
       this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }
    
    public void paintComponent() {
    	Dimension imgDim = new Dimension(500,500);
        BufferedImage mazeImage = new BufferedImage(imgDim.width, imgDim.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = mazeImage.createGraphics();
	        g2d.setBackground(Color.WHITE);
	        g2d.fillRect(0, 0, imgDim.width, imgDim.height);
	        g2d.setColor(Color.BLACK);
	        paintComponent(g2d);          
    }

}
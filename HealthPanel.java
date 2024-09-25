import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class HealthPanel extends JPanel{
    GridLayout gridLayout;
    private Color backgroundColour;
    private Image heartImage;
    private BufferedImage image;

    public HealthPanel(){
        createPanel();
    }

    
    public void createPanel() {

        gridLayout = new GridLayout(1, 1);
        this.setLayout(gridLayout);
        this.setPreferredSize(new Dimension(10,10));

        heartImage = ImageManager.loadImage ("Lives.png");
        image = new BufferedImage (10, 10, BufferedImage.TYPE_INT_RGB);

        //backgroundColour = new Color(179,163,152);
        //this.setBackground(backgroundColour);
}

public void draw(){
    Graphics2D imageContext = (Graphics2D) image.getGraphics();

    imageContext.drawImage(heartImage, 0, 0, null);	// draw the background image
    Graphics2D g2 = (Graphics2D) getGraphics();	// get the graphics context for the panel
    g2.drawImage(image, 0, 0, 10, 10, null);

    imageContext.dispose();
    g2.dispose();
}

}

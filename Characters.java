import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Characters {

   private JPanel panel;
   private int x;
   private int y;
   private int width;
   private int height;

   private BufferedImage character;
   private BufferedImage characterCopy;
   
   private BufferedImage image;  
   private String name;
   private Rectangle2D.Double characterRect;
   

   public Characters(JPanel p, Cookie c, String name, int x,int y, int width, int height){
    panel = p;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    
    this.name = name;
    character = ImageManager.loadBufferedImage(name);
    characterCopy = ImageManager.copyImage(character);
    image = new BufferedImage (400, 400, BufferedImage.TYPE_INT_RGB);


    copyToGray();
    characterRect = getBoundingRectangle();
    
   }

   private int toGray (int pixel) {

      int alpha, red, green, blue, gray;
    int newPixel;

    alpha = (pixel >> 24) & 255;
    red = (pixel >> 16) & 255;
    green = (pixel >> 8) & 255;
    blue = pixel & 255;

    // Calculate the value for gray

    gray = (red + green + blue) / 3;

    // Set red, green, and blue channels to gray

    red = green = blue = gray;
     
    //puts back pixels in their rightful places
    newPixel = blue | (green << 8) | (red << 16) | (alpha << 24);

    return newPixel;
 }

 private void copyToGray() {
   int imWidth = characterCopy.getWidth();
   int imHeight = characterCopy.getHeight();

       int [] pixels = new int[imWidth * imHeight];
       characterCopy.getRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);

   for (int i=0; i<pixels.length; i++) {
      pixels[i] = toGray(pixels[i]);
   }

       characterCopy.setRGB(0, 0, imWidth, imHeight, pixels, 0, imWidth);
}	


public void draw (Graphics2D g2, int i) {

   if (i == 1) {			// draw original (already in colour)
      g2.drawImage(character, x, y, width, height, null);
   }
   else
   if (i == 2) {			// draw copy (already in grayscale)
      g2.drawImage(characterCopy, x, y, width, height, null);
   }

}


/* 

   public void draw () {
    Graphics g = panel.getGraphics ();
    Graphics2D g2 = (Graphics2D) g;

    g2.drawImage(character, x, y, width, height,null);
    g.dispose();
 }   
 */



 public Rectangle2D.Double getBoundingRectangle(){
    return new Rectangle2D.Double (this.x, this.y, width, height);

   }

/* 
 public boolean isOnCharacter(int p, int q){
    //Rectangle2D.Double characterRect = getBoundingRectangle();

    return characterRect.contains(p,q);
 }*/

}

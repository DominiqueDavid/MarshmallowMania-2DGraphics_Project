import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.awt.Image;
import javax.swing.JPanel;

public class Milk {
   private JPanel panel;
   private int x;
   private int y;
   private int y2;
   private int width;
   private int height;

   private int dx;
   private int dy;

   private Rectangle2D.Double milk;

   private Color backgroundColour;
   private Dimension dimension;
   public Random random;
   private Image milkImage;
   private Image cmilkImage;
   private Image smilkImage;
   private static int flavour;

   public Milk(JPanel p, int x, int y){
    panel = p;
    this.x = x;
    this.y = y;

    dimension = panel.getSize();
    backgroundColour = panel.getBackground ();
   
    dx = 0;
    dy = 0;

    width = 40;
    height = 60;
    random = new Random();
    flavour = 1;
   

   milkImage = ImageManager.loadImage("Milk.png");
   cmilkImage = ImageManager.loadImage("ChocolateMilk.png");
   smilkImage = ImageManager.loadImage("StrawberryMilk.png");
   }




   public void draw(Graphics2D g2){
    if(flavour == 1){
      g2.drawImage(milkImage,x,y,width,height,null);
    }
    else if(flavour == 2){
      g2.drawImage(cmilkImage,x,y,width,height,null);
    }
   else if(flavour == 3){
      g2.drawImage(smilkImage,x,y,width,height,null);
   }
  
   }





   public void erase (){

    Graphics g = panel.getGraphics ();
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor (backgroundColour);
    g2.fill (new Rectangle2D.Double (x, y-10, width, height+10));

    g.dispose();
   }



   public void setLocation(){
    int panelWidth = panel.getWidth();
    x = random.nextInt(panelWidth - width);
    y = random.nextInt(300);
   }



   public Rectangle2D.Double getBoundingRectangle() {
    return new Rectangle2D.Double (x, y, width, height);
 }


 public void changeflavour(int choice){
   flavour = choice;
 }

 public int getFlavour(){
   return flavour;
 }
 

   public void move(){
    if(!panel.isVisible()) return;
    erase();
    setLocation();
    //draw();
   }

}

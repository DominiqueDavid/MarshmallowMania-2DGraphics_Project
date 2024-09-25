import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Image;
import javax.swing.JPanel;

public class Chocolate{
    private JPanel panel;

   private int x;
   private int y;

   private int width;
   private int height;

   private int topY;		// regenerates Marshmallow to this y coordinate

   private int dx;
   private int dy;		

   private Color backgroundColour;
   private Dimension dimension;

   boolean isRunning;
   Random random;
   private Milk milk;
   private Cookie cookie;
   ArrayList <SchemeMallow> mallows;
   private Rectangle2D.Double choco;
   private Image chocolateImage;

   private SoundManager soundManager;
   public Chocolate(JPanel p, int xPos, int yPos, Milk milk, Cookie cookie, ArrayList<SchemeMallow> mallows){
    panel = p;
    dimension = panel.getSize();
    backgroundColour = panel.getBackground ();

    x = xPos;
    y = yPos;
    topY = yPos;

    width = 30;
    height = 30;

    this.cookie = cookie;
    this.milk = milk;
    this.mallows = mallows;

    random = new Random();

    dx = 0;			
    dy = 10;
    soundManager = SoundManager.getInstance();
    chocolateImage = ImageManager.loadImage("ChocolateBar.png");
   }

   public void setLocation() {
    int panelWidth = panel.getWidth();
    x = random.nextInt (panelWidth - width);
    y = topY;
 }



 public void draw(Graphics2D g2){
    
    g2.drawImage(chocolateImage,x,y,width,height,null);
    //g.dispose();
 }


 public Rectangle2D.Double getBoundingRectangle(){
    return new Rectangle2D.Double (x, y, width, height);
   }



 public void erase () {
    Graphics g = panel.getGraphics ();
    Graphics2D g2 = (Graphics2D) g;

    g2.setColor (backgroundColour);
    g2.fill (new Rectangle2D.Double (x,y, width+5, height+5));
    g.dispose();
 }


 public void move() {

    
    if (!panel.isVisible ()) return ;
    x = x + dx;
    y = y + dy;
    int height = panel.getHeight();

    if (y > height) {
        setLocation();
    }


    boolean collision = this.collidesWithMilk();
         if(collision){
            milk.move();
            
         }

    boolean collision1 = this.collidesWithCookie();
    if(collision1){
       milk.changeflavour(2);
       soundManager.playClip("levelUP",false);
     }

     boolean collision3;
     for(SchemeMallow mallow: mallows){
        collision3 = this.collidesWithMallows(mallow);
        if(collision){
           // this.draw(); 
         }
     }
        
}



 public boolean collidesWithMilk() {
    Rectangle2D.Double chocoRect = getBoundingRectangle();
    Rectangle2D.Double milkRect = milk.getBoundingRectangle();
    
    return chocoRect.intersects(milkRect); 
 }


 public boolean collidesWithCookie() {
    Rectangle2D.Double chocoRect = getBoundingRectangle();
    Rectangle2D.Double cookieRect = cookie.getBoundingRectangle();
    
    return chocoRect.intersects(cookieRect); 
 }

 public boolean collidesWithMallows(SchemeMallow mallow) {
    Rectangle2D.Double chocoRect = getBoundingRectangle();
    Rectangle2D.Double mallowRect = mallow.getBoundingRectangle();
    return chocoRect.intersects(mallowRect); 
 }

 

}


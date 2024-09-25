import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.awt.Image;
import javax.swing.JPanel;

public class SchemeMallow{
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
   private SoundManager soundManager;

   boolean isRunning;
   Random random;
   private Milk milk;
   private Cookie cookie;
   private Rectangle2D.Double mallow;
   private Image marshmallowImage;
   private ScorePanel scorepanel;

   public SchemeMallow(JPanel p,ScorePanel spanel ,int xPos, int yPos, Milk milk, Cookie cookie){
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

    random = new Random();

    dx = 0;			
    dy = 10;

    scorepanel = spanel;
    soundManager = SoundManager.getInstance();
    marshmallowImage = ImageManager.loadImage("Marshmallow.png");
   }

   public void setLocation() {
    int panelWidth = panel.getWidth();
    x = random.nextInt (panelWidth - width);
    y = topY;
 }



 public void draw(Graphics2D g2){
    
    g2.drawImage(marshmallowImage,x,y,width,height,null);
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
       int gx = getX() + 60;
       int gy = getY();
       cookie.erase();
       cookie.bounce(gx,gy);
       soundManager.playClip("boing", false);
       scorepanel.updateLives();
     }
        
}


 public boolean collidesWithMilk() {
    Rectangle2D.Double mallowRect = getBoundingRectangle();
    Rectangle2D.Double milkRect = milk.getBoundingRectangle();
    
    return mallowRect.intersects(milkRect); 
 }


 public boolean collidesWithCookie() {
    Rectangle2D.Double mallowRect = getBoundingRectangle();
    Rectangle2D.Double cookieRect = cookie.getBoundingRectangle();
    
    return mallowRect.intersects(cookieRect); 
 }

 

 public int getX(){
    return x;
}

public int getY(){
    return y;
}



}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.swing.JPanel;

public class Cookie {

   private JPanel panel;
   private int x;
   private int y;
   public static final int width = 60;
   public static final int height = 60;

   private int dx;
   private int dy;

   private Rectangle2D.Double cookie;

   private Color backgroundColour;
   private Dimension dimension;
   private Milk milk;
   private BufferedImage cookieImage;
   private BufferedImage copy;
   private String imageName;

   private SoundManager soundManager;
   Graphics2D g2;

	int time, timeChange;


   public Cookie(JPanel p, int x, int y, Milk milk, int i){
    panel = p;
    this.x = x;
    this.y = y;

    dimension = panel.getSize();
    backgroundColour = panel.getBackground ();
    

    dx = 20;
    dy = 20;

   if(i == 1){
      imageName = "Cookie.png";
   }
   else if(i == 2){
      imageName = "Cookie2.png";
   }
   
    this.milk = milk;

    time = 0; 
    timeChange = 1;
    cookieImage = ImageManager.loadBufferedImage(imageName);
    soundManager = SoundManager.getInstance();
    copy = ImageManager.copyImage(cookieImage);
   }


   public void eraseImageParts(BufferedImage image, int interval){
      int imageWidth = image.getWidth();
      int imageHeight = image.getHeight();

      int [] pixels = new int[imageWidth *imageHeight];
      image.getRGB(0,0,imageWidth, imageHeight, pixels, 0, imageWidth);

      for(int i = 0; i < pixels.length; i = i + interval){
         pixels[i] = 0;
      }

      image.setRGB(0,0,imageWidth,imageHeight,pixels,0,imageWidth);
   }

    
   public int draw1(Graphics2D g2){
      if (time == 10)
			eraseImageParts(copy, 11);
		else
		if (time == 20)
			eraseImageParts(copy, 7);
		else
		if (time == 30)
			eraseImageParts(copy, 5);
		else
		if (time == 40)
			eraseImageParts(copy, 3);
		else
		if (time == 50)
			eraseImageParts(copy, 2);
		else
		if (time == 60)
			eraseImageParts(copy, 1);
  

		g2.drawImage(copy, x, y, width, height, null);

      return time;
 
   }


   public void update(){
      time = time + timeChange;
   }


   public void draw(Graphics2D g2){
    
    g2.drawImage(cookieImage,x,y,width,height,null);
       // g.dispose(); 
   }


   public Rectangle2D.Double getBoundingRectangle(){
    return new Rectangle2D.Double (x, y, width, height);
   }

   public void erase () {
   
   Graphics g = panel.getGraphics ();
   Graphics2D g2 = (Graphics2D) g;

    g2.setColor (backgroundColour);
    g2.fill (new Ellipse2D.Double (x, y, width, height));

    g.dispose();
 }


 public void move (int direction) {

    if (!panel.isVisible ()) return;
    
    dimension = panel.getSize();

    if (direction == 1) {	// move left
        x = x - dx;
    if (x < 0)
       x = dimension.width - width;
    }
    else if (direction == 2) {  	// move right
        x = x + dx;
    if (x + width > dimension.width)
       x = 0;
    }
    else if(direction == 3){// move up
        y = y - dy;
        if(y < 0){
            y = 0;
        }
    }
    else if(direction == 4){ // move down
        y = y + dy;
        if (y + width > dimension.height)
       y = dimension.height - height;

    }

 }
   

 public boolean collidesWithMilk() {
    Rectangle2D.Double cookieRect = getBoundingRectangle();
    Rectangle2D.Double milkRect = milk.getBoundingRectangle();
    
    return cookieRect.intersects(milkRect); 
 }

 

 public void bounce(int x, int y){
   this.x = x;
   this.y = y;
 }
    
}

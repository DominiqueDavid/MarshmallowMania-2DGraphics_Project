import java.util.ArrayList;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import java.util.Random;
import java.awt.image.*;


public class GamePanel extends JPanel implements Runnable {
    
    Cookie cookie;
    Milk milk;
    SchemeMallow mallow;
    Chocolate chocolate;
    Strawberry strawberry;
    Characters c1;
    Characters c2;
    ArrayList <SchemeMallow> mallows = new ArrayList<>();
    private static int score;
    private Image background;
    private Image characterBackground;
    private BufferedImage image;

    private Thread gameThread;
    private boolean isRunning;
    private boolean isGone;
    int count = 0;
    int x1,x2,y1,y2,width,height;
    private int cookieType = 1;
    
    ScorePanel scorepanel;
    private SoundManager soundManager;


    public GamePanel(ScorePanel spanel){ 
     score = 0;
     cookie = null;
     milk = null;
     chocolate = null;
     strawberry = null;
     c1 = null;
     c2 = null;
     isRunning = false;
     isGone = false;
     x1 = 150;
     y1 = 300;
     x2 = 300;
     y2 = 300;
     width = 100;
     height = 100;

     background = ImageManager.loadImage("background1.jpeg");
     characterBackground = ImageManager.loadImage("characterBackground.png");
     soundManager = SoundManager.getInstance();
     this.scorepanel = spanel;
     image = new BufferedImage (550, 700, BufferedImage.TYPE_INT_RGB);

    }

    public void createCharacters(){
        Graphics2D imageContext = (Graphics2D) image.getGraphics();
        imageContext.drawImage(characterBackground, 0, 0,550,700, null);	// draw the background image

       
        c1 = new Characters(this,cookie,"Cookie.png",x1,y1,100,100);
        c2 = new Characters(this,cookie,"Cookie2.png",x2,y2,100,100);
        c1.draw(imageContext, 2);
        c2.draw(imageContext, 2);

        Graphics2D g2 = (Graphics2D) getGraphics();	// get the graphics context for the panel
		g2.drawImage(image, 0, 0, 550, 700, null);

		imageContext.dispose();
		g2.dispose();
    }

    public void SwitchCharacters(int i){
        Graphics2D imageContext = (Graphics2D) image.getGraphics();
        imageContext.drawImage(characterBackground, 0, 0,550,700, null);	// draw the background image
        if (i == 1){
            cookieType = 1;
            c1.draw(imageContext,1);
            c2.draw(imageContext,2);
        }
        else if(i == 2){
            cookieType = 2;
            c1.draw(imageContext,2);
            c2.draw(imageContext,1);
        }

        Graphics2D g2 = (Graphics2D) getGraphics();	// get the graphics context for the panel
		g2.drawImage(image, 0, 0, 550, 700, null);

		imageContext.dispose();
		g2.dispose();

    }

    public boolean contains1(int clickX, int clickY){
        if( clickX >= x1 && clickX <= x1 + width && clickY >= y1 && clickY <= y1 + height){
           return true;
        }
        return false;
     }

     public boolean contains2(int clickX, int clickY){
        if( clickX >= x2 && clickX <= x2 + width && clickY >= y2 && clickY <= y2 + height){
           return true;
        }
        return false;
     }

    public void createGameEntities(){
        milk = new Milk(this,450,50);
        cookie = new Cookie(this,50,350,milk,cookieType);
        //mallow = new SchemeMallow(this,100,10 ,milk, cookie);
        int x = 90;
        int y = 10;
        for(int i= 0; i<3; i++ ){
            x+= 100;
            y+= 10;
            mallows.add(new SchemeMallow(this, scorepanel,x, y, milk, cookie));
        }
        chocolate = new Chocolate(this,50,1,milk,cookie,mallows);
        strawberry = new Strawberry(this, 150, 1, milk, cookie, mallows);
    }



    public void drawGameEntities(){

    Graphics2D imageContext = (Graphics2D) image.getGraphics();
    imageContext.drawImage(background,0,0,getWidth(),getHeight(),null);

    if(scorepanel.getLives() <= 0){
       int gone = cookie.draw1(imageContext);
       if(gone >= 70){
            isRunning = false;
            soundManager.stopClip("background");
            soundManager.playClip("gameOver", false);
       }
    }
    else{
        if(cookie != null){
            cookie.draw(imageContext);
            
        }

        if(milk != null){
            milk.draw(imageContext);
        }

        if(chocolate != null){
            chocolate.draw(imageContext);
        }

        if(strawberry != null){
            strawberry.draw(imageContext);
        }

        if(mallows != null){
           for( SchemeMallow mallow: mallows){
            mallow.draw(imageContext);
           }
        }
    }

        Graphics2D g2 = (Graphics2D) getGraphics();	// get the graphics context for the panel
		g2.drawImage(image, 0, 0, 550, 700, null);

		imageContext.dispose();
		g2.dispose();

    }

    public int updateGameEntities(int direction){
        if(cookie == null){
            return 0;
        }
        cookie.erase();
        cookie.move(direction);
        boolean collision = cookie.collidesWithMilk();
        if(collision){
            scorepanel.updatePoints(milk.getFlavour());
            soundManager.playClip("cookieDunk", false);
            milk.move();
         }
         return score;
    }
    
//moves the marshmallow,chocolate and strawberry down the screen gradually
    public void gameUpdate() {
        if(scorepanel.getLives() == 0){
            cookie.update();
        }
        else{
		for (SchemeMallow mallow : mallows) {
			 mallow.move();
		} 
        chocolate.move();
        strawberry.move();
    }
       
	}


    public void rain(){
        if(mallows != null){
            for( SchemeMallow mallow: mallows){
                gameThread = new Thread(this);
                isRunning = true;
                gameThread.start();
                soundManager.setVolume("background", 0.5f);
                soundManager.playClip("background", true);
                }
        }
        
    }

    public void run () {
        try {

            while (isRunning) {
                gameUpdate();
                drawGameEntities();
                Thread.sleep(115);	// increase value of sleep time to slow down fall   
              }
              
            DeleteEntities();
        }
        catch(InterruptedException e) {}
     }

    
    public void DeleteEntities(){
     cookie = null;
     milk = null;
     chocolate = null;
     strawberry = null;
     for(SchemeMallow mallow: mallows){
        mallow = null;
     }
  
    }

   


}

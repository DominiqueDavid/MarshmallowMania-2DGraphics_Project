import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScorePanel extends JPanel {
    // Label Declaration
    private JLabel score;
    private JLabel keyPressed;

    //TextField Declaration
    private JTextField scoreField;
    private JTextField keyField;

    private Color backgroundColour;
    GridLayout gridLayout;
    private static int points;
    private static int lives;

    private Image heartImage;
    private BufferedImage image;

    public ScorePanel(){
        Intialize();
        createPanel();
    }

    public void Intialize(){
        lives = 3;
          //Initialize Labels
        score = new JLabel("Cookies Dunked :)");
        keyPressed = new JLabel("Lives Remaining");

        //Initialize TextFields
        scoreField  = new JTextField(25);
        scoreField.setEditable(false);
        scoreField.setBackground(Color.WHITE);

        keyField = new JTextField(25);
        keyField.setEditable(false);
        keyField.setText("3");
        backgroundColour = new Color(205,250,219);
        keyField.setBackground(backgroundColour);
        keyField.setBackground(backgroundColour);
        
        
    }


    public void createPanel() {
        gridLayout = new GridLayout(2, 2);
        this.setLayout(gridLayout);
        this.add(score);
        this.add(scoreField);

                this.add(keyPressed);
                this.add(keyField);
               

        backgroundColour = new Color(179,163,152);
        this.setBackground(backgroundColour);

}





public void updatePoints(int flavour){
    String score;
 
    if(flavour == 1){ //vanilla
        points++;
        score = String.valueOf(points);
        scoreField.setText(score);
    }
    else if(flavour == 2){// chocolate
        points = points + 3;
        score = String.valueOf(points);
        scoreField.setText(score);
    }
    else if(flavour == 3){//strawberry
        points = points + 5;
        score = String.valueOf(points);
        scoreField.setText(score);
    }
  
}

public void updateLives(){
    lives = lives - 1;
    String health = String.valueOf(lives);
    keyField.setText(health);
    healthMonitor(lives);


}


public void healthMonitor(int i){
    Color field;
    String lives = String.valueOf(i);
    keyField.setText(lives);
    
    if(i == 3){
        field = new Color(205,250,219);
        keyField.setBackground(field);

    }
    else if(i == 2){
        field = new Color(246,253,195);
        keyField.setBackground(field);
    }
    else if(i == 1){
        field = new Color(255,207,150);
        keyField.setBackground(field);
    }
    else{
        field = new Color(255,128,128);
        keyField.setBackground(field);
        keyField.setText(" GAME OVER ");
    }
    
}

public int getLives(){
    return lives;
}

 }

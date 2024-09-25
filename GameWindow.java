import javax.swing.*;			
import java.awt.*;			
import java.awt.event.*;		

public class GameWindow extends JFrame implements ActionListener,KeyListener,MouseListener{

    // Label Declaration
    private JLabel score;
    private JLabel keyPressed;

    //TextField Declaration
    private JTextField scoreField;
    private JTextField keyField;

    //Button Declaration
    private JButton startB;
    private JButton clearB;
    private JButton character;
    
    //Panels
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel informationPanel;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    

    //Surface window
    Container window;

    //Game Features
    private int results;


    public GameWindow(){
        setTitle("Marshmallow Mania");
        setSize(650,900);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Color backgroundColour;
        
        

        //Initialize Labels
        score = new JLabel("Cookies Dunked :)");
        keyPressed = new JLabel("Lives Remaining");

        //Initialize TextFields
        scoreField  = new JTextField(25);
        scoreField.setEditable(false);
        scoreField.setBackground(Color.WHITE);

        keyField = new JTextField(25);
        keyField.setEditable(false);
        backgroundColour = new Color(205,250,219);
        keyField.setBackground(backgroundColour);
        keyField.setBackground(backgroundColour);

        //Intialize Buttons
        startB = new JButton("Start");
        clearB = new JButton("Close");
        character = new JButton("Choose Player");

            //Adding listeners to Buttons
            startB.addActionListener(this);
            clearB.addActionListener(this);
            character.addActionListener(this);





        //Creating Panels
        mainPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        mainPanel.setLayout(flowLayout);

        GridLayout gridLayout;
        scorePanel = new ScorePanel();

      //Information Panel
        informationPanel = new JPanel();
        gridLayout = new GridLayout(2, 2);
        informationPanel.setLayout(gridLayout);

                //Information Panel Components
                informationPanel.add(score);
                informationPanel.add(scoreField);

                informationPanel.add(keyPressed);
                informationPanel.add(keyField);

        backgroundColour = new Color(179,163,152);
        informationPanel.setBackground(backgroundColour);


        //Button Panel
        buttonPanel = new JPanel();
        gridLayout = new GridLayout(1,3);
        buttonPanel.setLayout(gridLayout);
        buttonPanel.setBackground(backgroundColour);

                 //Button Panel Components\\
                 buttonPanel.add(character);
                 buttonPanel.add(startB);
                 buttonPanel.add(clearB);
                 

        //Game Panel
        gamePanel = new GamePanel(scorePanel);
        backgroundColour = new Color(247,222,208);
        gamePanel.setBackground(backgroundColour);
        gamePanel.setPreferredSize(new Dimension(550, 700));
		

        
        //Decorate Main Panel
        mainPanel.add(scorePanel);
        mainPanel.add(gamePanel);
        mainPanel.add(buttonPanel);
        backgroundColour = new Color(226,191,179);
        mainPanel.setBackground(backgroundColour);

        gamePanel.addMouseListener(this);
        mainPanel.addKeyListener(this);

        

        //Add Main Panel to Window Surface
        window = getContentPane();
        window.add(mainPanel);

         
        
        //gamePanel.createGameEntities();
        setVisible(true);

    }










    //Listeners

    @Override
    public void actionPerformed(ActionEvent e) {
       String command = e.getActionCommand();
       

		if (command.equals(startB.getText())){
             gamePanel.createGameEntities();
             mainPanel.requestFocus();  //gives the main panel control of the screen
             gamePanel.drawGameEntities();
             gamePanel.rain();
        }
        else if (command.equals(clearB.getText())){
             System.exit(0);
         }
         else if(command.equals(character.getText())){
           // mainPanel.requestFocus();
            gamePanel.createCharacters();
         }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        //Displays which key is pressed, only after the main panel has control
        int keyCode = e.getKeyCode();
        String keyText = e.getKeyText(e.getKeyCode());
	    

        if (keyCode == KeyEvent.VK_RIGHT) {
			results = gamePanel.updateGameEntities(2);
			gamePanel.drawGameEntities();
		}

		if (keyCode == KeyEvent.VK_LEFT) {
			results = gamePanel.updateGameEntities(1);
			gamePanel.drawGameEntities();
		}

        if(keyCode == KeyEvent.VK_UP){
			results = gamePanel.updateGameEntities(3);
			gamePanel.drawGameEntities();
		}

        if(keyCode == KeyEvent.VK_DOWN){
			results = gamePanel.updateGameEntities(4);
			gamePanel.drawGameEntities();
		}

        


    }

    
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int i;

        if(gamePanel.contains1(x,y)){
            i = 1;
            gamePanel.SwitchCharacters(i);
            System.out.println("Chocolate Chip Cookie");
        }
        else if(gamePanel.contains2(x,y)){
            i = 2;
            gamePanel.SwitchCharacters(i);
            System.out.println("White Chocolate Chip Cookie");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }
       

    @Override
    public void keyReleased(KeyEvent e) {
     
    }

    

    
}
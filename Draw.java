import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;

public class Draw {
    char[][] board;
    Controller controller;

    //sets name of window
    JFrame frame = new JFrame("Hivolts");

    Player player;

    
    Draw(char[][] board, Player player, Controller controller) {
        this.board = board;
        this.player = player;
        this.controller = controller;
        setFrame();

        Work p = board();
        frame.add(p);

        KeyChecker kc = new KeyChecker(p, player, controller);
        p.addKeyListener(kc);
        frame.addKeyListener(kc);

        frame.setVisible(true);
    }

    //sets frame size
    void setFrame() {
        frame.setSize(1220, 1000);
        frame.getContentPane().add(new Work(controller));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(true);
    }

    Work board() {
        return new Work(this.controller);
    }
}


class Work extends JPanel {
    char[][] board;
    Controller controller;

    BufferedReader mho = null;

    enum State {
        start,
        inGame,
        lose,
        win
    }

    State s;

    public Work(Controller controller) {
        this.board = controller.board;
        this.controller = controller;

        s = State.start;
    }
    
    //paints the game to start
    public void paintStart(Graphics g) {

        paintGame(g);
    }

    //this is where we paint the text on the right side of the screen
    public void paintGame(Graphics g) {
    	Color orangeColor = new Color(255, 165, 0);
		g.setColor(Color.black);
		g.fillRect(0, 0, 10000, 10000);
		
		//side text on the right side of applet
		g.setColor(orangeColor);
        g.drawString("Welcome to Hivolts", 800, 50);
        g.drawString("You can use the qweasdzxc keys to move in a square pattern", 800, 75);
        g.drawString("The Mhos will try to follow you", 800, 100);
        g.drawString("You can not attack them but if they walk into a wall they will die", 800, 125);
        g.drawString("You too will die if you hit a wall or an Mho", 800, 150);
        g.drawString("You can use the j key to jump to a random location \n", 800, 175);
        g.drawString("This random location can be a Mho but not a fence", 800, 200);
        g.drawString("PRESS A KEY TO MOVE", 800, 250);
        
        //sets size of game. keep at 50 for now
        int size = 50;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                switch (board[i][j]) {
                    case 'f':
                        g.setColor(orangeColor);
                        //g.drawRect(size + j * size, size + i * size, size, size);
                        
                        //FENCE GRAPHICS
                        //LEFT AND RIGHT POLES
                        g.fillRect(size*j+9+size,size*i+5+size,4,40);
						g.fillRect(size*j+42+size,size*i+5+size,4,40);

						//UPPER STATIC LINES
						g.drawLine(108+size*j-98+size, 80+size*i-50+size, 110+size*j-98+size, 75+size*i-50+size);
						g.drawLine(110+size*j-98+size, 75+size*i-50+size, 120+size*j-98+size, 80+size*i-50+size);
						g.drawLine(120+size*j-98+size, 80+size*i-50+size, 140+size*j-98+size, 75+size*i-50+size);
						g.drawLine(140+size*j-98+size, 75+size*i-50+size, 140+size*j-98+size, 82+size*i-50+size);
						//LOWER STATIC LINES
						g.drawLine(108+size*j-98+size, 64+size*i-50+size, 110+size*j-98+size, 62+size*i-50+size);
						g.drawLine(110+size*j-98+size, 62+size*i-50+size, 120+size*j-98+size, 64+size*i-50+size);
						g.drawLine(120+size*j-98+size, 64+size*i-50+size, 140+size*j-98+size, 60+size*i-50+size);
						g.drawLine(140+size*j-98+size, 60+size*i-50+size, 140+size*j-98+size, 66+size*i-50+size);
						
                        break;
                    case 'e':
                    	//MHO GRAPHICS
                        g.setColor(orangeColor);
                        //MHO CIRCLE
                        g.fillOval(size + j * size + 3, size + i * size + 3, size - 8, size - 8);
    					g.setColor(Color.black);
    					//MHO EYES
    					g.fillOval(size + j * size + 11, size + i * size + 11, size - 39, size - 39);
    					g.fillOval(size + j * size + 26, size + i * size + 11, size - 39, size - 39);
    					//MHO FROWNY FACE :(
    					g.drawArc(size + j * size + 14, size + i * size + 32, 20, 10, -180, -180); 
    					
                        break;
                    case 'c':
                    	
                    	//OUR CHARACTER GRAPHICS
                        g.setColor(orangeColor);
                        //CHARACTER CIRCLE
                        g.drawOval(size + j * size + 3, size + i * size + 3, size - 8, size - 8);
                        //g.setColor(Color.black);
                        //CHARACTER EYES
                        g.fillOval(size + j * size + 11, size + i * size + 11, size - 39, size - 39);
                        g.fillOval(size + j * size + 26, size + i * size + 11, size - 39, size - 39);
                        //CHARACTER SMILE :)
                        g.drawArc(size + j * size + 14, size + i * size + 26, 20, 10, 180, 180);
                       
                        break;
                }
            }
        }

        //there are 12 mhos left
        g.drawString("  There are " + controller.countMhos() + " mhos left", size, 13 * size + 20);
        //you have had 0 moves
        g.drawString("  You have had " + controller.moves + " moves", size, 13 * size + 45);
    }

    //game over method
    public void paintLose(Graphics g) {
    	Color orangeColor = new Color(255, 165, 0);
    	g.setColor(Color.black);
    	g.fillRect(0, 0, 10000, 10000);
    	g.setColor(orangeColor);
        g.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        g.drawString("Game over, better luck next time", 400, 400);
    }
    
    //game won method
    public void paintWin(Graphics g) {
    	Color orangeColor = new Color(255, 165, 0);
    	g.setColor(Color.black);
    	g.fillRect(0, 0, 10000, 10000);
    	g.setColor(orangeColor);
        g.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        g.drawString("You Win!!", 400, 400);
    }

    @Override
    public void paintComponent(Graphics g) {
        switch (s) {
        	//for when game is starting
            case start:
                paintStart(g);
                break;
            //when in game
            case inGame:
                paintGame(g);
                break;
            //when we lose
            case lose:
                paintLose(g);
                break;
            //when we win!
            case win:
                paintWin(g);
                break;
        }
    }
}

class KeyChecker extends KeyAdapter {
    private final Player player;
    private final Controller controller;
    Work work;

    Set<Character> characters = new HashSet<>();

    KeyChecker(Work work, Player player, Controller controller) {
        this.work = work;
        this.player = player;
        this.controller = controller;

        //adds the different keys we are able to use
        characters.add('q');
        characters.add('w');
        characters.add('e');
        characters.add('a');
        characters.add('s');
        characters.add('d');
        characters.add('z');
        characters.add('x');
        characters.add('c');
        characters.add('j');
    }

    @Override
    public void keyPressed(KeyEvent event) {
        char key = event.getKeyChar();

        if (characters.contains(key)) {
            player.move(work.board, key);
            //when every other key is pressed mhos move
            //if character jumps mhos do not move
            if (key != 'j') {
                controller.turn();
            }
        }

        if (player.isAlive && !work.s.equals(Work.State.win)) {
            work.repaint();
            
        } else if (work.s.equals(Work.State.inGame)) {
            work.s = Work.State.lose;
            work.repaint();
            return;
            //if game is lost we can press space to restart
        } else if (work.s.equals(Work.State.lose)) {
            controller.regenerateGame();
            work.s = Work.State.inGame;
            work.repaint();
            return;
            //if game is won we can press space to restart
        } else if (work.s.equals(Work.State.win)) {
            controller.regenerateGame();
            work.s = Work.State.inGame;
            work.repaint();
            return;
        }

        if (work.s.equals(Work.State.start)) {
            work.s = Work.State.inGame;
        }

        //waits until all mhos are dead and then game is won
        if (controller.countMhos() == 0) {
            work.s = Work.State.win;
        }
        //if player is dead game is lost
        if (!controller.player.isAlive) {
            work.s = Work.State.lose;
        }
    }
}
import java.util.Scanner;

public class Controller {
    public static char[][] board = new char[12][12]; // c = player, f = fence, e = Mho, v = empty
    Player player = new Player();
    Mhos mhos = new Mhos();
    public static int mhoscount = 12;
    public static int fencescount = 20;
    Draw swingUI;

    boolean swing; // true = use swing to draw the board, false = output the board in console

    int moves = 0; // moves done
    int mhosLeft = 12; // amount of mhos still in the game
    //swing is a boolean for turns
    Controller(boolean swing) {
        this.swing = swing;

        regenerateGame();
        //swing is used 
        if (swing) {
            swingUI = new Draw(board, player, this);
        }
    }

    //everytime player loses/wins new game is made
    public void regenerateGame() {
        moves = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                board[i][j] = '\u0000';
            }
        }

        //makes outer fences
        for (int i = 0; i < 12; i++) {
            board[i][0] = 'f';
            board[i][11] = 'f';
            board[0][i] = 'f';
            board[11][i] = 'f';
        }

        // fences placement
        int amountCreated = 0;
        int randy = 2;
        int randx = 0;
        while (amountCreated < fencescount) {
            int rand1 = random(1, 10);
            randx =randx + rand1;
            if (randx > 10) {
            	randx = randx - 10;
            	randy++;
            }
            if (randy>11) {
            	randy=2;
            }
            if (board[randx+1][randy] != 'f') {
                amountCreated++;
                board[randx+1][randy] = 'f';
            }
        }

        // Mhos placement
        randy = 2;
        randx = 0;
        amountCreated = 0;
        while (amountCreated < mhoscount) {
            int rand1 = random(1, 10);
            randx =randx + rand1;
            if (randx > 10) {
            	randx = randx - 10;
            	randy++;
            }
            if (randy>11) {
            	randy=2;
            }
            if (board[randx+1][randy] != 'f' && board[randx+1][randy] != 'e') {
                amountCreated++;
                board[randx+1][randy] = 'e';
            }
        }

        // Character placement
        amountCreated = 0;
        randy = 2;
        randx = 0;
        while (amountCreated < 1) {
            int rand1 = random(5, 10);
            randx =randx + (3 * rand1);
            while (randx > 10) {
            	randx = randx - 10;
            	randy++;
            }
            if (randy>11) {
            	randy=2;
            }
            if (board[randx+1][randy] != 'f' && board[randx+1][randy] != 'e') {
                amountCreated++;
                board[randx+1][randy] = 'c';
            }
        }

        // Empty location placement
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (board[i][j] == '\u0000') {
                    board[i][j] = 'v';
                }
            }
        }

        player = new Player();
        mhos = new Mhos();
    }

    private int random(int start, int end) {
        // Generate a random number from start to end inclusive
        int diff = end - start + 1;
        return ((int) Math.floor(Math.random() * diff)) + start;
    }

    public void turn() {
        // Do one turn
        if (!swing) { // If the game is to be played through console
            Scanner in = new Scanner(System.in);
            board = player.move(board, in.next().charAt(0));
        }

        moves++;

        if (board == null) {
            return;
        } // If the player is blind and walks into a wall on his own accord

        board = mhos.move(board); // Let the enemies move
    }

    public int countMhos() {
        // Counts the Mhos on the board
        int ans = 0;
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (board[i][j] == 'e') {
                    ans++;
                }
            }
        }
        mhosLeft = ans;
        return ans;
    }

    public void print() {
        // Prints the board on the console
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (board[i][j] != 'v') {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        System.out.println("Enemies left " + mhosLeft);

    }

    public void gameLoop() {
        // Loops the game until the player dies or wins
        while (player.isAlive) {
            countMhos(); // Count the Mhos for the draw/print function
            if (!swing) {
                print(); // Print in console
                turn(); // Let the player and Mhos move
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void gameLoop(int maxTurns) {
        // Loops the game for a set amount of turns, until the player dies, or the player wins
        while (player.isAlive) {
            countMhos(); // Count the Mhos for the draw/print function
            if (!swing) {
                // Print in console
                print();
            }
            turn(); // Let the player and Mhos move
        }
    }
}
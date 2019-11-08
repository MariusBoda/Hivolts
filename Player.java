import java.util.Random;

public class Player {
	boolean isAlive = true;

	char[][] move(char[][] board, char move) {
		printboard(board);
		//prints new line
		System.out.println("\n");
		//prints the board
		System.out.println(move);
		int playerRow = -1;
		int playerCol = -1;

		for (int i = 0; i <= 10; i++) { // finding out where the player is
			for (int j = 0; j <= 10; j++) {
				if (board[i][j] == 'c') {
					//sets playerRow to wherever it is
					//same with column
					playerRow = i;
					playerCol = j;
				}
			}
		}
		
		
		isAlive = true;
		//this means player is inside or on a fence
		//so the boolean of being alive will become false
		//will change game state to loss
		if (playerCol == -1) {
			isAlive = false;
			return board;
		}

		board[playerRow][playerCol] = 'v';
		//same thing as above
		if (playerCol == -1) {
			isAlive = false;
			return null;
		}
		//sets player row and col again to -1 and -1
		int newCol = -1;
		int newRow = -1;
		//if player clicks q then the player moves up and left
		if (move == 'q') {
			newCol = playerCol - 1;
			newRow = playerRow - 1;
		}
		//if player clicks w then the player moves up
		//newCol is set to the old playerCol since we 
		//are not moving horizontally
		if (move == 'w') {
			newCol = playerCol;
			newRow = playerRow - 1;

		}
		//if player clicks e then the character goes up and right
		if (move == 'e') {
			newCol = playerCol + 1;
			newRow = playerRow - 1;

		}
		if (move == 'a') {
			newCol = playerCol - 1;
			newRow = playerRow;
		}
		if (move == 's') {
			newCol = playerCol;
			newRow = playerRow;
		}
		if (move == 'd') {
			newCol = playerCol + 1;
			newRow = playerRow;
		}
		if (move == 'z') {
			newCol = playerCol - 1;
			newRow = playerRow + 1;
		}
		if (move == 'x') {
			newCol = playerCol;
			newRow = playerRow + 1;
		}
		if (move == 'c') {
			newCol = playerCol + 1;
			newRow = playerRow + 1;
		}
		if (move == 'j') {
			boolean jump = true;
			while (jump) {
				int newCol1 = random(10,1);
				int newRow1 = random(10,1);
				if (Controller.board[newRow1][newCol1] != 'f') {
					newCol = newCol1;
					newRow = newRow1;
					jump = false;
				}
			}

		}
				

		if (board[newRow][newCol] != 'v' && board[newRow][newCol] != 'c') {
			isAlive = false;
			board[playerRow][playerCol] = 'v';
		} else {
			board[playerRow][playerCol] = 'v';
			board[newRow][newCol] = 'c';
		}

		//     System.out.println("test");


		return board;
	}

	void printboard(char[][] board) {
		//     System.out.println("from print board hello");
		int boardlength = 12;
		for (int i=0; i<boardlength; i++) {
			//    for (int j=0; j<boardlength; j++) {
			System.out.println(board[i]);
			//  }
		}

	}

	int random(int end, int start) {
		int diff = end - start + 1;
		return ((int) Math.floor(Math.random() * diff)) + start;
	}

}
import java.util.Random;

public class Player {
	boolean isAlive = true;

	char[][] move(char[][] board, char move) {
		printboard(board);
		System.out.println("\n");
		System.out.println(move);
		int playerRow = -1;
		int playerCol = -1;

		for (int i = 0; i <= 10; i++) { // finding out where the player is
			for (int j = 0; j <= 10; j++) {
				if (board[i][j] == 'c') {
					playerRow = i;
					playerCol = j;
				}
			}
		}

		isAlive = true;

		if (playerCol == -1) {
			isAlive = false;
			return board;
		}

		board[playerRow][playerCol] = 'v';

		if (playerCol == -1) {
			isAlive = false;
			return null;
		}

		int newCol = -1;
		int newRow = -1;

		if (move == 'q') {
			newCol = playerCol - 1;
			newRow = playerRow - 1;
		}
		if (move == 'w') {
			newCol = playerCol;
			newRow = playerRow - 1;

		}
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
				if (Controller.board[newCol1][newRow1] == 'f') {
					newCol1 = random(10,1);
					newRow1 = random(10,1); 
				}
				else if (Controller.board[newCol1][newRow1] != 'f') {
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
public class Mhos {
    //c = player, f = fence, e = mho, v = vacant
    char[][] move(char[][] board) {
        int playerRow = -1;
        int playerColumn = -1;

        for (int i = 0; i <= 10; i++) { // finding out where the player is
            for (int j = 0; j <= 10; j++) {
                if (board[i][j] == 'c') {
                    playerRow = i;
                    playerColumn = j;
                }
            }
        }

        for (int i = 0; i <= 10; i++){
            for (int j=0;j<=10;j++) {
                if (board[i][j] == 'e') {
                    int newI = 0;
                    int newJ = 0;

                    if (playerRow < i && playerColumn == j) {
                        // mho is directly above player. (move down.)
                        newI = i - 1;
                        newJ = j;
                    } else if (playerRow > i && playerColumn == j) {
                        // mho is directly below player. (move up.)
                        newI = i + 1;
                        newJ = j;
                    } else if (playerRow == i && playerColumn < j) {
                        // mho is directly to the left of the player. (move right.)
                        newI = i;
                        newJ = j - 1;
                    } else if (playerRow == i && playerColumn > j) {
                        // mho is directly to the right of the player. (move left.)
                        newI = i;
                        newJ = j + 1;
                    } else if (playerRow > i && playerColumn > j && playerRow - i == playerColumn - j) {
                        // mho is to the left and below the player. (move right and up.)
                        newI = i + 1;
                        newJ = j + 1;
                    } else if (playerRow > i && playerColumn < j && playerRow - i == -(playerColumn - j)) {
                        // mho is to the right and below the player. (move left and up.)
                        newI = i + 1;
                        newJ = j - 1;
                    } else if (playerRow < i && playerColumn > j && -(playerRow - i) == playerColumn - j) {
                        // mho is to the left and above the player. (move right and down.)
                        newI = i - 1;
                        newJ = j + 1;
                    } else if (playerRow < i && playerColumn < j && -(playerRow - i) == -(playerColumn - j)) {
                        // mho is to the right and above the player. (move left and down.)
                        newI = i - 1;
                        newJ = j - 1;
                    }else if ((Math.abs(playerRow - i)) > Math.abs(playerColumn - j)) {
                        // vertical distance > horizontal distance.
                        if (playerRow > i) {
                            // mho is below the player. (move up.)
                            newI = i + 1;
                            newJ = j;
                        }
                        else {
                            // mho is below the player. (move down.)
                            newI = i-1;
                            newJ = j;
                        }
                    } else {
                        // horizontal distance > vertical distance.
                        if (playerColumn > j) {
                            // mho is to the left of the player. (move right.)
                            newI = i;
                            newJ = j+1;
                        } else {
                            // mho is to the right of the player. (move left.)
                            newI = i;
                            newJ = j-1;
                        }
                    }

                    if (board[newI][newJ] != 'f') {
                        board[newI][newJ] = 't';
                    }

                    board[i][j] = 'v';
                }
            }
        }

        for (int i = 0; i <= 10; i++) { // finding out where the player is
            for (int j = 0; j <= 10; j++) {
                if (board[i][j] == 't') {
                    board[i][j] = 'e';
                }
            }
        }

        return board;
    }
}
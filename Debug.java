
/* A class that outputs several facts about the board in console */

public class Debug {
    char[][] board = new char[12][12];

    public void setBoard(char[][] board) {
        this.board = board.clone();
    }

    public int count(char c) {
        int ans = 0;

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (board[i][j] == c) {
                    ans++;
                }
            }
        }

        System.out.println("There are " + ans + " " + c);
        return ans;
    }
}
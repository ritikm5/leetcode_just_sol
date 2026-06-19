class Solution {
    private static final int[][] DIRS = {
        {-1,-1}, {-1,0}, {-1,1},
        {0,-1},          {0,1},
        {1,-1},  {1,0},  {1,1}
    };

    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        // First pass: mark transitions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int liveNeighbors = 0;

                for (int[] d : DIRS) {
                    int r = i + d[0];
                    int c = j + d[1];

                    if (r >= 0 && r < m &&
                        c >= 0 && c < n &&
                        (board[r][c] == 1 || board[r][c] == 2)) {
                        liveNeighbors++;
                    }
                }

                if (board[i][j] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[i][j] = 2; // live -> dead
                    }
                } else {
                    if (liveNeighbors == 3) {
                        board[i][j] = 3; // dead -> live
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                } else if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }
}

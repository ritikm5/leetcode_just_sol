class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        // Mark border-connected O's
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
        }

        // Flip surrounded O's and restore safe ones
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col) {
        int m = board.length;
        int n = board[0].length;

        if (row < 0 || row >= m || col < 0 || col >= n
                || board[row][col] != 'O') {
            return;
        }

        board[row][col] = 'T';

        dfs(board, row + 1, col);
        dfs(board, row - 1, col);
        dfs(board, row, col + 1);
        dfs(board, row, col - 1);
    }
}

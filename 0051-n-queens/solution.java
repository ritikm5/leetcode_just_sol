class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        
        // Initialize board with empty cells
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // row + col
        boolean[] diag2 = new boolean[2 * n - 1]; // row - col + n - 1
        
        backtrack(0, board, cols, diag1, diag2, result, n);
        return result;
    }
    
    private void backtrack(int row, char[][] board, boolean[] cols, 
                          boolean[] diag1, boolean[] diag2, 
                          List<List<String>> result, int n) {
        
        if (row == n) {
            // Found a valid solution
            List<String> solution = new ArrayList<>();
            for (char[] r : board) {
                solution.add(new String(r));
            }
            result.add(solution);
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (cols[col] || diag1[row + col] || diag2[row - col + n - 1]) {
                continue;
            }
            
            // Place queen
            board[row][col] = 'Q';
            cols[col] = true;
            diag1[row + col] = true;
            diag2[row - col + n - 1] = true;
            
            // Recur to next row
            backtrack(row + 1, board, cols, diag1, diag2, result, n);
            
            // Backtrack
            board[row][col] = '.';
            cols[col] = false;
            diag1[row + col] = false;
            diag2[row - col + n - 1] = false;
        }
    }
}

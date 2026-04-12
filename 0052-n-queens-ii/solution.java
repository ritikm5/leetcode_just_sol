class Solution {
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // row + col
        boolean[] diag2 = new boolean[2 * n - 1]; // row - col + n - 1
        
        return backtrack(0, cols, diag1, diag2, n);
    }
    
    private int backtrack(int row, boolean[] cols, boolean[] diag1, 
                         boolean[] diag2, int n) {
        
        if (row == n) {
            return 1; // Found one valid solution
        }
        
        int count = 0;
        
        for (int col = 0; col < n; col++) {
            if (cols[col] || diag1[row + col] || diag2[row - col + n - 1]) {
                continue;
            }
            
            cols[col] = true;
            diag1[row + col] = true;
            diag2[row - col + n - 1] = true;
            
            count += backtrack(row + 1, cols, diag1, diag2, n);
            
            cols[col] = false;
            diag1[row + col] = false;
            diag2[row - col + n - 1] = false;
        }
        
        return count;
    }
}

class Solution {
    private int[][][] memo;
    private int[] boxes;

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        this.boxes = boxes;
        memo = new int[n][n][n];
        return dfs(0, n - 1, 0);
    }

private int dfs(int l, int r, int k) {
    if (l > r) return 0;

    while (r > l && boxes[r] == boxes[r - 1]) {
        r--;
        k++;
    }

    if (memo[l][r][k] != 0)
        return memo[l][r][k];

    int ans = dfs(l, r - 1, 0) + (k + 1) * (k + 1);

    for (int i = l; i < r; i++) {
        if (boxes[i] == boxes[r]) {
            ans = Math.max(ans,
                    dfs(l, i, k + 1) +
                    dfs(i + 1, r - 1, 0));
        }
    }

    memo[l][r][k] = ans;
    return ans;
}
}

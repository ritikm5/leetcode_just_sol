class Solution {
    private int tilt = 0;

    public int findTilt(TreeNode root) {
        dfs(root);
        return tilt;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftSum = dfs(node.left);
        int rightSum = dfs(node.right);

        tilt += Math.abs(leftSum - rightSum);

        return leftSum + rightSum + node.val;
    }
}

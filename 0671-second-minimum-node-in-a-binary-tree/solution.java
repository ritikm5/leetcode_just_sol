class Solution {
    private long secondMin = Long.MAX_VALUE;
    private int min;

    public int findSecondMinimumValue(TreeNode root) {
        min = root.val;
        dfs(root);

        return secondMin == Long.MAX_VALUE ? -1 : (int) secondMin;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.val > min) {
            secondMin = Math.min(secondMin, node.val);
            return; 
        }

        dfs(node.left);
        dfs(node.right);
    }
}

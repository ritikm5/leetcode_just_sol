/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }

        dfs(root, val, 1, depth);
        return root;
    }

    private void dfs(TreeNode node, int val, int level, int depth) {
        if (node == null) return;

        if (level == depth - 1) {
            TreeNode oldLeft = node.left;
            TreeNode oldRight = node.right;

            node.left = new TreeNode(val);
            node.right = new TreeNode(val);

            node.left.left = oldLeft;
            node.right.right = oldRight;

            return;
        }

        dfs(node.left, val, level + 1, depth);
        dfs(node.right, val, level + 1, depth);
    }
}

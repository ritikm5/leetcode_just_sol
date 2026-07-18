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
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);

        int rows = height + 1;
        int cols = (1 << (height + 1)) - 1;

        List<List<String>> res = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add("");
            }
            res.add(row);
        }

        fill(root, res, 0, (cols - 1) / 2, height);

        return res;
    }

    private void fill(TreeNode node, List<List<String>> res, int row, int col, int height) {
        if (node == null) return;

        res.get(row).set(col, String.valueOf(node.val));

        int offset = 1 << (height - row - 1);

        if (node.left != null) {
            fill(node.left, res, row + 1, col - offset, height);
        }

        if (node.right != null) {
            fill(node.right, res, row + 1, col + offset, height);
        }
    }

    private int getHeight(TreeNode root) {
        if (root == null) return -1;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}

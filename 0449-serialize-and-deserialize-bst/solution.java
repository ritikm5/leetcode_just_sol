/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Serialize
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(TreeNode node, StringBuilder sb) {
        if (node == null) return;

        sb.append(node.val).append(",");
        preorder(node.left, sb);
        preorder(node.right, sb);
    }

    private int index = 0;

    // Deserialize
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;

        String[] arr = data.split(",");
        int[] preorder = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            preorder[i] = Integer.parseInt(arr[i]);
        }

        index = 0;
        return build(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode build(int[] preorder, int min, int max) {
        if (index == preorder.length)
            return null;

        int val = preorder[index];

        if (val < min || val > max)
            return null;

        index++;

        TreeNode root = new TreeNode(val);

        root.left = build(preorder, min, val);
        root.right = build(preorder, val, max);

        return root;
    }
}

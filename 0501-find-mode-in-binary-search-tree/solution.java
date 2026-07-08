class Solution {

    private Integer prev = null;
    private int count = 0;
    private int maxCount = 0;
    private List<Integer> modes = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        inorder(root);

        int[] ans = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            ans[i] = modes.get(i);
        }
        return ans;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        if (prev != null && prev == node.val) {
            count++;
        } else {
            count = 1;
        }

        if (count > maxCount) {
            maxCount = count;
            modes.clear();
            modes.add(node.val);
        } else if (count == maxCount) {
            modes.add(node.val);
        }

        prev = node.val;

        inorder(node.right);
    }
}

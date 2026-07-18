import java.util.*;

class Solution {

    private Map<String, Integer> map = new HashMap<>();
    private List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serialize(root);
        return ans;
    }

    private String serialize(TreeNode node) {
        if (node == null) {
            return "N";
        }

        String serial = node.val + "," +
                        serialize(node.left) + "," +
                        serialize(node.right);

        int count = map.getOrDefault(serial, 0);

        if (count == 1) {
            ans.add(node);
        }

        map.put(serial, count + 1);

        return serial;
    }
}

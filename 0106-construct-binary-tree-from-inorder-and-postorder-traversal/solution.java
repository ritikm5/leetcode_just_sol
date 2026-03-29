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

import java.util.*;

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i);
        
        return build(inorder, 0, inorder.length - 1,
                     postorder, 0, postorder.length - 1,
                     inorderMap);
    }
    
    private TreeNode build(int[] inorder, int inStart, int inEnd,
                           int[] postorder, int postStart, int postEnd,
                           Map<Integer, Integer> inorderMap) {
        
        if (inStart > inEnd || postStart > postEnd) return null;
        
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        
        int rootIndex = inorderMap.get(rootVal);
        int leftSize = rootIndex - inStart;
        
        root.left = build(inorder, inStart, rootIndex - 1,
                          postorder, postStart, postStart + leftSize - 1,
                          inorderMap);
        
        root.right = build(inorder, rootIndex + 1, inEnd,
                           postorder, postStart + leftSize, postEnd - 1,
                           inorderMap);
        
        return root;
    }
}

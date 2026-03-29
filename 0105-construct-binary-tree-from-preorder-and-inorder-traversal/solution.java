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
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i);
        
        return build(preorder, 0, preorder.length - 1, 
                     inorder, 0, inorder.length - 1, 
                     inorderMap);
    }
    
    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd,
                           Map<Integer, Integer> inorderMap) {
        
        if (preStart > preEnd || inStart > inEnd) return null;
        
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        
        int rootIndex = inorderMap.get(rootVal);
        int leftSize = rootIndex - inStart;
        
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                          inorder, inStart, rootIndex - 1, inorderMap);
        
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                           inorder, rootIndex + 1, inEnd, inorderMap);
        
        return root;
    }
}

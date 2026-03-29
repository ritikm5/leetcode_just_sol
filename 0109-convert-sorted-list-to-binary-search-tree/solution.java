/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

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
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
    
        int n = countNodes(head);
        ListNode[] headRef = new ListNode[]{head};
        return buildTree(headRef, n);
    }
    
    private int countNodes(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
    
    private TreeNode buildTree(ListNode[] headRef, int n) {
        if (n <= 0) return null;
        
        TreeNode left = buildTree(headRef, n / 2);
        TreeNode root = new TreeNode(headRef[0].val);
        root.left = left;
        headRef[0] = headRef[0].next;
        root.right = buildTree(headRef, n - n / 2 - 1);
        return root;
    }
}

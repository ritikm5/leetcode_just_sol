import java.util.Random;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    private ListNode head;
    private Random rand;

    public Solution(ListNode head) {
        this.head = head;
        this.rand = new Random();
    }

    public int getRandom() {
        int result = 0;
        int count = 0;

        ListNode curr = head;

        while (curr != null) {
            count++;

            if (rand.nextInt(count) == 0) {
                result = curr.val;
            }

            curr = curr.next;
        }

        return result;
    }
}

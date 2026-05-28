class Solution {
    public ListNode partition(ListNode head, int x) {

        // Dummy nodes for two partitions
        ListNode smallerHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);

        ListNode smaller = smallerHead;
        ListNode greater = greaterHead;

        while (head != null) {

            if (head.val < x) {
                smaller.next = head;
                smaller = smaller.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }

            head = head.next;
        }

        greater.next = null;
        smaller.next = greaterHead.next;

        return smallerHead.next;
    }
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Dummy node before the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode current = head;

        while (current != null) {

            if (current.next != null && current.val == current.next.val) {

                while (current.next != null && current.val == current.next.val) {
                    current = current.next;
                }
                prev.next = current.next;
            } else {
                prev = prev.next;
            }

            current = current.next;
        }

        return dummy.next;
    }
}

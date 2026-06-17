class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Find middle
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        ListNode secondHalf = reverse(slow);

        ListNode firstHalf = head;
        ListNode secondPtr = secondHalf;

        while (secondPtr != null) {
            if (firstHalf.val != secondPtr.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondPtr = secondPtr.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }
}

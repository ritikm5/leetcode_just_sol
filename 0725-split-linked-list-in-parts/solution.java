class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {

        ListNode[] ans = new ListNode[k];

        // Step 1: Find length
        int length = 0;
        ListNode curr = head;

        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // Step 2: Calculate sizes
        int base = length / k;
        int extra = length % k;

        curr = head;

        // Step 3: Split the list
        for (int i = 0; i < k; i++) {

            if (curr == null) {
                ans[i] = null;
                continue;
            }

            ans[i] = curr;

            int partSize = base + (extra > 0 ? 1 : 0);

            if (extra > 0)
                extra--;

            for (int j = 1; j < partSize; j++) {
                curr = curr.next;
            }

            ListNode nextPart = curr.next;
            curr.next = null;
            curr = nextPart;
        }

        return ans;
    }
}

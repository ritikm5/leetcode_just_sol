class Solution {

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--; // We start from number 1

        while (k > 0) {
            long steps = countSteps(n, curr, curr + 1);

            if (steps <= k) {
                // Skip the entire subtree
                curr++;
                k -= steps;
            } else {
                // Go deeper into the subtree
                curr *= 10;
                k--;
            }
        }

        return curr;
    }

    private long countSteps(int n, long first, long last) {
        long steps = 0;

        while (first <= n) {
            steps += Math.min((long) n + 1, last) - first;
            first *= 10;
            last *= 10;
        }

        return steps;
    }
}

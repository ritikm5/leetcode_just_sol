class Solution {
    public int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();

        // Step 1: Find first decreasing digit
        int i = digits.length - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }

        if (i < 0) {
            return -1;
        }

        // Step 2: Find just larger digit
        int j = digits.length - 1;
        while (digits[j] <= digits[i]) {
            j--;
        }

        // Swap
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
        reverse(digits, i + 1, digits.length - 1);

        long ans = Long.parseLong(new String(digits));
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}

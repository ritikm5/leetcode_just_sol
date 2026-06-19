import java.math.BigInteger;

class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();

        for (int i = 1; i <= n / 2; i++) {
            // First number has leading zero
            if (num.charAt(0) == '0' && i > 1) {
                break;
            }

            for (int j = i + 1; j < n; j++) {
                // Second number has leading zero
                if (num.charAt(i) == '0' && j - i > 1) {
                    break;
                }

                BigInteger first = new BigInteger(num.substring(0, i));
                BigInteger second = new BigInteger(num.substring(i, j));

                if (isValid(first, second, j, num)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isValid(BigInteger first, BigInteger second,
                            int start, String num) {

        while (start < num.length()) {
            BigInteger sum = first.add(second);
            String sumStr = sum.toString();

            if (!num.startsWith(sumStr, start)) {
                return false;
            }

            start += sumStr.length();
            first = second;
            second = sum;
        }

        return true;
    }
}

class Solution {
    public String fractionAddition(String expression) {
        int num = 0;
        int den = 1;

        int i = 0;
        int n = expression.length();

        while (i < n) {
            // Read sign
            int sign = 1;
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                if (expression.charAt(i) == '-') {
                    sign = -1;
                }
                i++;
            }

            // Read numerator
            int numerator = 0;
            while (i < n && Character.isDigit(expression.charAt(i))) {
                numerator = numerator * 10 + (expression.charAt(i) - '0');
                i++;
            }
            numerator *= sign;

            i++; // Skip '/'

            // Read denominator
            int denominator = 0;
            while (i < n && Character.isDigit(expression.charAt(i))) {
                denominator = denominator * 10 + (expression.charAt(i) - '0');
                i++;
            }

            // Add fractions
            num = num * denominator + numerator * den;
            den = den * denominator;

            int g = gcd(Math.abs(num), den);
            num /= g;
            den /= g;
        }

        return num + "/" + den;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}

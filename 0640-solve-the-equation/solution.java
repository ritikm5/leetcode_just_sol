class Solution {
    public String solveEquation(String equation) {
        String[] parts = equation.split("=");

        int[] left = parse(parts[0]);
        int[] right = parse(parts[1]);

        int xCoeff = left[0] - right[0];
        int constant = right[1] - left[1];

        if (xCoeff == 0) {
            if (constant == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }

        return "x=" + (constant / xCoeff);
    }

    private int[] parse(String expr) {
        int xCoeff = 0;
        int constant = 0;

        int sign = 1;
        int i = 0;

        while (i < expr.length()) {

            if (expr.charAt(i) == '+') {
                sign = 1;
                i++;
            } else if (expr.charAt(i) == '-') {
                sign = -1;
                i++;
            }

            int num = 0;
            boolean hasNum = false;

            while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                num = num * 10 + (expr.charAt(i) - '0');
                hasNum = true;
                i++;
            }

            if (i < expr.length() && expr.charAt(i) == 'x') {
                if (!hasNum) num = 1;
                xCoeff += sign * num;
                i++;
            } else {
                constant += sign * num;
            }
        }

        return new int[]{xCoeff, constant};
    }
}

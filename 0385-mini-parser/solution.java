class Solution {

    public NestedInteger deserialize(String s) {

        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }

        Deque<NestedInteger> stack = new ArrayDeque<>();

        int num = 0;
        int sign = 1;
        boolean hasNum = false;

        for (char c : s.toCharArray()) {

            if (c == '[') {
                NestedInteger ni = new NestedInteger();

                if (!stack.isEmpty()) {
                    stack.peek().add(ni);
                }

                stack.push(ni);
            }
            else if (c == '-') {
                sign = -1;
            }
            else if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                hasNum = true;
            }
            else if (c == ',' || c == ']') {

                if (hasNum) {
                    stack.peek().add(
                        new NestedInteger(sign * num)
                    );

                    num = 0;
                    sign = 1;
                    hasNum = false;
                }

                if (c == ']' && stack.size() > 1) {
                    stack.pop();
                }
            }
        }

        return stack.pop();
    }
}

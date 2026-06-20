class Solution {
    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] inStack = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (inStack[c - 'a']) {
                continue;
            }

            while (!stack.isEmpty()
                    && stack.peekLast() > c
                    && lastIndex[stack.peekLast() - 'a'] > i) {

                char removed = stack.pollLast();
                inStack[removed - 'a'] = false;
            }

            stack.offerLast(c);
            inStack[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }

        return sb.toString();
    }
}

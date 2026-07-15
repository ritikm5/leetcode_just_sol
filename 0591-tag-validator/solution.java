class Solution {
    public boolean isValid(String code) {
        Stack<String> stack = new Stack<>();
        int i = 0;
        int n = code.length();

        while (i < n) {

            // Text outside the outermost tag is invalid
            if (i > 0 && stack.isEmpty()) {
                return false;
            }

            // CDATA
            if (code.startsWith("<![CDATA[", i)) {
                if (stack.isEmpty()) return false;

                int j = code.indexOf("]]>", i);
                if (j == -1) return false;

                i = j + 3;
            }

            // End tag
            else if (code.startsWith("</", i)) {
                int j = code.indexOf('>', i);
                if (j == -1) return false;

                String tag = code.substring(i + 2, j);

                if (!isValidTag(tag)) return false;
                if (stack.isEmpty() || !stack.peek().equals(tag))
                    return false;

                stack.pop();
                i = j + 1;
            }

            // Start tag
            else if (code.charAt(i) == '<') {
                int j = code.indexOf('>', i);
                if (j == -1) return false;

                String tag = code.substring(i + 1, j);

                if (!isValidTag(tag)) return false;

                stack.push(tag);
                i = j + 1;
            }

            // Normal text
            else {
                i++;
            }
        }

        return stack.isEmpty();
    }

    private boolean isValidTag(String tag) {
        if (tag.length() < 1 || tag.length() > 9)
            return false;

        for (char c : tag.toCharArray()) {
            if (c < 'A' || c > 'Z')
                return false;
        }

        return true;
    }
}

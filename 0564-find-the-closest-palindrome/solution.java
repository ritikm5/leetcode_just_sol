import java.util.*;

class Solution {
    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        int len = n.length();

        Set<Long> candidates = new HashSet<>();

        // Edge cases
        candidates.add((long)Math.pow(10, len) + 1);
        candidates.add((long)Math.pow(10, len - 1) - 1);

        long prefix = Long.parseLong(n.substring(0, (len + 1) / 2));

        for (long i = prefix - 1; i <= prefix + 1; i++) {
            String first = String.valueOf(i);
            StringBuilder sb = new StringBuilder(first);

            int start = (len % 2 == 0) ? first.length() - 1 : first.length() - 2;

            for (int j = start; j >= 0; j--) {
                sb.append(first.charAt(j));
            }

            candidates.add(Long.parseLong(sb.toString()));
        }

        candidates.remove(num);

        long ans = -1;

        for (long cand : candidates) {
            if (ans == -1 ||
                Math.abs(cand - num) < Math.abs(ans - num) ||
                (Math.abs(cand - num) == Math.abs(ans - num) && cand < ans)) {
                ans = cand;
            }
        }

        return String.valueOf(ans);
    }
}

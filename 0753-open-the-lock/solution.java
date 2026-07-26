import java.util.*;

class Solution {

    public int openLock(String[] deadends, String target) {

        Set<String> dead = new HashSet<>(Arrays.asList(deadends));

        if (dead.contains("0000"))
            return -1;

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer("0000");
        visited.add("0000");

        int steps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                String cur = q.poll();

                if (cur.equals(target))
                    return steps;

                char[] arr = cur.toCharArray();

                for (int i = 0; i < 4; i++) {

                    char original = arr[i];

                    // rotate forward
                    arr[i] = (char) ((original - '0' + 1) % 10 + '0');
                    String next = new String(arr);

                    if (!dead.contains(next) && visited.add(next))
                        q.offer(next);

                    arr[i] = (char) ((original - '0' + 9) % 10 + '0');
                    next = new String(arr);

                    if (!dead.contains(next) && visited.add(next))
                        q.offer(next);

                    arr[i] = original;
                }
            }

            steps++;
        }

        return -1;
    }
}

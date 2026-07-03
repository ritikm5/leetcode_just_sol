import java.util.*;

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // If endGene is not in the bank, it's impossible
        if (!bankSet.contains(endGene)) {
            return -1;
        }

        char[] genes = {'A', 'C', 'G', 'T'};
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(startGene);
        visited.add(startGene);

        int mutations = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                if (current.equals(endGene)) {
                    return mutations;
                }

                char[] chars = current.toCharArray();

                for (int j = 0; j < 8; j++) {
                    char original = chars[j];

                    for (char c : genes) {
                        if (c == original) continue;

                        chars[j] = c;
                        String next = new String(chars);

                        if (bankSet.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }

                    chars[j] = original;
                }
            }

            mutations++;
        }

        return -1;
    }
}

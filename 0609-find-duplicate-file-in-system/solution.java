import java.util.*;

class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {
            String[] parts = path.split(" ");
            String directory = parts[0];

            for (int i = 1; i < parts.length; i++) {
                String file = parts[i];

                int left = file.indexOf('(');
                int right = file.indexOf(')');

                String fileName = file.substring(0, left);
                String content = file.substring(left + 1, right);

                String fullPath = directory + "/" + fileName;

                map.computeIfAbsent(content, k -> new ArrayList<>()).add(fullPath);
            }
        }

        List<List<String>> result = new ArrayList<>();

        for (List<String> files : map.values()) {
            if (files.size() > 1) {
                result.add(files);
            }
        }

        return result;
    }
}

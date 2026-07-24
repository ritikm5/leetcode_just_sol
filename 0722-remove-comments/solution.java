import java.util.*;

class Solution {
    public List<String> removeComments(String[] source) {

        List<String> result = new ArrayList<>();

        boolean inBlock = false;
        StringBuilder sb = new StringBuilder();

        for (String line : source) {

            if (!inBlock)
                sb = new StringBuilder();

            int i = 0;

            while (i < line.length()) {

                if (!inBlock) {

                    // Line comment
                    if (i + 1 < line.length()
                            && line.charAt(i) == '/'
                            && line.charAt(i + 1) == '/') {
                        break;
                    }

                    // Block comment starts
                    else if (i + 1 < line.length()
                            && line.charAt(i) == '/'
                            && line.charAt(i + 1) == '*') {
                        inBlock = true;
                        i += 2;
                    }

                    else {
                        sb.append(line.charAt(i));
                        i++;
                    }

                } else {

                    if (i + 1 < line.length()
                            && line.charAt(i) == '*'
                            && line.charAt(i + 1) == '/') {
                        inBlock = false;
                        i += 2;
                    } else {
                        i++;
                    }
                }
            }

            if (!inBlock && sb.length() > 0) {
                result.add(sb.toString());
            }
        }

        return result;
    }
}

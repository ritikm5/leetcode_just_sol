class Solution {
    public int strongPasswordChecker(String password) {

        int n = password.length();

        boolean lower = false, upper = false, digit = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) lower = true;
            else if (Character.isUpperCase(c)) upper = true;
            else if (Character.isDigit(c)) digit = true;
        }

        int missing = 0;
        if (!lower) missing++;
        if (!upper) missing++;
        if (!digit) missing++;

        int replace = 0;
        int[] mods = new int[3];

        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && password.charAt(j) == password.charAt(i))
                j++;

            int len = j - i;

            if (len >= 3) {
                replace += len / 3;
                mods[len % 3]++;
            }

            i = j;
        }

        if (n < 6) {
            return Math.max(missing, 6 - n);
        }

        if (n <= 20) {
            return Math.max(missing, replace);
        }

        int delete = n - 20;
        int remainDelete = delete;
        int use = Math.min(mods[0], remainDelete);
        
        replace -= use;
        remainDelete -= use;
        use = Math.min(mods[1] * 2, remainDelete);
        replace -= use / 2;
        remainDelete -= use;

        use = remainDelete / 3;
        replace -= use;

        return delete + Math.max(missing, replace);
    }
}

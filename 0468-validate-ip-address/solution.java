class Solution {
    public String validIPAddress(String queryIP) {
        if (queryIP.indexOf('.') >= 0) {
            return isIPv4(queryIP) ? "IPv4" : "Neither";
        }

        if (queryIP.indexOf(':') >= 0) {
            return isIPv6(queryIP) ? "IPv6" : "Neither";
        }

        return "Neither";
    }

    private boolean isIPv4(String ip) {
        String[] parts = ip.split("\\.", -1);

        if (parts.length != 4) return false;

        for (String part : parts) {
            // Empty part or too long
            if (part.length() == 0 || part.length() > 3)
                return false;

            // Leading zero
            if (part.length() > 1 && part.charAt(0) == '0')
                return false;

            int num = 0;
            for (char c : part.toCharArray()) {
                if (!Character.isDigit(c))
                    return false;
                num = num * 10 + (c - '0');
            }

            if (num > 255)
                return false;
        }

        return true;
    }

    private boolean isIPv6(String ip) {
        String[] parts = ip.split(":", -1);

        if (parts.length != 8)
            return false;

        for (String part : parts) {
            if (part.length() < 1 || part.length() > 4)
                return false;

            for (char c : part.toCharArray()) {
                if (!(Character.isDigit(c) ||
                      (c >= 'a' && c <= 'f') ||
                      (c >= 'A' && c <= 'F'))) {
                    return false;
                }
            }
        }

        return true;
    }
}

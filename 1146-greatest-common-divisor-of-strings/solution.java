class Solution {
    public String gcdOfStrings(String str1, String str2) {
        String result = "";
        boolean runner = true;
        for(int j = Math.min(str1.length(), str2.length()); j>0 && runner; j--){
            String base = str1.substring(0, j);
            if (str1.replace(base, "").isEmpty() && str2.replace(base, "").isEmpty()){
                result = str1.substring(0, j);
                runner = false;
            }
        }
        return result;
    }
}

class Solution {
    public String reverseWords(String s) {
        String result = "";
        s+= " ";
        String word = "";
        String current = "";
        List<String> wordsList = new ArrayList<String> ();

        for(int j =0; j<s.length(); j++){
            current = String.valueOf(s.charAt(j)).trim();
            if (!current.equals("")){
                word += String.valueOf(s.charAt(j)).trim();
            } else{
                if (!word.equals("")){
                    wordsList.add(word);
                }
                word = "";
            }
        }

        int count = wordsList.size();
        for(int j =0; j<count; j++){
            result += wordsList.remove(wordsList.size()-1);
            if (j != count-1){
                result += " ";
            }
        }

        return result;
    }
}

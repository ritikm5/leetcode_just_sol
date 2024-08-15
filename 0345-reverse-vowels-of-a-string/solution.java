class Solution {
    public String reverseVowels(String s) {
        String result = "";

        Set<Character> vowelsSet = new HashSet<>();
        vowelsSet.add('a');
        vowelsSet.add('e');
        vowelsSet.add('i');
        vowelsSet.add('o');
        vowelsSet.add('u');
        vowelsSet.add('A');
        vowelsSet.add('E');
        vowelsSet.add('I');
        vowelsSet.add('O');
        vowelsSet.add('U');

        List<Integer> index = new ArrayList<Integer> ();
        Character[] word = new Character[s.length()]; 
        int count = 0;

        for(int j=0; j<s.length(); j++){
            word[j] = s.charAt(j);
            if (vowelsSet.contains(s.charAt(j))){
                index.add(j);
                count +=1;
            }
        }

        for(int j=0; j<count/2; j++){
            if ( (s.length() > 2 && count>1) || s.length() == index.size()){
                int first = index.remove(0);
                int last = index.remove(index.size()-1);
                word[first] = s.charAt(last);
                word[last] = s.charAt(first);
            }
        }
        for (int j =0; j<word.length; j++){
            result += String.valueOf(word[j]);
        }

        return result;
    }
}

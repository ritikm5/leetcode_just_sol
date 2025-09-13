class Solution {

    public boolean canconvert(String S){
        try{
            Integer.parseInt(String.valueOf(S));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int checkrange(String S){
        
        if (S.charAt(0) == '-'){
            if (S.length() > 11) return Integer.MIN_VALUE;
            if (S.length() < 11) return Integer.parseInt(S);

            if(Integer.parseInt(String.valueOf(S.charAt(1))) > 2) {
                return Integer.MIN_VALUE;
            } else if(Integer.parseInt(String.valueOf(S.charAt(1))) < 2){
                return Integer.valueOf(S);
            } else{
                if ( (Integer.parseInt(S.substring(2))) > (Integer.parseInt(String.valueOf(Integer.MIN_VALUE).substring(2)))){
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.valueOf(S);
                }
            }
        }
        else{
            if (S.length() > 10) return Integer.MAX_VALUE;
            if (S.length() < 10) return Integer.parseInt(S);

            if(Integer.parseInt(String.valueOf(S.charAt(0))) > 2) {
                return Integer.MAX_VALUE;
            } else if(Integer.parseInt(String.valueOf(S.charAt(0))) < 2){
                return Integer.valueOf(S);
            } else{
                if ( (Integer.parseInt(S.substring(1))) > (Integer.parseInt(String.valueOf(Integer.MAX_VALUE).substring(1)))){
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.valueOf(S);
                }
            }
        }
    }

    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;
        if (canconvert(s)) return Integer.valueOf(s);
        String re = "";
        int r=0;
        boolean isneg = false;

        if (s.charAt(0) == '-'){ 
            isneg = true; 
            s=s.substring(1);
        } 
        else if (s.charAt(0) == '+'){ 
            s=s.substring(1);
        }

        for (int i=0; i<s.length(); i++){
            if (canconvert(String.valueOf(s.charAt(i)))) re += s.charAt(i);
            else break;
        }

        if (re.length() == 0) return 0;
        if (isneg) re = "-" + re;
        r = checkrange(re);
        return r;
    }
}

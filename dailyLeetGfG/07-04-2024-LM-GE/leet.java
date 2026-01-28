class Solution {
    public boolean checkValidString(String s) {
        int open = 0, n = s.length();

        // L -> R, check if every ')' had a corresponding '*' OR '(' at left
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '*')
                open++;
            else open--;

            if(open < 0)  
                return false;
        }

        // R -> L, check if every '(' had a corresponding '*' OR ')' at right
        star = 0; open = 0;
        for(int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c == ')' || c == '*')
                open++;
            else open--;
            
            if(open < 0)  
                return false;
        }
        return true;
    }
}
class Solution {
    public int minAddToMakeValid(String s) {
        int n = s.length();
        int opening = 0, closing = 0;
        for(int i = 0; i < n; i++) {
            if (s.charAt(i) == '(')
                opening++;
            else {
                if (opening > 0)
                    opening--;
                else closing++;
            }
        }
        return Math.abs(opening) + closing;
    }
}

// "()))(("

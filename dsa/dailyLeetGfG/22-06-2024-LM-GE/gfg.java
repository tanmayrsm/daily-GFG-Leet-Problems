// User function Template for Java

class Solution {
    long ExtractNumber(String sentence) {
        // code here
        String[] s = sentence.split(" ");
        Long max = -1L;
        for(String g : s) {
            int m = g.length();
            int maxo = 0;
            for(int i = 0; i < m; i++) {
                char curr = g.charAt(i);
                if(Character.isDigit(curr) && curr != '9') {
                    maxo++;
                } else break;
            }
            if(maxo == m) {
                max = Math.max(Long.parseLong(g), max);
            }
        }
        return max;
    }
}
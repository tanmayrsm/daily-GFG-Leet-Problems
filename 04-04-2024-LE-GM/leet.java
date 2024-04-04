class Solution {
    public int maxDepth(String s) {
        int n = s.length(), ans = 0, currSize = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '(')
                currSize++;
            else if (s.charAt(i) == ')')
                currSize--;
            ans = Math.max(ans, currSize);
        }
        return ans;
    }
}
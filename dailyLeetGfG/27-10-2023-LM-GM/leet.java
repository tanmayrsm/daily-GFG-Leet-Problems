
class Solution {
    public String longestPalindrome(String s) {
        // brute force
        // tc - O(n^3)
        
        // optimised solution
        int n = s.length();
        
        int[] ans = new int[] {0, 0};
        for(int i = 0; i < n; i++) {
            int[] oddLenPalindrome = getPalindrome(s, i, i, n);
            int[] evenLenPalindrome = getPalindrome(s, i, i + 1, n);
            
            if(oddLenPalindrome[1] - oddLenPalindrome[0] > ans[1] - ans[0])
                ans = oddLenPalindrome;
            if(evenLenPalindrome[1] - evenLenPalindrome[0] > ans[1] - ans[0])
                ans = evenLenPalindrome;
        }

        return s.substring(ans[0], ans[1] + 1);
    }

    private int[] getPalindrome(String s, int l, int r, int n) {
        int[] res = new int[2];
        while(l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        l++; r--;
        
        res[0] = l;
        res[1] = r;
        return res;
    }
}
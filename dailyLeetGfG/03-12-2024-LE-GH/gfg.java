
class Solution {
    public static int minChar(String s) {
        // Write your code here
        // brute force => O(n^2) = add reversed version at start, and from i = 0 to start of actual s, ch
        // check for min string len whih is palindrome
        
        // logical approach => O(n)
        int i = 0, n = s.length(), j = n - 1, ans = 0;
        while(i < j) {
            if(i < j && s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                // assume, u added j to n waale substring at start
                ans = n - j;
                i = 0;
                while(s.charAt(i) == s.charAt(j)) {
                    i++;
                    ans--;
                }
                j--;
            }
        }
        
        return ans;
    }
}
class Solution {
    boolean canFormPalindrome(String s) {
        // code here
        int[] ct = new int[26];
        int n = s.length();
        for(int i = 0; i < n; i++)  ct[s.charAt(i) - 'a']++;
        int oddCt = 0;
        for(int i = 0; i < ct.length; i++)
            if(ct[i] % 2 == 1) {
                oddCt++;
            }
        return oddCt <= 1;
    }
}
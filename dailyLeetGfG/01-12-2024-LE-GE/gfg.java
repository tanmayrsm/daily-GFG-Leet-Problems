class Solution {
    // Function to find the first non-repeating character in a string.
    static char nonRepeatingChar(String s) {
        // Your code here
        int[] ct = new int[26];
        int n = s.length();
        for(int i = 0; i < n; i++)
            ct[s.charAt(i) - 'a']++;
        for(int i = 0; i < n; i++)
            if(ct[s.charAt(i) - 'a'] == 1)
                return s.charAt(i);
        return '$';
    }
}
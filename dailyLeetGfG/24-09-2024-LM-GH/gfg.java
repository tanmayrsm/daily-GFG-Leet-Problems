
class Solution
{
    //Function to find the smallest window in the string s consisting
    //of all the characters of string p.
    public static String smallestWindow(String s, String p)
    {
        // Your code here
        int[] validCt = new int[26];
        int[] currCt = new int[26];
        int n = s.length(), m = p.length(), l = 0, r = 0, ans = Integer.MAX_VALUE;
        int ansL = 0, ansR = 0;
        for(int i = 0; i < m; i++)
            validCt[p.charAt(i) - 'a']++;
        while (r < n) {
            while (r < n && !isValid(validCt, currCt)) {
                currCt[s.charAt(r) - 'a']++;
                r++;
            }
            while (l < r && isValid(validCt, currCt)) {
                if (r - l < ans) {
                    ansL = l; ansR = r;
                    ans = r - l;
                }
                currCt[s.charAt(l) - 'a']--;
                l++;
            }
        }
        if(ans == Integer.MAX_VALUE)    return "-1";
        return s.substring(ansL, ansR);
    }
    private static boolean isValid(int[] validCt, int[] currCt) {
        for (int i = 0; i < currCt.length; i++) {
            if (validCt[i] != 0 && currCt[i] < validCt[i])
                return false;
        }
        return true;
    }
}
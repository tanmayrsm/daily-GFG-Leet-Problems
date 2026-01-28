class Solution {
    // Function is to check whether two strings are anagram of each other or not.
    public static boolean areAnagrams(String s1, String s2) {

        // Your code here
        int n1 = s1.length(), n2 = s2.length();
        if(n1 != n2)    return false;
        int[] c1 = new int[26], c2 = new int[26];
        for(int i = 0; i < n1; i++)
            c1[s1.charAt(i) - 'a']++;
        for(int i = 0; i < n2; i++)
            c2[s2.charAt(i) - 'a']++;
        for(int i = 0; i < c1.length; i++)
            if(c1[i] != c2[i])
                return false;
        return true;
    }
}
class Solution {
    public String firstPalindrome(String[] words) {
        for(String word : words) {
            if(check(word)) return word;
        }
        return "";
    }
    private static boolean check(String s) {
        int l = 0, r = s.length() - 1;
        while(l < r && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
        }
        return l >= r;
    }
}
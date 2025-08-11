class Solution {
    public boolean isPalinSent(String s) {
        // code here
        String[] newer = s.split(" ");

        s = String.join("", newer).toLowerCase();
        return isPalin(s, s.length());
    }
    private boolean isPalin(String s, int n) {
        int l = 0, r = n - 1;
        while (l < r) {
            while (l < n && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (r >= 0 && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (l < r && s.charAt(l) != s.charAt(r))    return false;
            l++;
            r--;
        }
        return true;
    }

}
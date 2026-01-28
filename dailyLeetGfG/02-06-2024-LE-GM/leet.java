class Solution {
    public void reverseString(char[] s) {
        int n = s.length - 1, l = 0, r = n;
        while(l <= r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }
}
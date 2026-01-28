class Solution {
    public int minimumLength(String s) {
        int n = s.length(), l = 0, r = n - 1;
        while(l < r) {
            char lChar = s.charAt(l);
            char rChar = s.charAt(r);
            if(lChar != rChar)
                break;
            while(l < r && s.charAt(l) == lChar)
                l++;
            while(l <= r && s.charAt(r) == rChar)
                r--;
        }
        return r - l + 1;
    }
}
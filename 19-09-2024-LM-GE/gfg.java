
class Solution {
    // Function to reverse words in a given string.
    String reverseWords(String str) {
        // code here
        String[] res = str.split("\\.");
        int l = 0, r = res.length - 1;
        while(l < r) {
            String temp = res[l];
            res[l] = res[r];
            res[r] = temp;
            l++;
            r--;
        }
        return String.join(".", res);
    }
}
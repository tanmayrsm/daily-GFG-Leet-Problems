
class Solution {
    int isPalindrome(String S) {
        // code here
        int i = 0, j = S.length() - 1;
        while(i < j && S.charAt(i) == S.charAt(j)) {
            i++;
            j--;
        }
        if(i < j)   return 0;
        return 1;
    }
};
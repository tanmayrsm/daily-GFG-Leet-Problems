class Solution {    // referred soln
    public int maxConsecutiveAnswers(String s, int tar) {
        int n=s.length();
        char[] A=s.toCharArray();
        int i = 0, j, k = tar, res = 0;
        for (j = 0; j < n; ++j) {
            if (A[j] == 'T') k--;
            while (k < 0) {
                if (A[i] == 'T') k++;
                i++;
            }
            res = Math.max(res, j - i);
        }
        i=0;k=tar;
        for (j = 0; j < n; ++j) {
            if (A[j] == 'F') k--;
            while (k < 0) {
                if (A[i] == 'F') k++;
                i++;
            }
            res = Math.max(res, j - i);
        }
        return res+1;
    }
}
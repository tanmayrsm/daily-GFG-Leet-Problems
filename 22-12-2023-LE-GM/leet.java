class Solution {
    public int maxScore(String s) {
        int noOfOnes = 0, n = s.length(), noOfZeroes = 0, ans = 0;

        for(int i = 0; i < n; i++)
            if(s.charAt(i) == '1')
                noOfOnes++;

        for(int i = 0; i < n - 1; i++) {    // ignore last char split, i.e split only till 'n-1'
            if(s.charAt(i) == '0') {
                noOfZeroes++;
            } else  noOfOnes--;
            // if(i > 0 && i < n - 1)
                ans = Math.max(ans, noOfOnes + noOfZeroes);
        }
        return ans;
    }
}
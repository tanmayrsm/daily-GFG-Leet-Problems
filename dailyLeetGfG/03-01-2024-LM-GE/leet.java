class Solution {
    public int numberOfBeams(String[] bank) {
        int lastRowLasers = 0, currLasers = 0, n = bank.length, m = bank[0].length(), ans = 0;
        for(int i = 0; i < n; i++) {
            currLasers = 0;
            for(int j = 0; j < m; j++) {
                if(bank[i].charAt(j) == '1')
                    currLasers++;
            }
            ans += currLasers * lastRowLasers;
            if(currLasers > 0)
                lastRowLasers = currLasers;
        }
        return ans;
    }
}
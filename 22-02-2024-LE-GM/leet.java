class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] ct = new int[n + 1];
        for(int[] t : trust) {
            ct[t[0]]--;
            ct[t[1]]++;
        }
        for(int i = 1; i <= n; ++i)
            if(ct[i] == n - 1)
                return i;
        return -1;
    }
}
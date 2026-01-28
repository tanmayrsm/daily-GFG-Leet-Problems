class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int N = hBars.length, M = vBars.length, vBound = m + 2, hBound = n + 2;
        if(N == 0 || M == 0)    return 1;
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int maxConSec1sA = 1, maxConSec1sB = 1, curr = 0;
        for(int i = 1; i < N; i++) {
            if(hBars[i] == 1 || hBars[i] == hBound) continue;
            int x = hBars[i] - hBars[i - 1];
            if(x == 1) {
                curr++;
            } else curr = 0;
            maxConSec1sA = Math.max(maxConSec1sA, curr + 1);
        }
        curr = 0;
        for(int i = 1; i < M; i++) {
            if(vBars[i] == 1 || vBars[i] == vBound) continue;
            int x = vBars[i] - vBars[i - 1];
            if(x == 1) {
                curr++;
            } else curr = 0;
            maxConSec1sB = Math.max(maxConSec1sB, curr + 1);
        }
        int maxArea = (Math.min(maxConSec1sA, maxConSec1sB) + 1);
        return maxArea * maxArea;
    }
}
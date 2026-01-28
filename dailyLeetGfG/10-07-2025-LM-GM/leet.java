class Solution {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int n = startTime.length, ans = 0;
        int[] maxSpaceR = new int[n];
        int[] maxSpaceL = new int[n];

        // maxSpace at right of 'i'
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1)
                maxSpaceR[i] = eventTime - endTime[i];
            else maxSpaceR[i] = Math.max(maxSpaceR[i + 1], startTime[i + 1] - endTime[i]);
        }

        // maxSpace at left of 'i'
        for (int i = 0; i < n; i++) {
            if (i == 0)
                maxSpaceL[i] = startTime[i];
            else maxSpaceL[i] = Math.max(maxSpaceL[i - 1], startTime[i] - endTime[i - 1]);
        }

        for (int i = 0; i < n; i++) {
            int blockLen = endTime[i] - startTime[i];
            int prevEnd = (i == 0) ? 0 : endTime[i - 1];
            int nextStart = (i == n - 1) ? eventTime : startTime[i + 1];
            // move block at complete left or complete right
            ans = Math.max(ans, Math.max
                    (nextStart - (prevEnd + blockLen),
                    (nextStart - blockLen) - prevEnd)
            );
            // or check if this block can be removed completely
            if (i - 1 >= 0 && maxSpaceL[i - 1] >= blockLen || (i + 1 < n && maxSpaceR[i + 1] >= blockLen))
                ans = Math.max(ans, nextStart - prevEnd);
        }
        return ans;
    }
}




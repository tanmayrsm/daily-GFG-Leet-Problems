// reff - https://www.youtube.com/watch?v=Nn_LU8ir5wY

class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int res = 0, left = 0, blocked = 0;

        for (int i = 0; i < n; i++) {
            res = Math.max(res, (startTime[i] - left) - blocked);
            blocked += endTime[i] - startTime[i];

            if (i >= k) {
                int j = i - k;
                left = endTime[j];
                blocked -= endTime[j] - startTime[j];
            }
        }

        res = Math.max(res, (eventTime - left) - blocked);
        return res;
    }
}
class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landDuration.length, m = waterDuration.length, ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int landFirst = landDuration[i] + landStartTime[i];
                int waterFirst = waterDuration[j] + waterStartTime[j];
                ans = Math.min(ans, Math.min(
                                (waterStartTime[j] > landFirst ? waterFirst : landFirst + waterDuration[j]),
                                (landStartTime[i] > waterFirst ? landFirst : waterFirst + landDuration[i])
                        )
                );
            }
        }
        return ans;
    }
}
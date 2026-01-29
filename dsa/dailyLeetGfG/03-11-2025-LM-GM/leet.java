class Solution {
    public int minCost(String colors, int[] neededTime) {
        int ans = 0, n = neededTime.length;
        for (int i = 1; i < n; i++) {
            if (colors.charAt(i - 1) == colors.charAt(i)) {
                int currMax = neededTime[i - 1], currSum = neededTime[i - 1];
                while (i < n && colors.charAt(i - 1) == colors.charAt(i)) {
                    currMax = Math.max(currMax, neededTime[i]);
                    currSum += neededTime[i];
                    i++;
                }
                currSum -= currMax;
                ans += currSum;
            }
        }
        return ans;
    }
}
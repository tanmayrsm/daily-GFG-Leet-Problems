class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length(), i = 1, ans = 0;
        while(i < n) {
            int minTime = neededTime[i - 1], maxTime = Integer.MIN_VALUE;
            boolean isRep = false;
            while(i < n && colors.charAt(i) == colors.charAt(i - 1)) {
                isRep = true;
                minTime += neededTime[i];
                maxTime = Math.max(maxTime, Math.max(neededTime[i], neededTime[i - 1]));
                i++;
            }
            if(isRep)    ans += (minTime - maxTime);    // add time for repeated colors, except the maxTime in this range of repeating colors 
            i++;
        }
        return ans;
        
    }
}
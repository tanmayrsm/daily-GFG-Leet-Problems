class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, ans = 0;
        for (List<Integer> arr : arrays) {
            int m = arr.size();
            int currMin = arr.get(0), currMax = arr.get(m - 1);
            if (max != Integer.MIN_VALUE) {
                ans = Math.max(ans, max - currMin);
            }
            if (min != Integer.MAX_VALUE)
                ans = Math.max(ans, currMax - min);
            min = Math.min(min, currMin);
            max = Math.max(max, currMax);
        }

        return ans;
    }
}
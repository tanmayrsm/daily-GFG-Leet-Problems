class Solution {    // referred soln
    public int candy(int[] ratings) {
        int n = ratings.length;
        // create a array for left-to-right traversal
        int[] left = new int[n];
        Arrays.fill(left, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) left[i] = left[i-1]+1;
        }
        // create a array for right-to-left traversal
        int[] right = new int[n];
        Arrays.fill(right, 1);
        for (int i = n - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) right[i] = right[i+1]+1;
        }
        int totalCandies = 0;
        for (int i = 0; i < n; i++) {
            totalCandies += Math.max(left[i], right[i]);
        }
        return totalCandies;
    }
}
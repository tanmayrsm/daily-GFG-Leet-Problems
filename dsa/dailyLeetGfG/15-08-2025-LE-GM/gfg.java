class Solution {
    public ArrayList<int[]> insertInterval(int[][] intervals, int[] x) {
        // code here
        ArrayList<int[]> ans = new ArrayList<>();
        int n = intervals.length, ansL = 0;
        boolean added = false;
            
        for (int i = 0; i < n; i++) {
            if (!added && intervals[i][0] > x[0]) {
                added = true;
                ansL = checkAndMerge(x, ans, ansL);
            }
            ansL = checkAndMerge(intervals[i], ans, ansL);
        }
        if (!added)
            checkAndMerge(x, ans, ansL);
        return ans;
    }
    private int checkAndMerge(int[] x, ArrayList<int[]> ans, int ansL) {
        if (ans.isEmpty()) {
            ans.add(x);
            return 1;
        }
        if (ans.get(ansL - 1)[1] >= x[0]) {
            ans.get(ansL - 1)[1] = Math.max(ans.get(ansL - 1)[1], x[1]);
            return ansL;
        }
        ans.add(x);
        return ansL + 1;
    }
}

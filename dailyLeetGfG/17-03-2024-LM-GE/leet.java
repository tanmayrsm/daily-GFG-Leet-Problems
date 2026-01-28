class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        int n = intervals.length;
        ArrayList<int[]> ans = new ArrayList<>();
        int start = newInterval[0];
        int end = newInterval[1];
        
        int i = 0;
        
        // iterate till u get a conflicting point
        while(i < n && intervals[i][1] < start) {
            ans.add(intervals[i]);
            i++;
        }
        
        // once u get a conflicting point, adjust ur start and end pointers accordingly
        while(i < n && end >= intervals[i][0]) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        ans.add(new int[]{start, end});

        // add rest of intervals
        while(i < n) {
            ans.add(intervals[i]);
            i++;
        }

        return ans.toArray(new int[ans.size()][]);
    }

}
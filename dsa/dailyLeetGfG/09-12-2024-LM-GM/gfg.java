
class Solution {
    static ArrayList<int[]> insertInterval(int[][] intervals, int[] newInterval) {
        // code here
        int len = intervals.length;
        ArrayList<int[]> result = new ArrayList<>();
       
        int p1 = newInterval[0];
        int p2 = newInterval[1];
        
        
        int idx = 0;
        
        while(idx < len && intervals[idx][1] < p1){
            result.add(intervals[idx]);
            idx++;
        }
        
        while(idx < len && intervals[idx][0] <= p2){
            
            p1 = Math.min(p1 , intervals[idx][0]);
            p2 = Math.max(p2 , intervals[idx][1]);
            
            idx++;
        }
        
        result.add(new int[]{p1 , p2});
        
        while(idx <len){
             result.add(intervals[idx]);
             idx++;
        }
        
        
        return result;
    }
}
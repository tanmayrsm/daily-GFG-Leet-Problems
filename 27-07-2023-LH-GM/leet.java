class Solution {    // referred soln
    
    public boolean isPossibelToRun(int n, int[] batteries, long avgTime) {
        long duration = 0;
        for (int ele: batteries) {
            if(ele < avgTime) duration += ele;
            else duration += avgTime;
        }
        return duration >= avgTime*n;
    }
    
    public long maxRunTime (int n, int[] batteries) {
        long sum = 0;
        for (int ele : batteries) sum += ele;
        
        long startTime = 0, endTime = sum, ans = 0;
        
        while(startTime <= endTime){
            long avgTime = (startTime + endTime) / 2;
            
            if(isPossibelToRun (n, batteries, avgTime)){
                ans = avgTime;
                startTime = avgTime + 1;
            }else{
                endTime = avgTime - 1;
            }
        }
        return ans;
    }
}
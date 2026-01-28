class Solution {
    public int startStation(int[] gas, int[] cost) {
        // code here
        int n = gas.length, prev = 0, sum = 0, totSum = 0;
        int totalg = 0;
        int totalc = 0;
        for(int i=0;i<n;i++) {
            totalg += gas[i];
            totalc += cost[i];
        }
        if(totalg<totalc){
            return -1;
        }
        
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i];
            totSum += sum;
            if (sum < 0) {
                prev = i + 1;
                sum = 0;
            }
        }
        return prev;
    }
}
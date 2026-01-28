
class Solution {
    static int minRemoval(int intervals[][]) {
        // code here
        Arrays.sort(intervals , (a,b)->Integer.compare(a[0],b[0]));
        int n= intervals.length ; 
        int count = 0 ; 
        int min=intervals[0][1];
        for(int i=1;i<n;i++){
            if(intervals[i][0]==intervals[i][1]) continue;
            if(intervals[i][0]<min){
                count++;
                min=Math.min(min,intervals[i][1]);
            }else
                min=intervals[i][1];
        }
       return count ; 
    }
}
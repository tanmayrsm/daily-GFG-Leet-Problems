// User function Template for Java

class Solution {
    static int minSteps(int d) {
        // code here
        int cnt = 0;
        int i=0;
        while(i<d || (i-d)%2!=0){
            cnt++;
            i+=cnt;
        }
        return cnt;
    }
}
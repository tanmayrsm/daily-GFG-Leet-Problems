class Solution{
    static long maximumSumSubarray(int K, ArrayList<Integer> arr,int N){
        // code here
        int r = 0, l = 0;
        long ans = 0, currRes = 0;
        while(r < N) {
            if(r - l == K) {
                currRes -= arr.get(l);
                l++;
            }
            while(r < N && r - l < K) {
                currRes += arr.get(r);
                r++;
            }
            ans = Math.max(ans, currRes);
        }
        return ans;
    }
}
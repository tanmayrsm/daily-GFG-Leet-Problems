class Solution {
    static class Pair{
        int sum;
        int index;
        public Pair(int sum, int index){
            this.sum = sum;
            this.index = index;
        }
    }
    public int rangeSum(int[] nums, int n, int left, int right) {
        int mod = 1_000_000_007;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.sum-b.sum);
        for(int i=0;i<n;i++){
            pq.offer(new Pair(nums[i], i));
        }
        left--;
        right--;
        int ans = 0;
        for(int i=0;i<=right;i++){
            Pair p = pq.poll();
            if(i>=left){
                ans = (ans%mod + p.sum%mod)%mod;
            }
            if(p.index < n-1){
                p.sum = (p.sum%mod + nums[++p.index]%mod)%mod;
                pq.offer(p);
            }
            
        }
        return ans;
    }
}
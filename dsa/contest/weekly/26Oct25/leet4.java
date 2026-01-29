class Solution {
    public long maxSumTrionic(int[] nums) {

        int n = nums.length;
        long res= -1 * (long)1e16;

        for(int i=1;i<n-2;i++){

            int a = i; //starting point
            int b = i; //ending point

            long net = nums[a];

            //net decreasing window
            while(b+1<n && nums[b+1] < nums[b]){
                net+=(long)nums[b+1];
                b++;
            }

            if(b==a)continue;

            int c= b; //to store ending point

            long left = 0;
            long right = 0;

            long lx =Integer.MIN_VALUE;
            long rx =Integer.MIN_VALUE;

            //left increasing window
            while(a-1>=0 && nums[a-1] < nums[a]){
                left+=(long)nums[a-1];
                lx = Math.max(lx, left);
                a--;
            }

            if(a==i)continue;

            //right increasing window
            while(b+1<n && nums[b+1] > nums[b]){
                right+=(long)nums[b+1];
                rx = Math.max(rx, right);
                b++;
            }

            if(b==c)continue;
            i=b-1;
            res = Math.max( res, lx+rx+net);

        }
        return res;
    }
}
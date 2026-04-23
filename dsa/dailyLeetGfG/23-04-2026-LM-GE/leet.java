class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] res = new long[n];
        HashMap<Integer, ArrayList<Integer>> mp = new HashMap<>();

        for( int i = 0; i < n; i++ ) {
            if( !mp.containsKey( nums[i] ) ) {
                mp.put( nums[i], new ArrayList<>() );
            }

            mp.get( nums[i] ).add( i );
        }

        for( int key : mp.keySet() ) {
            ArrayList<Integer> list = mp.get( key );
            int len = list.size();

            long[] prefix = new long[len];
            prefix[0] = list.get(0);

            for( int i = 1; i < len; i++ ) {
                prefix[i] = prefix[i - 1] + list.get(i);
            }

            for (int k = 0; k < len; k++) {
                int currIdx = list.get(k);

                long leftSum = (k > 0) ? prefix[k - 1] : 0;
                long rightSum = prefix[len - 1] - prefix[k];

                long left = (long) currIdx * k - leftSum;
                long right = rightSum - (long) currIdx * (len - k - 1);

                res[currIdx] = left + right;
            }

        }
        return res;
    }
}
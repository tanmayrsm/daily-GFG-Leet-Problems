class KthLargest {
    int K;
    List<Integer> pq;
    public KthLargest(int k, int[] nums) {
        pq = new ArrayList<>();
        K = k;
        for(int x : nums)
            pq.add(x);
        Collections.sort(pq);
    }
    
    public int add(int val) {
        int idx = bs(pq, 0, pq.size() - 1, val);
        pq.add(idx, val);
        return pq.get(pq.size() - K);
    }

    private static int bs(List<Integer> arr, int l, int r, int target) {
        if(arr.size() == 0 ||  target <= arr.get(0))
            return 0;
        else if (target >= arr.get(arr.size() - 1))
            return arr.size();
        int ans = 0;
        int mid = 0;
        while(l <= r){
            mid = l + (r - l) / 2;
            if(arr.get(mid) == target)
                return mid;
            else if (arr.get(mid) < target) {
                // ans = mid;
                l = mid + 1;
            } else {
                // ans = mid;
                r = mid - 1;
            }
        }
        return r + 1;
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
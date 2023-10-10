class Solution {
    public int minOperations(int[] nums) {
        int ans = 0;
        int n = nums.length;
        
        List<Integer> uniqueArr = Arrays.stream(nums).
                                    boxed().    // int[] to List<Integer>
                                    distinct(). // only take unique values
                                    sorted().   // sort values
                                    collect(Collectors.toList());
        
        int start = 0;
        for(int end = 0; end < uniqueArr.size(); end++) {
            if(uniqueArr.get(end) <= uniqueArr.get(start) + n - 1) {
                ans = Math.max(ans, end - start + 1);
            } else  start++;
        }
        return n - ans;
    }
}
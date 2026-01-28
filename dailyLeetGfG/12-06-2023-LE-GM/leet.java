class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        int i = 0;
        while(i < nums.length) {
            int l = i;
            int r = i + 1;
            while(r < nums.length && nums[r] - nums[r-1] == 1)
                r++;
            if(r == i + 1)
                ans.add(Integer.toString(nums[l]));
            else   {
                // using StringBuilder, as normal append to String class leads to more runtime
                // e.g - Integer.toString(nums[l]) + "->" + Integer.toString(nums[r - 1])
                // is expensive operation
                
                StringBuilder x = new StringBuilder(Integer.toString(nums[l]));
                x.append("->");
                x.append(Integer.toString(nums[r - 1]));
                ans.add(x.toString());
            }  
            i = r;
        }
        return ans;
    }
}
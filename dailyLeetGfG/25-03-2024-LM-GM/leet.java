class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<nums.length;i++) {
            int ind = Math.abs(nums[i])-1;
            if(nums[ind] < 0) {
                ans.add(ind+1);
            } else {
                nums[ind] *= -1;
                // you can use any of the below to mark it as negative
                // nums[ind] = ~(nums[ind])+1;
                // nums[ind] = -nums[ind];
            }
        }
        return ans;
    }
}
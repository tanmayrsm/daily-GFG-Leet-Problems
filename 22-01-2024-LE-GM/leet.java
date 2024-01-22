class Solution {    // reff
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        int n = nums.length;
        for(int i=0; i<n; i++){
            int num = Math.abs(nums[i]);
            if(nums[num-1] < 0){
                // already occurred
                ans[0] = num;
            }else{
                nums[num-1] = nums[num-1] * -1;
            }
        }
        for(int i=0; i<n; i++){
            if(nums[i] > 0){
                ans[1] = i+1;
                break;
            }
        }

        return ans;
        
    }
}
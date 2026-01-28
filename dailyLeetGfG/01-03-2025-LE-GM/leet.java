class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length, j = 0;
        for (int i = 0; i < n - 1; i++)
            if (nums[i] != 0 && nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                j = Math.max(j, i + 1);
                while(j < n && nums[j] == 0) j++;
                if (j < n && nums[j] != 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }
}
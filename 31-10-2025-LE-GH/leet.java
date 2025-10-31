class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int res[] = new int[2], i = 0;
        for (int num : nums){
            int val = num >= 1000 ? num - 1000 : num;
            if (nums[val] + 1000 >= 2000)
                res[i++] = val;
            else
                nums[val] += 1000;
        }
        return res;
    }
}
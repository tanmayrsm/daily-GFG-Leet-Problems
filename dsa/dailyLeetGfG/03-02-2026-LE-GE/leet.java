class Solution {
    public boolean isTrionic(int[] nums) {
        int temp = nums[0], j = 1;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int old = nums[i];
            nums[i] = nums[i] - temp;
            temp = old;
        }
        // check for [+ + + - - - + + +] pattern in array

        while(j < n && nums[j] > 0)
            j++;
        if(j == 1 || j == n)  return false;
        while(j < n && nums[j] < 0)
            j++;
        if(j == n)  return false;
        while(j < n && nums[j] > 0)
            j++;
        return j == n;
    }
}
// 1 3 5 4 2 6
// 2 2 -1 -2 4

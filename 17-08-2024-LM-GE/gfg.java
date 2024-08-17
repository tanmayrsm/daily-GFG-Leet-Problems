// User function Template for Java

class Solution {
    public static long[] productExceptSelf(int nums[]) {
        // code her
        int n = nums.length, zeroCt = 0;
        long a = 1;
        long[] ans = new long[n];
        for (int num : nums) {
            if (num == 0)   zeroCt++;
            else a *= num;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0 && zeroCt == 1)
                ans[i] = a;
            else if (nums[i] == 0)   ans[i] = 0;
            else if (zeroCt > 0)    ans[i] = 0;
            else ans[i] = a / nums[i];
        }
        return ans;
    }
}
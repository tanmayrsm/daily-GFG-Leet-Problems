class Solution {
    public int maxRotateFunction(int[] nums) {
        // pattern based question
        // watch f[0] and f[1] closely, and try to derive some relation between them
        int fn = 0, n = nums.length, maxi, sum = 0;

        for(int i = 0; i < n; i++) {
            fn += (i * nums[i]);
            sum += nums[i];
        }
        maxi = fn;

        for(int i = n - 1; i >= 1; i--) {
            fn = fn + sum - n * nums[i];
            maxi = Math.max(maxi, fn);
        }
        return maxi;
    }
}
// THE PATTERN -

// F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25

// in F(1), count of 4 incresed by 1, same for 3, 2, 6...but 6 got removed + 6*3 also got removed
// F(1) = 25 + 15 (sum of all elems, as they incresed by 1) - 6(last_elem) * 4 (6 got removed 4 times, and 4 is length of array)
// F(1) = 16

// F(2) = 16 + 15 - 2*4
// F(2) = 23

// F(3) = 23 + 15 - 3*4
// F(3) = 26
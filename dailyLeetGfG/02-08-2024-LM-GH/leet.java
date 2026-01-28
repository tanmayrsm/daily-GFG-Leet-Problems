class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int l = 0, r = 0, oneCt = 0, track = 0, currOnes = 0, maxOnesInWindow = 0;
        for (int num : nums)
            if (num == 1)
                oneCt++;
        // create initial window
        while (r < oneCt) {
            if (nums[r] == 1)
                currOnes++;
            r++;
        }
        r--;
        track = r;
        while (track < 2 * (n)) {
            maxOnesInWindow = Math.max(maxOnesInWindow, currOnes);
            r++;
            r %= n;
            if (nums[r] == 1)
                currOnes++;
            if (nums[l] == 1)
                currOnes--;
            l++;
            l %= n;
            track++;
        }

        return oneCt - maxOnesInWindow;
    }
}

// intuition -> we need x no of ones together, where x is total no fo 1s in array
// hence have a window of size 'x'
// in each window, count 1s, the window with max 1s - x => min no of swaps
// what about circular part ?
// eg- 1 1 0 1
// 1 1 0 1 . 1 1 0 1
// either use extra space and attach mirror of array
// or use i % N
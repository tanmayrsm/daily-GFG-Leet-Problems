class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int countL = 1; //initialize increasing counter with 1 since we checking only subarrays
        int countR = 1; //same as the above for decreasing
        int inc = 0; //this is to keep track of currently increasing sunbarray
        int dec = 0; //same as the above for decreasing

        int prev = nums[0]; //we begin with the first element and set i below to next element to skip dealing with empty value at the beginning
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];

            if (curr > prev) { //increasing condition met so we increment countL
                countL++;
            }

            if (curr <= prev) { // if this condition is met, the chain or streak is broken of increasing subarray condition; so we reset it to 1 (1 because subarray is 1 element at least by default)
                inc = Math.max(inc, countL);
                countL = 1;
            }

            if (curr < prev) { //decreasing condition met so we incremenet countR
                countR++;
            }

            if (curr >= prev) {
                dec = Math.max(dec, countR);
                countR = 1;
            }

            prev = curr; //we set at the very end the prev to curr so this way, we don't make a mistake while processing prev vs. curr above for both conditions

        }

        inc = Math.max(inc, countL); //if either countL or countR are not empty, we pick them up and check if they are the max or not by using Math.max()
        dec = Math.max(dec, countR);

        return Math.max(inc, dec); // finally, we now return inc or dec, whichever is larger than the other
    }
}
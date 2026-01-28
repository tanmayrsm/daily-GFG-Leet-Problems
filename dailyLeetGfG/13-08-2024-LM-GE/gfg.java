
/*You are required to complete
this function*/

// Function to find square root
// x: element to find square root
class Solution {
    long floorSqrt(long n) {
        // Your code here
        long low = 1, high = n, ans = 0;
        while (low <= high) {
            long mid = (low + high) / 2;
            long sq = mid * mid;
            if (sq == n) {
                return mid;
            } else if (sq < n) {
                low = mid + 1;
                ans = mid;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
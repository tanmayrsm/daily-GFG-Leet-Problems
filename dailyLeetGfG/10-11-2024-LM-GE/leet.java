class Solution {    // reff
    public int minimumSubarrayLength(int[] nums, int k) {
        int w = 0, min = Integer.MAX_VALUE;
        int[] bits = new int[32]; // Array to count bits in the sliding window
        
        // Sliding window pointers l (left) and r (right)
        for (int l = 0, r = 0; r < nums.length; r++) {
            // Quick check: if the current element alone meets the requirement, return 1
            if (nums[r] >= k) return 1;
            
            // Add the current element to the bitwise OR
            w |= nums[r];
            
            // Update bit counts for each position in bits[]
            for (int i = 0; i < bits.length; i++) {
                bits[i] += (nums[r] >> i) & 1;
            }
            
            // Try to shrink the window while keeping the OR >= k
            while (w >= k) {
                // Update the minimum length of the subarray
                min = Math.min(min, r - l + 1);
                
                // Remove the leftmost element from the bitwise OR
                for (int i = 0; i < bits.length; i++) {
                    bits[i] -= (nums[l] >> i) & 1;
                    
                    // If a bit count drops to zero, clear that bit in the OR
                    if (bits[i] == 0) w &= ~(1 << i);
                }
                
                // Move the left pointer to narrow the window
                l++;
            }
        }
        
        // If no valid subarray was found, return -1; otherwise, return min length
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
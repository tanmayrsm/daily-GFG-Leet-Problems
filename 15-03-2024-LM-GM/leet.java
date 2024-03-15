class Solution {
    public int[] productExceptSelf(int[] nums) {
        // same as prefixSum, use prefixMul
        int zeroCt = 0, n = nums.length, withoutFirstZero = 1;
        int[] res = new int[n];
        res[0] = nums[0];
        for(int i = 0; i < n; i++) {
            if(nums[i] == 0)    
                zeroCt++;
            if(i - 1 >= 0)
                res[i] = nums[i] * res[i - 1];
        }
        int mulFact = res[n - 1];
        if(zeroCt == 1) {   // edge case of 1 zero
            for(int x : nums)
                if(x != 0)
                    withoutFirstZero *= x;
        }
        for(int i = 0; i < n; i++) {
            if(zeroCt == 1 && nums[i] == 0) {
                res[i] = withoutFirstZero;
            } else if(zeroCt > 0 || mulFact == 0)
                res[i] = 0;
            else res[i] = mulFact / nums[i];
        }
        return res;
    }
}

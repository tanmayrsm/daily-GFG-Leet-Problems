class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length, smallerThanPivot = 0, equalToPivot = 0, l = 0, r = 0;
        int[] ans = new int[n];
        for (int num : nums)
            if (num < pivot)
                smallerThanPivot++;
            else if (num == pivot)
                equalToPivot++;

        r = smallerThanPivot + equalToPivot; // pointer for nos > pivot element
        
        for(int num : nums) {
            if (num < pivot)
                ans[l++] = num;
            else if (num > pivot) 
                ans[r++] = num;
        }

        while(l < smallerThanPivot + equalToPivot)  // fill middle elements
            ans[l++] = pivot;
        return ans;
    }
}
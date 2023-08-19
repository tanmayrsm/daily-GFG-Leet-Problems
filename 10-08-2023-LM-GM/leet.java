class Solution {
    private static boolean bs(int[] arr, int l, int r, int target) {
        if(l > r)   return false;
        int mid = (r + l) / 2;
        
        if(arr[mid] == target)
            return true;
        boolean isLeftOrdered = mid - 1 >= 0 ? isOrder(arr, l, mid - 1) : true;
        boolean isRightOrdered = mid + 1 < arr.length ? isOrder(arr, mid + 1, r) : true;
        

        if(isLeftOrdered && mid - 1 >= 0 && arr[l] <= target && arr[mid - 1] >= target) // is left side is ordered, and target lies within that range
            return bs(arr, l, mid - 1, target);
        else if (isRightOrdered && mid + 1 < arr.length && arr[mid + 1] <= target && arr[r] >= target)  // if right side is ordered, and target lies within that range
            return bs(arr, mid + 1, r, target);
        if(!isLeftOrdered && isRightOrdered)    // go for unordered side first
            return bs(arr, l, mid - 1, target);
        return bs(arr, mid + 1, r, target);

    }

    private static boolean isOrder(int[] nums, int l, int r) {
        for(int i = l + 1; i <= r; i++)
            if(nums[i] < nums[i - 1])
                return false;
        return true;
    }
    public boolean search(int[] nums, int target) {
        return bs(nums, 0, nums.length - 1, target);
    }
}
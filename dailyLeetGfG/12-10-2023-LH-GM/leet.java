/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
 class Solution {
    static int n;
    public int findInMountainArray(int target, MountainArray mountainArr) {
        n = mountainArr.length();
        int peak = getPeak(mountainArr, 0, n - 1);
        
        int onLeft = getLeft(mountainArr, 0, peak, target);
        
        if(onLeft != -1)    return onLeft;
        int onRight = getRight(mountainArr, peak + 1, n - 1, target);
        
        return onRight;
    }

    private static int getPeak(MountainArray arr, int l, int r) {
        int indo = -1;
        while(l <= r) {
            int mid = r - (r - l) / 2;
            if(mid - 1 < 0) return mid + 1; // edge case
            if(mid + 1 >= n)    return mid - 1; // edge case
            int left = arr.get(mid - 1), right = arr.get(mid + 1), curr = arr.get(mid);
            if( left < curr && curr < right)
                l = mid + 1;
            else if (left < curr && curr > right) {
                return mid;
            } else r = mid - 1;
        }
        return -1;
    }

    private static int getLeft(MountainArray arr, int l, int r, int target) {
        int indo = -1;
        while(l <= r) {
            int mid = r - (r - l) / 2;
            int left = arr.get(l), right = arr.get(r), curr = arr.get(mid);
            if(curr == target)
                return mid;
            else if (curr > target) {
                r = mid - 1;
            } else l = mid + 1;
        }
        return -1;
    }

    private static int getRight(MountainArray arr, int l, int r, int target) {
        int indo = -1;
        while(l <= r) {
            int mid = r - (r - l) / 2;
            int left = arr.get(l), right = arr.get(r), curr = arr.get(mid);
            if(curr == target)
                return mid;
            else if (curr > target) {
                l = mid + 1;
            } else r = mid - 1;
        }
        return -1;
    }
}
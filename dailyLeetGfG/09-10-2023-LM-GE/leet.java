class Solution {
    public int[] searchRange(int[] arr, int target) {
        int n = arr.length, l = 0, r = n - 1, mid;
        if(n == 1) {
            if(arr[0] == target)    return new int[]{0, 0};
            return new int[]{-1, -1};
        }    
        int start = -1, end = -1;
        while(l <= r) {
            mid = r - (r - l) / 2;
            if(arr[mid] == target) {
                start = mid;
                r = mid - 1;
            }
            else if (arr[mid] > target) {
                r = mid - 1;
            } else l = mid + 1;
        }

        if(start != -1) {
            l = 0;
            r = n - 1;
            while(l <= r) {
                int mid2 = r - (r - l) / 2;
                if(arr[mid2] == target) {
                    end = mid2;
                    l = mid2 + 1;
                }
                else if (arr[mid2] > target) {
                    r = mid2 - 1;
                } else l = mid2 + 1;
            }
        }

        return new int[] {start, end};
    }
}
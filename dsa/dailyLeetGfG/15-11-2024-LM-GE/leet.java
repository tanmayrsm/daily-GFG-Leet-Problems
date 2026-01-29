class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int left = 0;
        while (left + 1 < n && arr[left] <= arr[left + 1]) {
            left++;
        }
        if (left == n - 1) {
            return 0;
        }
        int right = n - 1;
        while (right > 0 && arr[right - 1] <= arr[right]) {
            right--;
        }
        int minRemoval = Math.min(n - left - 1, right); 
        int i = 0;
        int j = right;
        while (i <= left && j < n) {
            if (arr[i] <= arr[j]) {
                minRemoval = Math.min(minRemoval, j - i - 1);
                i++;
            } else {
                j++;
            }
        }
        
        return minRemoval;
    }
}

// 1 2 3 8...2 3 4

// [1,2,3,10,4,2,3,5]
// 1-2-3-(10-4-2)-3-5


// 1 2 3 [10 4] 3 5
// 1 2 3 [10 4 1] 5
// 1 2 3 4 [10 4 1] 5
// [10 5 4] 1 2
// 1 2 3 4 5 [4 5 4] 6
// 1,2,3,5,[4,3,1,2,3,5,4,3],6 => l1 :3, l2: 11
// 1,2,3,5...3,6
// 3,6...1,2,3,5

// 



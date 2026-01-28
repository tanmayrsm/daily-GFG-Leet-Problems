class Solution {
    public int longestSubarray(int[] arr) {
        int n = arr.length, ans = 0, prev = -1, prevIdx = -1;
        for (int i = 0; i < n; ) {
            if (arr[i] == 1) {
                int j = i + 1;
                while(j < n && arr[j] == 1) j++;
                arr[i] = j - i;


                if (prev == -1) {
                    prev = arr[i];
                    prevIdx = j - 1;
                } else if (i - prevIdx == 2) {
                    ans = Math.max(ans, prev + arr[i]);
                }
                ans = Math.max(ans, arr[i]);

                // egde case -> all elements in arr are '1'
                if (arr[i] == n)    return arr[i] - 1;
                prev = arr[i];
                prevIdx = j - 1;
                
                i = j;
            } else i++;
        }

        // INITIALLY I used below for-loop to keep logic simple
        // then I commented this, and tried to add its logic in above loop, with 'prev' concept
        // for (int i = 0; i < n; ) {
            
        //     if (arr[i] > 0) {
        //         ans = Math.max(ans, arr[i]);
        //         int j = i + arr[i];
        //         if (j < n && j + 1 < n && arr[j + 1] > 0) {
        //             ans = Math.max(ans, arr[i] + arr[j + 1]);
        //         }
        //         i = j;
        //     } else i++;
        // }
        return ans;
    }
}


class Solution {
    public int longestSubarray(int[] nums) {
        int left = 0, zeros = 0, res = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeros++;
            while (zeros > 1) {
                if (nums[left++] == 0) zeros--;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
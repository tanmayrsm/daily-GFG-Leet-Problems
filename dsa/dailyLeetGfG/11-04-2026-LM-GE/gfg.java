class Solution {
    public int countIncreasing(int[] arr) {
        // code here
        int ans = 0, n = arr.length, curr = 1;
        for(int i = 1; i < n; i++) {
            if(arr[i] > arr[i - 1]) {
                curr++;
            } else {
                int adder = (curr * (curr + 1)) / 2;
                adder -= curr;
                ans += adder;
                curr = 1;
            }
        }
        if(curr > 1) {
            int adder = (curr * (curr + 1)) / 2;
            adder -= curr;
            ans += adder;
        }
        return ans;
    }
}

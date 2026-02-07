class Solution {
    int maxSum(int[] arr) {
        // code here
        int sum = 0, n = arr.length, ans = 0, cSum = 0;
        for(int ele : arr)  sum += ele;
        for(int i = 0; i < n; i++)  cSum += i * arr[i];
        ans = Math.max(ans, cSum);

        for (int i = 0; i < n; i++) {
            int curr = arr[i] * n;
            cSum -= sum;
            cSum += curr;
            ans = Math.max(ans, cSum);
        }
        return ans;
    }
}

// what i doing ? sliding window
// 9 7 8 -> cSum = 9(0) + 7(1) + 8(2)
// for 7 8 9 -> cSUm - (sum of all elements i.e 9 + 7 + 8)
// += newElem (9 * n)
// do a dry run do DIY


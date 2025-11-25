class Solution {
    public int subarrayXor(int[] arr) {
        // code here
        // if [a,b,c,d,e] -> no of times each char in subarray -> [5,8,9,8,5] -> return xor of even idx nos
        // if [a,b,c,d] -> [4,6,6,4]
        int n = arr.length, ans = 0;
        if (n % 2 == 0) return 0;

        for (int i = 0; i < n; i += 2)
            ans ^= arr[i];
        return ans;
    }
}
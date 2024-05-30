class Solution {
    public int countTriplets(int[] arr) {
        int ans = 0, n = arr.length;
        int[] prefXor = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            prefXor[i] = arr[i - 1] ^ prefXor[i - 1];
        }

        for(int i = 0; i < n; i++) {
            for(int k = i + 1; k <= n; k++) {
                if(prefXor[i] == prefXor[k]) {
                    ans += k - i - 1;
                }
            }
        }

        return ans;
    }
}
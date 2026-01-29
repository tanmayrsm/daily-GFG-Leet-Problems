class Solution {
        public int minSoldiers(int[] arr, int k) {
                // code here
                int n = arr.length, req = 0, ptr = 0, ans = 0;
                for (int i = 0; i < n; i++) {
                        arr[i] = arr[i] % k;
                        if (arr[i] == 0)    req++;
                }
                Arrays.sort(arr);
                ptr = n - 1;
                n = (n % 2 == 1) ? n + 1 : n;
                while(ptr >= 0 && req < n / 2) {
                        ans += k - arr[ptr--];
                        req++;
                }
                return ans;
        }
}
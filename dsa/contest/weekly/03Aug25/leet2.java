class Solution {
    public int maxBalancedShipments(int[] weight) {
        int n = weight.length, ans = 0;
        for (int i = n - 1; i >= 1; ) {
            if (weight[i] < weight[i - 1]) {
                ans++;
                int ct = -1;
                while(i - 1 >= 0 && weight[i] < weight[i - 1]) {
                    ct++;
                    i--;
                }
                ans += (ct / 2);
            } else i--;
        }
        return ans;
    }
}


class Solution {
    public boolean canDistribute(int k, int n, int[] quantities) {
        int store = 0;

        for (int quantity : quantities) {
            store += (quantity % k == 0) ? quantity / k : quantity / k + 1; 
        }

        return store <= n;
    }

    public int minimizedMaximum(int n, int[] quantities) {
        if (n == 1) return quantities[0];
        int l = 1, r = Arrays.stream(quantities).max().getAsInt();

        while (l < r) {
            int m = (r - l) / 2 + l;
            if (canDistribute(m, n, quantities)) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return l;
    }
}
class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < energy.length; i++) {
            if (i - k >= 0 && energy[i] < energy[i] + energy[i - k]) energy[i] += energy[i - k];
            if (i + k >= energy.length) {  // as I know next jump does not exists
                ans = Math.max(ans, energy[i]);
            }
        }
        return ans;
    }
}
class Solution {
    public int minValue(String s, int k) {
        // code here
        int n = s.length();
        int[] ct = new int[26];
        for (int i = 0; i < n; i++)
            ct[s.charAt(i) - 'a']++;
        while(k > 0) {
            int maxIndex = getMaxIndex(ct);
            if (maxIndex == -1) break;
            ct[maxIndex]--;
            k--;
        }
        return getSum(ct);
    }
    private int getSum(int[] ct) {
        int sum = 0;
        for (int x : ct) {
            sum += x*x;
        }
        return sum;
    }
    private int getMaxIndex(int[] ct) {
        int maxo = 0, idx = -1;
        for (int i = 0; i < ct.length; i++) {
            if (ct[i] > maxo) {
                maxo = ct[i];
                idx = i;
            }
        }
        return idx;
    }
}